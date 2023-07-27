package com.management.delivery.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.domain.Utils.AmazonReviewScraper;
import com.management.domain.dto.AuthRequest;
import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.ProductEntity;
import com.management.domain.service.JwtService;
import com.management.domain.service.ProductService;
import com.management.domain.service.ReviewService;
import com.management.domain.service.TokenValidationService;
import com.management.domain.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/v1")
@Api(value = "API REST PRODUCTS")
@CrossOrigin(origins = "http://localhost:3000")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
		@ApiResponse(code = 400, message = "Invalid Request"),
		@ApiResponse(code = 401, message = "You don't have access to this resource"),
		@ApiResponse(code = 403, message = "You don't have access to this resource"),
		@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 500, message = "Error unexpected") })
public class ProductController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	AmazonReviewScraper amazonReviewScraper;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ProductService productService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;

	@Autowired
	TokenValidationService tokenValidationService;

	@GetMapping("/feedback")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Product with feedback list.", notes = "Receive ASIN as parameter check if the product exists and save the records.", response = String.class, authorizations = {
			@Authorization(value = "BasicAuth") })
	public ResponseEntity<Optional<List<ProductEntity>>> getProductByASIN(@RequestParam("asin") String asin)
			throws RecordNotFoundException, Exception {

		// Capture products info from a asin code.
		Optional<List<ProductEntity>> products = amazonReviewScraper.reviewScraper(asin);

		if (products.isEmpty())
			return new ResponseEntity<Optional<List<ProductEntity>>>(products, HttpStatus.NOT_FOUND);

		List<ProductEntity> productList = products.get();

		for (ProductEntity productEntity : productList) {
			productService.findById(productEntity);
			reviewService.findByAsinAndFeedBackCode(productEntity.getReviewEntity());
		}

		return new ResponseEntity<Optional<List<ProductEntity>>>(products, HttpStatus.OK);
	}

	@GetMapping("/lastProducts")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	@ApiOperation(value = "Retur1' list of last product searched.", response = String.class, authorizations = {
			@Authorization(value = "BasicAuth") })
	public ResponseEntity<Optional<List<ProductEntity>>> getLastProducts() throws RecordNotFoundException, Exception {

		// Capture products info from a asin code.
		Optional<List<ProductEntity>> lastProducts = productService.listAll();

		return new ResponseEntity<Optional<List<ProductEntity>>>(lastProducts, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authRequest.getUsername().toUpperCase(), authRequest.getPassword().toUpperCase()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

	@PostMapping("/new")
	public ResponseEntity<AuthRequest> addUser(@RequestBody AuthRequest authRequest) throws Exception {

		if (userService.addNewUser(authRequest).isEmpty())
			return new ResponseEntity<AuthRequest>(authRequest, HttpStatus.CONFLICT);

		return new ResponseEntity<AuthRequest>(authRequest, HttpStatus.OK);
	}

	@PostMapping("/checkToken")
	public Boolean checkToken(@RequestBody String token) throws JSONException {
		return tokenValidationService.isTokenValid(token);
	}

}

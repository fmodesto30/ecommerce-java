package com.management.delivery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.domain.Utils.AmazonReviewScraper;
import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.ProductEntity;
import com.management.domain.model.ReviewEntity;
import com.management.domain.service.ProductService;
import com.management.domain.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/v1")
@Api(value = "API REST PRODUCTS")
@CrossOrigin(origins = "*")
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
	ProductService productService;
	
	@Autowired
	ReviewService reviewService;

	@GetMapping("/feedback")
	@ApiOperation(value = "Product with feedback list.", 
				  notes = "Receive ASIN as parameter check if the product exists and save the records.", 
				  response = String.class, 
				  authorizations = {@Authorization(value = "BasicAuth") })
	public ResponseEntity<Optional<List<ProductEntity>>> getProductByASIN(@RequestParam("asin") String asin)
			throws RecordNotFoundException, Exception {
		
		
		//Capture products info from a asin code.
		Optional<List<ProductEntity>> products = amazonReviewScraper.reviewScraper(asin);	
		
		if (products.isEmpty())
			return new ResponseEntity<Optional<List<ProductEntity>>>(products, HttpStatus.NOT_FOUND);
		
	    List<ProductEntity> productList = products.get();
	    
	    for (ProductEntity productEntity : productList) {
	    	Optional<ProductEntity> product = productService.findById(productEntity);
	    	Optional<ReviewEntity> review = reviewService.findByAsinAndFeedBackCode(productEntity.getReviewEntity());
	    }
		
		return new ResponseEntity<Optional<List<ProductEntity>>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/lastProducts")
	@ApiOperation(value = "Return a list of last product searched.", 
				  response = String.class, authorizations = {@Authorization(value = "BasicAuth") })
	public ResponseEntity<Optional<List<ProductEntity>>> getLastProducts()	throws RecordNotFoundException, Exception {
		
		//Capture products info from a asin code.
		Optional<List<ProductEntity>> lastProducts = productService.listAll();
		
		return new ResponseEntity<Optional<List<ProductEntity>>> (lastProducts, HttpStatus.OK);
	}
	
	
}

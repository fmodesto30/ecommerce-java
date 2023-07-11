package com.management.domain.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.management.domain.dao.ProductDAO;
import com.management.domain.model.ProductEntity;
import com.management.domain.model.ReviewEntity;

@Component
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static ProductEntity productEntityAux = new ProductEntity();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Optional<ProductEntity> findByAsin(String asin) throws Exception {

		final StringBuffer sql = new StringBuffer();
		sql.append("\n SELECT * ");
		sql.append("\n FROM TB_PRODUCT");
		sql.append("\n WHERE asin = ? ");

		Object[] args = { asin };
		ProductEntity productEntity;

		try {
			productEntity = (ProductEntity) jdbcTemplate.queryForObject(sql.toString(), args, new BeanPropertyRowMapper(ProductEntity.class));
			return Optional.of(productEntity);
		} catch (EmptyResultDataAccessException ex) {
			// Handle the case when no matching record is found
			return Optional.empty();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Optional<ProductEntity> saveProduct(ProductEntity productEntity) throws Exception {
		final StringBuffer sql = new StringBuffer();
		sql.append("\n INSERT INTO TB_PRODUCT (");
		sql.append("\n ASIN , ");
		sql.append("\n NAME , ");
		sql.append("\n PRICE , ");
		sql.append("\n DESCRIPTION , ");
		sql.append("\n TIME  )");
		sql.append("\n VALUES ( ?, ?, ? , ?, ?)");

		Object[] args = { productEntity.getAsin(), productEntity.getName(), productEntity.getPrice(), productEntity.getDescription(), productEntity.getTime() };

		int rowsAffected = jdbcTemplate.update(sql.toString(), args);
		if (rowsAffected == 0) {
			throw new Exception("Failed to save product.");
		}

		return Optional.of(productEntity);

	}
	
	@Override
	public Optional<List<ProductEntity>> listAll() throws Exception {
	    final StringBuffer sql = new StringBuffer();
	    sql.append("\n SELECT * FROM ");
	    sql.append("\n TB_PRODUCT A ");
	    sql.append("\n INNER JOIN ");
	    sql.append("\n TB_REVIEW B ");
	    sql.append("\n ON A.ASIN = B.ASIN ");
	    sql.append("\n ORDER BY A.TIME DESC");

	    Map<String, ProductEntity> productMap = new HashMap<>();

	    jdbcTemplate.query(sql.toString(), rs -> {
	        String asin = rs.getString("ASIN");

	        // Create new product and reviews list if this is a new product
	        if (!productMap.containsKey(asin)) {
	            ProductEntity productEntity = new ProductEntity();
	            productEntity.setProductId(rs.getLong("PRODUCT_ID"));
	            productEntity.setAsin(asin);
	            productEntity.setName(rs.getString("NAME"));
	            productEntity.setPrice(rs.getString("PRICE"));
	            productEntity.setDescription(rs.getString("DESCRIPTION"));
	            productEntity.setTime(rs.getTimestamp("TIME"));
	            productEntity.setReviews(new ArrayList<>());
	            
	            productMap.put(asin, productEntity);
	        }

	        // Always add the review to the existing product
	        ReviewEntity reviewEntity = new ReviewEntity();
	        reviewEntity.setReviewId(rs.getLong("REVIEW_ID"));
	        reviewEntity.setAsin(asin);
	        reviewEntity.setFeedBackCode(rs.getString("FEEDBACK_CODE"));
	        reviewEntity.setCustomerName(rs.getString("CUSTOMER_NAME"));
	        reviewEntity.setFeedBack(rs.getString("FEEDBACK"));
	        reviewEntity.setFeedBackDate(rs.getString("FEEDBACK_DATE"));
	        reviewEntity.setRating(rs.getString("RATING"));

	        productMap.get(asin).getReviews().add(reviewEntity);
	    });

	    return Optional.of(new ArrayList<>(productMap.values()));
	}


	public class ProductReviews {
	    private ProductEntity product;
	    private List<ReviewEntity> reviews;

	    public ProductReviews(ProductEntity product, List<ReviewEntity> reviews) {
	        this.product = product;
	        this.reviews = reviews;
	    }

		/**
		 * @return the product
		 */
		public ProductEntity getProduct() {
			return product;
		}

		/**
		 * @param product the product to set
		 */
		public void setProduct(ProductEntity product) {
			this.product = product;
		}

		/**
		 * @return the reviews
		 */
		public List<ReviewEntity> getReviews() {
			return reviews;
		}

		/**
		 * @param reviews the reviews to set
		 */
		public void setReviews(List<ReviewEntity> reviews) {
			this.reviews = reviews;
		}

	    // Getters and setters
	    
	    
	}


}

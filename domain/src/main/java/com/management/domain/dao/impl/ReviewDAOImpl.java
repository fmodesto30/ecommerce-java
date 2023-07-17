package com.management.domain.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.management.domain.dao.ReviewDAO;
import com.management.domain.model.ReviewEntity;

@Component
public class ReviewDAOImpl implements ReviewDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Optional<ReviewEntity> findByAsinAndFeedBackCode(String asin, String feedbackCode) throws Exception {

		final StringBuffer sql = new StringBuffer();
		sql.append("\n SELECT * ");
		sql.append("\n FROM TB_REVIEW");
		sql.append("\n WHERE ASIN = ? and FEEDBACK_CODE = ? ");

		Object[] args = { asin, feedbackCode };
		ReviewEntity reviewEntity;

		try {
			reviewEntity = (ReviewEntity) jdbcTemplate.queryForObject(sql.toString(), args, new BeanPropertyRowMapper(ReviewEntity.class));
			return Optional.of(reviewEntity);
		} catch (EmptyResultDataAccessException ex) {
			// Handle the case when no matching record is found
			return Optional.empty();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Optional<ReviewEntity> saveReview(ReviewEntity reviewEntity) throws Exception {
		final StringBuffer sql = new StringBuffer();
		sql.append("\n INSERT INTO TB_REVIEW (");
		sql.append("\n ASIN , ");
		sql.append("\n FEEDBACK_CODE , ");
		sql.append("\n CUSTOMER_NAME , ");
		sql.append("\n FEEDBACK , ");
		sql.append("\n FEEDBACK_DATE , ");
		sql.append("\n RATING , ");
		sql.append("\n TIME  )");
		sql.append("\n VALUES ( ?, ?, ?, ?, ?, ?, ? )");

		Object[] args = { reviewEntity.getAsin(), reviewEntity.getFeedBackCode(), reviewEntity.getCustomerName(), 
						  reviewEntity.getFeedBack(), reviewEntity.getFeedBackDate(), reviewEntity.getRating(), reviewEntity.getTime() };

		int rowsAffected = jdbcTemplate.update(sql.toString(), args);
		if (rowsAffected == 0) {
			throw new Exception("Failed to save product.");
		}

		return Optional.of(reviewEntity);
	}

}

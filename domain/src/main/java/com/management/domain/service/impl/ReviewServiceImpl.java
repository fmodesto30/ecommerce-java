package com.management.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.domain.dao.ReviewDAO;
import com.management.domain.model.ReviewEntity;
import com.management.domain.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDAO reviewDAO;

	@Override
	public Optional<ReviewEntity> findByAsinAndFeedBackCode(ReviewEntity reviewEntity) throws Exception {
 		Optional<ReviewEntity> review = reviewDAO.findByAsinAndFeedBackCode(reviewEntity.getAsin(), reviewEntity.getFeedBackCode());
		
		if(review.isEmpty())
			reviewDAO.saveReview(reviewEntity);
			
		return review;

	}

}

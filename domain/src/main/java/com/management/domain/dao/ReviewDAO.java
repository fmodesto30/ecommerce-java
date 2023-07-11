package com.management.domain.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.management.domain.model.ReviewEntity;

@Component
public interface ReviewDAO {

	Optional<ReviewEntity> saveReview(ReviewEntity reviewEntity) throws Exception;

	Optional<ReviewEntity> findByAsinAndFeedBackCode(String asin, String feedbackCode) throws Exception;

}

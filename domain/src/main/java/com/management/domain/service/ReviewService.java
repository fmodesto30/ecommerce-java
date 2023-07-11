package com.management.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.management.domain.model.ReviewEntity;

@Service
public interface ReviewService {
	
	Optional<ReviewEntity> findByAsinAndFeedBackCode(ReviewEntity reviewEntity) throws Exception;

}

package com.management.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.management.domain.dao.impl.ProductDAOImpl.ProductReviews;
import com.management.domain.model.ProductEntity;
import com.management.domain.model.ReviewEntity;

@Service
public interface ProductService {
	
	Optional<ProductEntity> findById(ProductEntity productEntity) throws Exception;

	Optional<List<ProductEntity>> listAll()throws Exception;

}

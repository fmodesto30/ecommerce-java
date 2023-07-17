package com.management.domain.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.management.domain.model.ProductEntity;

@Component
public interface ProductDAO {

	public Optional<List<ProductEntity>> listAll() throws Exception;
	
	Optional<ProductEntity> findByAsin(String asin) throws Exception;

	Optional<ProductEntity> saveProduct(ProductEntity productEntity) throws Exception;
	
}

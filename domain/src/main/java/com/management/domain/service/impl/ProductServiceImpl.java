package com.management.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.domain.dao.ProductDAO;
import com.management.domain.model.ProductEntity;
import com.management.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public Optional<ProductEntity> findById(ProductEntity productEntity) throws Exception {
		Optional<ProductEntity> product = productDAO.findByAsin(productEntity.getAsin());
		
		if(product.isEmpty())
			productDAO.saveProduct(productEntity);
		
		return product;
	}

	@Override
	public Optional<List<ProductEntity>> listAll() throws Exception {
		Optional<List<ProductEntity>> lastProducts = productDAO.listAll();
		return lastProducts;
	}

}

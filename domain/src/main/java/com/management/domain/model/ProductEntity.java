package com.management.domain.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_PRODUCT")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name = "asin", nullable = false, length = 10)
	private String asin;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private String price;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "time", nullable = false)
	private Date time;
	
	private ReviewEntity reviewEntity;
	
	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
	private List<ReviewEntity> reviews;
		
}



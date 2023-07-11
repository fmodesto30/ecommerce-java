package com.management.domain.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
	
	private List<ReviewEntity> reviews;

	/**
	 * @return the id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param id the id to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the asin
	 */
	public String getAsin() {
		return asin;
	}

	/**
	 * @param asin the asin to set
	 */
	public void setAsin(String asin) {
		this.asin = asin;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the reviewEntity
	 */
	public ReviewEntity getReviewEntity() {
		return reviewEntity;
	}

	/**
	 * @param reviewEntity the reviewEntity to set
	 */
	public void setReviewEntity(ReviewEntity reviewEntity) {
		this.reviewEntity = reviewEntity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "ProductEntity [" + (productId != null ? "productId=" + productId + ", " : "") + (asin != null ? "asin=" + asin + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (price != null ? "price=" + price + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (time != null ? "time=" + time + ", " : "")
				+ (reviewEntity != null ? "reviewEntity=" + reviewEntity + ", " : "")
				+ (reviews != null ? "reviews=" + reviews : "") + "]";
	}
	
}



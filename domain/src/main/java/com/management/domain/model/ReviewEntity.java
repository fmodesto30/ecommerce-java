package com.management.domain.model;

import java.util.Date;

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
@Table(name="TB_REVIEW")
public class ReviewEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	
	@Column(name = "asin", nullable = false, length = 10)
	private String asin;
	
	@Column(name = "customername", nullable = false, length = 10)
	private String customerName;
	
	@Column(name = "feedbackcode", nullable = false)
	private String feedBackCode;
	
	@Column(name = "feedback", nullable = false)
	private String feedBack;
	
	@Column(name = "feedbackdate", nullable = false)
	private String feedBackDate;
	
	@Column(name = "rating", nullable = false)
	private String rating;
	
	@Column(name = "time", nullable = false)
	private Date time;

	/**
	 * @return the reviewId
	 */
	public Long getProductId() {
		return reviewId;
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
	 * @return the feedBack
	 */
	public String getFeedBack() {
		return feedBack;
	}

	/**
	 * @param feedBack the feedBack to set
	 */
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	/**
	 * @return the feedBackDate
	 */
	public String getFeedBackDate() {
		return feedBackDate;
	}

	/**
	 * @param feedBackDate the feedBackDate to set
	 */
	public void setFeedBackDate(String feedBackDate) {
		this.feedBackDate = feedBackDate;
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
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the feedBackCode
	 */
	public String getFeedBackCode() {
		return feedBackCode;
	}

	/**
	 * @param feedBackCode the feedBackCode to set
	 */
	public void setFeedBackCode(String feedBackCode) {
		this.feedBackCode = feedBackCode;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewEntity [" + (reviewId != null ? "reviewId=" + reviewId + ", " : "") + (asin != null ? "asin=" + asin + ", " : "")
				+ (customerName != null ? "customerName=" + customerName + ", " : "")
				+ (feedBackCode != null ? "feedBackCode=" + feedBackCode + ", " : "")
				+ (feedBack != null ? "feedBack=" + feedBack + ", " : "")
				+ (feedBackDate != null ? "feedBackDate=" + feedBackDate + ", " : "")
				+ (rating != null ? "rating=" + rating + ", " : "") + (time != null ? "time=" + time : "") + "]";
	}

}

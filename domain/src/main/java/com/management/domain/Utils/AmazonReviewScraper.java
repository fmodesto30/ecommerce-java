package com.management.domain.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.management.domain.model.ProductEntity;
import com.management.domain.model.ReviewEntity;

@Service
public class AmazonReviewScraper {

	private Document document;
	private ProductEntity productEntity;
	private ReviewEntity reviewEntity;
	private int TIMER = 3000;
	private static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";

	public Optional<List<ProductEntity>> reviewScraper(String asin) throws Exception {

		String url_product = "https://www.amazon.com.br/dp/" + asin;
		String url_feedback = "https://www.amazon.com.br/product-reviews/" + asin
				+ "/ref=acr_dp_hist_1?ie=UTF8&filterByStar=critical&reviewerType=all_reviews#reviews-filter-bar";

		Optional<String> productOptional = getProductTitle(url_product);
		String product = productOptional.orElse("");

		Optional<String> priceOptional = getPrice(url_product, getDocument());
		String price = priceOptional.orElse("");

		Optional<String> productDescriptionOptional = getProductDescription(url_product, getDocument());
		String productDescription = productDescriptionOptional.orElse("");

		Document document_feedback = Jsoup.connect(url_feedback).get(); 
		Elements reviewElements_feedback = document_feedback.select("div.review");

		List<ProductEntity> feedbacks = reviewElements_feedback.stream().map(review -> {
			String feedback = review.select("span.review-text-content").text();
			String time = review.select("span.review-date").text();
		
			Elements starRatingElement = review.select("i[data-hook=review-star-rating] span.a-icon-alt");
			String rating = starRatingElement != null ? starRatingElement.text() : "";
			
			String feedbackCode = review.attr("id"); // Retrieve the unique feedback identifier
			Elements profileNameElements = review.select("span.a-profile-name");
			String customerName = profileNameElements.first().text();

			productEntity = new ProductEntity();
			reviewEntity = new ReviewEntity();

			return setEntityValues(productEntity, reviewEntity, asin, product, price, productDescription, customerName,
					feedbackCode, feedback, rating, time);

		}).collect(Collectors.toList());

		return Optional.of(feedbacks);
	}

	public Optional<String> getProductTitle(String url) throws Exception {
		int maxRetries = 5;
		int retryCount = 0;
		String product = "";

 		while (retryCount < maxRetries ||!product.isBlank()) {
			
 			try {
 				setDocument(Jsoup.connect(url).userAgent(USER_AGENT).get());
 				
 			} catch (Exception e) {
 				throw new Exception("Product not found");
 			}
 			
			Element productTitleElement = getDocument().selectFirst("#productTitle");

			if (productTitleElement != null) {
				product = productTitleElement.text().trim();
				break;
			} else {
				retryCount++;
				if (retryCount < maxRetries ) {
					try {
						Thread.sleep(TIMER);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return Optional.ofNullable(product);
	}

	public Optional<String> getProductDescription(String url, Document document)
			throws IOException, InterruptedException {

		String productDescription = "";

		Element featureBulletsElement = document.getElementById("featurebullets_feature_div");

		if (featureBulletsElement != null) {
			Elements descriptionElements = featureBulletsElement
					.select("div#feature-bullets ul.a-unordered-list li span.a-list-item");

			if (descriptionElements != null && !descriptionElements.isEmpty()) {
				StringBuilder descriptionBuilder = new StringBuilder();
				for (Element descriptionElement : descriptionElements) {
					String descriptionItem = descriptionElement.text().trim();
					descriptionBuilder.append(descriptionItem).append("\n | ");
				}
				productDescription = descriptionBuilder.toString().trim();
			}
		}
		return Optional.ofNullable(productDescription);
	}

	public Optional<String> getPrice(String url_product, Document document) throws IOException, InterruptedException {

		String price = "";
		Element priceElement = document.selectFirst("span.a-price span.a-offscreen");

		if (priceElement != null) {
			price = priceElement.text();
		}

		return Optional.ofNullable(price);
	}

	private ProductEntity setEntityValues(ProductEntity productEntity, ReviewEntity reviewEntity, String asin,
			String product, String price, String description, String customerName, String feedbackCode, String feedback,
			String rating, String feedbackDate) {

		reviewEntity.setAsin(asin);
		reviewEntity.setCustomerName(customerName);
		reviewEntity.setFeedBackCode(feedbackCode);
		reviewEntity.setFeedBack(feedback);
		reviewEntity.setFeedBackDate(feedbackDate);
		reviewEntity.setRating(rating);
		reviewEntity.setTime(new Date());

		productEntity.setAsin(asin);
		productEntity.setName(product);
		productEntity.setPrice(price);
		productEntity.setDescription(description);
		productEntity.setTime(new Date());

		productEntity.setReviewEntity(reviewEntity);

		return productEntity;
	}

	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

}

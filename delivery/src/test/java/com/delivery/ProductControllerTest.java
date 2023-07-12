package com.delivery;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.management.delivery.controller.ProductController;
import com.management.domain.Utils.AmazonReviewScraper;
import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.ProductEntity;
import com.management.domain.model.ReviewEntity;
import com.management.domain.service.ProductService;
import com.management.domain.service.ReviewService;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private AmazonReviewScraper amazonReviewScraper;

    @MockBean
    private ProductService productService;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void testGetProductByASIN() throws RecordNotFoundException, Exception {
        String asin = "B0BV55WWMW";
        
        amazonReviewScraper = new AmazonReviewScraper();

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setAsin(asin);
        productEntity1.setName("Test Product 1");
        productEntity1.setPrice("20");
        productEntity1.setDescription("Test description 1");
        productEntity1.setTime(new Date());

        ReviewEntity reviewEntity1 = new ReviewEntity();
        reviewEntity1.setAsin(asin);
        reviewEntity1.setCustomerName("Test Customer 1");
        reviewEntity1.setFeedBackCode("Feedback Code 1");
        reviewEntity1.setFeedBack("Feedback 1");
        reviewEntity1.setFeedBackDate("Feedback Date 1");
        reviewEntity1.setRating("Rating 1");
        reviewEntity1.setTime(new Date());

        reviewEntity1.setProductEntity(productEntity1);
        productEntity1.setReviews(Collections.singletonList(reviewEntity1));

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setAsin(asin);
        productEntity2.setName("Test Product 2");
        productEntity2.setPrice("30");
        productEntity2.setDescription("Test description 2");
        productEntity2.setTime(new Date());

        ReviewEntity reviewEntity2 = new ReviewEntity();
        reviewEntity2.setAsin(asin);
        reviewEntity2.setCustomerName("Test Customer 2");
        reviewEntity2.setFeedBackCode("Feedback Code 2");
        reviewEntity2.setFeedBack("Feedback 2");
        reviewEntity2.setFeedBackDate("Feedback Date 2");
        reviewEntity2.setRating("Rating 2");
        reviewEntity2.setTime(new Date());

        reviewEntity2.setProductEntity(productEntity2);
        productEntity2.setReviews(Collections.singletonList(reviewEntity2));

        assertEquals(404, 404);
    }
}

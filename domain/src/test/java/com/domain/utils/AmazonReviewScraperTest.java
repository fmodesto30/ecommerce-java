package com.domain.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import com.management.domain.Utils.AmazonReviewScraper;

public class AmazonReviewScraperTest {

    private AmazonReviewScraper scraper;
    private Document mockDocument;

    @Before
    public void setUp() {
        scraper = new AmazonReviewScraper();
        mockDocument = mock(Document.class);
    }

    @Test
    public void testGetProductTitle_WhenTitleExists_ReturnsTitle() throws Exception {
        String expectedTitle = "Product Title";
        when(mockDocument.selectFirst("#productTitle")).thenReturn(mockElementWithText(expectedTitle));
        scraper.setDocument(mockDocument);

        Optional<String> actualTitle = scraper.getProductTitle("https://www.amazon.com.br/dp/B09WXVH7WK");
        

        assertTrue(actualTitle.isPresent());
        assertEquals(expectedTitle, actualTitle.get());
    }


    @Test
    public void testGetProductTitle_WhenTitleDoesNotExist_ReturnsEmptyOptional() throws Exception {
        when(mockDocument.selectFirst("#productTitle")).thenReturn(null);
        scraper.setDocument(mockDocument);

        Optional<String> actualTitle = scraper.getProductTitle("https://www.amazon.com.br/dp/B09WXVH7WK");

        assertFalse(actualTitle.isPresent());
    }

    @Test
    public void testGetProductDescription_WhenDescriptionExists_ReturnsDescription() throws IOException, InterruptedException {
        String expectedDescription = "Product Description";
        when(mockDocument.getElementById("featurebullets_feature_div")).thenReturn(mockElementWithDescription(expectedDescription));

        Optional<String> actualDescription = scraper.getProductDescription("https://www.amazon.com.br/dp/B09WXVH7WK", mockDocument);

        assertTrue(actualDescription.isPresent());
        assertEquals(expectedDescription, actualDescription.get());
    }

    @Test
    public void testGetProductDescription_WhenDescriptionDoesNotExist_ReturnsEmptyOptional() throws IOException, InterruptedException {
        when(mockDocument.getElementById("featurebullets_feature_div")).thenReturn(null);

        Optional<String> actualDescription = scraper.getProductDescription("https://www.amazon.com.br/dp/B09WXVH7WK", mockDocument);

        assertFalse(actualDescription.isPresent());
    }

    @Test
    public void testGetPrice_WhenPriceExists_ReturnsPrice() throws IOException, InterruptedException {
        String expectedPrice = "$19.99";
        when(mockDocument.selectFirst("span.a-price span.a-offscreen")).thenReturn(mockElementWithText(expectedPrice));

        Optional<String> actualPrice = scraper.getPrice("https://www.example.com/product", mockDocument);

        assertTrue(actualPrice.isPresent());
        assertEquals(expectedPrice, actualPrice.get());
    }

    @Test
    public void testGetPrice_WhenPriceDoesNotExist_ReturnsEmptyOptional() throws IOException, InterruptedException {
        when(mockDocument.selectFirst("span.a-price span.a-offscreen")).thenReturn(null);

        Optional<String> actualPrice = scraper.getPrice("https://www.amazon.com.br/dp/B09WXVH7WK", mockDocument);

        assertFalse(actualPrice.isPresent());
    }

    public org.jsoup.nodes.Element mockElementWithText(String text) {
        org.jsoup.nodes.Element mockElement = mock(org.jsoup.nodes.Element.class);
        when(mockElement.text()).thenReturn(text);
        return mockElement;
    }

    public org.jsoup.nodes.Element mockElementWithDescription(String description) {
        org.jsoup.nodes.Element mockElement = mock(org.jsoup.nodes.Element.class);
        Elements mockElements = mock(Elements.class);
        when(mockElements.isEmpty()).thenReturn(false);
        when(mockElements.size()).thenReturn(1);
        when(mockElements.first()).thenReturn(mockElementWithText(description));
        when(mockElement.select("div#feature-bullets ul.a-unordered-list li span.a-list-item")).thenReturn(mockElements);
        return mockElement;
    }
}
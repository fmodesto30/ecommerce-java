package com.management.application;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HTMLParserExample {
    public static void main(String[] args) {
        String url = "https://www.amazon.com.br/dp/B0C91JQDLY";

        try {
            Document doc = Jsoup.connect(url).get();
                 
            // Get price
            Element priceElement = doc.selectFirst("span.a-price span.a-offscreen");
            if (priceElement != null) {
                String price = priceElement.text();
                System.out.println("Price: " + price);
            } else {
                System.out.println("Price not found");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

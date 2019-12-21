package com.gpembed;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Utils {
    public static void main(String[] args) throws Exception {
        if (true) {
            String urlString = "https://photos.app.goo.gl/a1N2ughgZXwCkKPX8";
            System.out.format("Google Photos Share URL: %s\nEmbed URL: %s\n", urlString, Utils.getEmbedUrl(urlString));
        }
    }
    public static boolean isBlank(String s) {
        return (s == null || s.trim().length() == 0);
    }
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static String getEmbedUrl(String urlString) throws IOException {
        Document doc = Jsoup.connect(urlString).get();
        String query = "meta[property='og:image']";
        Elements elements = doc.select(query);
        System.out.format("%d elements found with query: \"%s\" via URL %s\n", elements.size(), query, urlString);
        for (Element element:elements) {
            System.out.format("Element %s Element attribute: %s\n", element, element.attr("content"));
            return element.attr("content");
        }
        return null;
    }
}

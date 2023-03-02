package io.vpv.version.springbootversionlambda.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentParserUtility {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Document getDocumentFromURL(String url) {
        logger.info("Requesting document from {}.", url);
        if (null == url) {
            throw new IllegalArgumentException("Parameter URL is missing");
        }

        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect to " + url, e);
        }
    }

    public Document getDocumentFromURL(String baseURL, String dependencyPage, String dependencyPageNew) {
        try {
            return getDocumentFromURL(baseURL + dependencyPage);
        } catch (RuntimeException e) {
            //In case of error we will defer to new Page URL
            return getDocumentFromURL(baseURL + dependencyPageNew);
        }
    }
}

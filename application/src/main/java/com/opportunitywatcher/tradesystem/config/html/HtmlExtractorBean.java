package com.opportunitywatcher.tradesystem.config.html;

import com.opportunitywatcher.tradesystem.config.ConfigProperties;
import com.opportunitywatcher.tradesystem.config.HtmlExtractor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class HtmlExtractorBean implements HtmlExtractor {

    private final ResourceLoader resourceLoader;

    private final ConfigProperties.HtmlExtractorProperties properties;

    @Autowired
    public HtmlExtractorBean(final ResourceLoader resourceLoader, final ConfigProperties configProperties) {
        this.resourceLoader = resourceLoader;
        this.properties = configProperties.getHtml();
    }

    @Override
    public String extractBodyContentFromStaticResource(String resourceName) {
        final String resourceLocation = properties.getResourcePrefix() + resourceName;
        final Resource htmlResource = resourceLoader
                .getResource(resourceLocation);
        if (htmlResource != null) {
            try (InputStream input = htmlResource.getInputStream()) {
                final Document htmlDocument = Jsoup.parse(input, properties.getCharacterEncoding(), properties.getBaseUri());
                return htmlDocument.body().outerHtml();
            } catch (IOException e) {
                log.error("Error reading the requested HTML resource: {}", resourceName, e);
                throw new RuntimeException("Error reading " + resourceName, e);
            }
        } else {
            String errorMessage = MessageFormatter
                    .format("No resource called {} found at \"{}\"", resourceName, properties.getResourcePrefix())
                    .getMessage();
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}

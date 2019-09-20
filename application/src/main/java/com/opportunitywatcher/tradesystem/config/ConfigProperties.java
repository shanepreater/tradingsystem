package com.opportunitywatcher.tradesystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("tradingsystem.config")
@Data
public class ConfigProperties {
    private String disclaimerVersion;

    private String disclaimer;

    private HtmlExtractorProperties html = new HtmlExtractorProperties();

    @Data
    public static class HtmlExtractorProperties {
        public static final String UTF8 = "utf-8";
        public static final String DEFAULT_BASE_URI = "http://www.opportunitywatcher.com";

        private String resourcePrefix = "classpath:static/";
        private String characterEncoding = UTF8;
        private String baseUri = DEFAULT_BASE_URI;
    }
}

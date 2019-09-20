package com.opportunitywatcher.tradesystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConfigInfoContributor implements InfoContributor {
    private final ConfigProperties configProperties;

    private final HtmlExtractor htmlExtractor;

    private final BuildProperties buildProperties;

    @Autowired
    public ConfigInfoContributor(final ConfigProperties configProperties,
                                 final HtmlExtractor htmlExtractor,
                                 final BuildProperties buildProperties) {
        this.configProperties = configProperties;
        this.htmlExtractor = htmlExtractor;
        this.buildProperties = buildProperties;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> configDetails = new HashMap<>();
        configDetails.put("disclaimerVersion", configProperties.getDisclaimerVersion());
        final String disclaimerHtml = htmlExtractor.extractBodyContentFromStaticResource(configProperties.getDisclaimer());
        configDetails.put("disclaimer", disclaimerHtml);

        builder.withDetail("version", buildProperties.getVersion());
        builder.withDetail("config", configDetails);
    }
}

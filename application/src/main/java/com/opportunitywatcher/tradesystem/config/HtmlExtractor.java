package com.opportunitywatcher.tradesystem.config;

/**
 * Provides the body content for a static HTML resource.
 */
public interface HtmlExtractor {
    String extractBodyContentFromStaticResource(final String resourceName);
}

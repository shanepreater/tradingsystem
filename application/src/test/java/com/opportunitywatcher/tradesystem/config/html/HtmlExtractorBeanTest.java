package com.opportunitywatcher.tradesystem.config.html;

import com.opportunitywatcher.tradesystem.config.ConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HtmlExtractorBeanTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private Resource resource;

    private ConfigProperties configProperties;

    private HtmlExtractorBean underTest;

    @BeforeEach
    public void initialise() {
        configProperties = new ConfigProperties();
        configProperties.setDisclaimer("disclaimer.html");
        configProperties.setDisclaimerVersion("1.0.0.BETA");

        ConfigProperties.HtmlExtractorProperties hep = new ConfigProperties.HtmlExtractorProperties();
        hep.setResourcePrefix("mock:");
        configProperties.setHtml(hep);

        underTest = new HtmlExtractorBean(resourceLoader, configProperties);
    }

    @Test
    @DisplayName("Ensure a simple HTML document is read correctly.")
    void testThatASimpleHtmlFileWorks() throws IOException {
        final String simpleHtml = "<html><head><title>Hi there!</title></head><body><div><h3>Title here</h3><p>This is the body text</p></div></body></html>";
        ByteArrayInputStream bis = new ByteArrayInputStream(simpleHtml.getBytes("utf-8"));

        when(resourceLoader.getResource("mock:test1.html")).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(bis);

        final String bodyText = underTest.extractBodyContentFromStaticResource("test1.html");
        assertNotNull(bodyText);
        assertFalse(StringUtils.isEmpty(bodyText));
        assertEquals("<body>\n" +
                " <div>\n" +
                "  <h3>Title here</h3>\n" +
                "  <p>This is the body text</p>\n" +
                " </div>\n" +
                "</body>", bodyText);
    }

    @Test
    @DisplayName("Ensure that an exception is thrown if the resource isn't loaded.")
    void testExceptionOnResourceLoad() throws IOException {
        when(resourceLoader.getResource("mock:test2.html")).thenReturn(resource);
        when(resource.getInputStream()).thenThrow(new IOException("Not today thank you"));

        try {
            underTest.extractBodyContentFromStaticResource("test2.html");
            fail("Exception should be thrown");
        } catch (RuntimeException e) {
            assertEquals("Error reading test2.html", e.getMessage());
        }

    }

    @Test
    @DisplayName("Ensure that a missing resource throws an exception.")
    void testNoResource() {
        when(resourceLoader.getResource("mock:test3.html")).thenReturn(null);

        try {
            underTest.extractBodyContentFromStaticResource("test3.html");
            fail("exception should be thrown!");
        }
        catch(RuntimeException e) {
            assertEquals("No resource called test3.html found at \"mock:\"", e.getMessage());
        }
    }
}
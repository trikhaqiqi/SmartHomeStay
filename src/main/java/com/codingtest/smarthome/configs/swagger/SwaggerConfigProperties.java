package com.codingtest.smarthome.configs.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("swaggerConfigProperties")
public class SwaggerConfigProperties {
    @Value("${api.version}")
    private String apiVersion;
    @Value("${swagger.enabled}")
    private String enabled = "false";
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.useDefaultResponseMessages}")
    private String useDefaultResponseMessages;
    @Value("${swagger.enableUrlTemplating}")
    private String enableUrlTemplating;
    @Value("${swagger.deepLinking}")
    private String deepLinking;
    @Value("${swagger.defaultModelsExpandDepth}")
    private String defaultModelsExpandDepth;
    @Value("${swagger.defaultModelExpandDepth}")
    private String defaultModelExpandDepth;
    @Value("${swagger.displayOperationId}")
    private String displayOperationId;
    @Value("${swagger.displayRequestDuration}")
    private String displayRequestDuration;
    @Value("${swagger.filter}")
    private String filter;
    @Value("${swagger.maxDisplayedTags}")
    private String maxDisplayedTags;
    @Value("${swagger.showExtensions}")
    private String showExtensions;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUseDefaultResponseMessages() {
        return useDefaultResponseMessages;
    }

    public void setUseDefaultResponseMessages(String useDefaultResponseMessages) {
        this.useDefaultResponseMessages = useDefaultResponseMessages;
    }

    public String getEnableUrlTemplating() {
        return enableUrlTemplating;
    }

    public void setEnableUrlTemplating(String enableUrlTemplating) {
        this.enableUrlTemplating = enableUrlTemplating;
    }

    public String getDeepLinking() {
        return deepLinking;
    }

    public void setDeepLinking(String deepLinking) {
        this.deepLinking = deepLinking;
    }

    public String getDefaultModelsExpandDepth() {
        return defaultModelsExpandDepth;
    }

    public void setDefaultModelsExpandDepth(String defaultModelsExpandDepth) {
        this.defaultModelsExpandDepth = defaultModelsExpandDepth;
    }

    public String getDefaultModelExpandDepth() {
        return defaultModelExpandDepth;
    }

    public void setDefaultModelExpandDepth(String defaultModelExpandDepth) {
        this.defaultModelExpandDepth = defaultModelExpandDepth;
    }

    public String getDisplayOperationId() {
        return displayOperationId;
    }

    public void setDisplayOperationId(String displayOperationId) {
        this.displayOperationId = displayOperationId;
    }

    public String getDisplayRequestDuration() {
        return displayRequestDuration;
    }

    public void setDisplayRequestDuration(String displayRequestDuration) {
        this.displayRequestDuration = displayRequestDuration;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getMaxDisplayedTags() {
        return maxDisplayedTags;
    }

    public void setMaxDisplayedTags(String maxDisplayedTags) {
        this.maxDisplayedTags = maxDisplayedTags;
    }

    public String getShowExtensions() {
        return showExtensions;
    }

    public void setShowExtensions(String showExtensions) {
        this.showExtensions = showExtensions;
    }
}


package com.opportunitywatcher.tradesystem.model;

import java.util.Map;

interface InfoProvider {
    OAuth2UserInfo build(Map<String, Object> attributes);
}

public enum AuthProvider {
    local((attributes) -> null),
    facebook(FacebookOAuth2UserInfo::new),
    google(GoogleOAuth2UserInfo::new),
    github(GithubOAuth2UserInfo::new);

    private final InfoProvider provider;

    AuthProvider(final InfoProvider provider) {
        this.provider = provider;
    }

    public OAuth2UserInfo getInfo(final Map<String, Object> attributes) {
        return provider.build(attributes);
    }
}

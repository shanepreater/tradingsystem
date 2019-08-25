package com.opportunitywatcher.tradesystem.security.oauth2.user;

import com.opportunitywatcher.tradesystem.model.AuthProvider;
import com.opportunitywatcher.tradesystem.model.OAuth2UserInfo;
import com.opportunitywatcher.tradesystem.security.oauth2.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        AuthProvider authProvider = AuthProvider.valueOf(registrationId);
        final OAuth2UserInfo info = authProvider.getInfo(attributes);
        if (info != null) {
            return info;
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}

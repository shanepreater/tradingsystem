package com.opportunitywatcher.tradesystem.payload;

import com.opportunitywatcher.tradesystem.model.AuthProvider;
import com.opportunitywatcher.tradesystem.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private boolean emailVerified;
    private String imageUrl;
    private AuthProvider provider;
    private String providerId;
    private List<String> roles;

    public static UserResponse fromUser(final User user) {
        UserResponse response = new UserResponse();
        response.setEmail(user.getEmail());
        response.setEmailVerified(user.isEmailVerified());
        response.setId(user.getIdentifier());
        response.setImageUrl(user.getImageUrl());
        response.setName(user.getName());
        response.setProvider(user.getProvider());
        response.setProviderId(user.getProviderId());
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        if (user.isAdmin()) {
            roles.add("ADMIN");
        }
        if (user.isTester()) {
            roles.add("TESTER");
        }
        response.setRoles(roles);
        return response;
    }
}

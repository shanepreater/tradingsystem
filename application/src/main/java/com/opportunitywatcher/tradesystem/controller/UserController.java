package com.opportunitywatcher.tradesystem.controller;

import com.opportunitywatcher.tradesystem.ResourceNotFoundException;
import com.opportunitywatcher.tradesystem.UserService;
import com.opportunitywatcher.tradesystem.payload.UserResponse;
import com.opportunitywatcher.tradesystem.security.CurrentUser;
import com.opportunitywatcher.tradesystem.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.findUser(userPrincipal.getId()).map(UserResponse::fromUser)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PostMapping("/user/me/acceptDisclaimer")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void acceptDisclaimer(@RequestBody final String disclaimerVersion, @CurrentUser UserPrincipal userPrincipal) {
        userService.acceptDisclaimer(userPrincipal.getId(), disclaimerVersion);
    }
}

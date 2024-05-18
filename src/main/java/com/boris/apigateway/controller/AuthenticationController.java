package com.boris.apigateway.controller;

import com.boris.apigateway.model.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

//    @GetMapping("/login")
//    public ResponseEntity<AuthenticationResponse> login(
//            @AuthenticationPrincipal OidcUser oidcUser,
//            Model model,
//            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
//    ){
//        AuthenticationResponse response = AuthenticationResponse.builder()
//                .userId(oidcUser.getEmail())
//                .accessToken(client.getAccessToken().getTokenValue())
//                .refreshToken(client.getRefreshToken().getTokenValue())
//                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
//                .authorityList(oidcUser.getAuthorities()
//                        .stream().map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.toList()))
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @AuthenticationPrincipal OidcUser oidcUser,
            Model model,
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
    ){
        if (oidcUser != null && client != null) {
            System.out.println(("Access Token: " + client.getAccessToken().getTokenValue()));
            System.out.println(("Refresh Token: " + client.getRefreshToken().getTokenValue()));
        } else {
            System.out.println(("Authentication failed: OidcUser or OAuth2AuthorizedClient is null"));
        }

        AuthenticationResponse response = AuthenticationResponse.builder()
                .userId(oidcUser.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .authorityList(oidcUser.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

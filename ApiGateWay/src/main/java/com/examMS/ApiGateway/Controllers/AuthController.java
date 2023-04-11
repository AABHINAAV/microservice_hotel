package com.examMS.ApiGateway.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.examMS.ApiGateway.Entities.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model) {

        logger.info("email id : {}", user.getEmail());
        logger.info("token : {}", client.getAccessToken().getTokenValue());
        logger.info("refresh token : {}", client.getRefreshToken());
        logger.info("expires at : {}", client.getAccessToken().getExpiresAt().getEpochSecond());

        AuthResponse authResponse = new AuthResponse();

        authResponse.setUserId(user.getEmail());
//
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());
//
        authResponse.setRefreshToken(String.valueOf(client.getRefreshToken()));
//
        authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

        logger.info("authorities:-\n");
        List<String> authorities = user.getAuthorities().stream().map(grantedauthorities -> {
            logger.info(grantedauthorities.getAuthority());
            return grantedauthorities.getAuthority();
        }).collect(Collectors.toList());

        authResponse.setAuthorities(authorities);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

}
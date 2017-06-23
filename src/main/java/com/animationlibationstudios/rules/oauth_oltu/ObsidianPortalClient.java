package com.animationlibationstudios.rules.oauth_oltu;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//@RestController
public class ObsidianPortalClient {

    private String accessToken;
    private Long accessTokenExpiry;
    private String authorizationCode;

    // TODO: property-ize the hard-coded URIs and keys

    @RequestMapping(method = GET, path = "/redirect")
    private void requestAuthorizationCode(HttpServletRequest req, HttpServletResponse res) throws OAuthProblemException {
        OAuthAuthzResponse response = OAuthAuthzResponse.oauthCodeAuthzResponse(req);
        this.authorizationCode = response.getCode();
    }

    private void temp() throws OAuthSystemException, OAuthProblemException {
        RestTemplate restTemplate = new RestTemplate();

        OAuthClientRequest request = OAuthClientRequest
                .authorizationLocation("https://www.obsidianportal.com/oauth/authorize")
                .setClientId("6jXcenizL0dyfZUPlrY2")
                .setRedirectURI("http://localhost:8080/redirect")
                .buildQueryMessage();

        OAuthAuthzResponse response = restTemplate.getForObject(request.getLocationUri(), OAuthAuthzResponse.class);
    }

    private void requestAccessToken() throws OAuthSystemException, OAuthProblemException {
        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation("https://www.obsidianportal.com/oauth/access_token")
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId("6jXcenizL0dyfZUPlrY2")
                .setClientSecret("8deGcEUJNNHhGziVeJPdl2qy6bOjju6JFd0VLiAF")
                .setRedirectURI("http://localhost:8080/redirect")
                .setCode(this.authorizationCode)
                .buildQueryMessage();

        // create OAuth client that uses custom http client under the hood
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        GitHubTokenResponse response = oAuthClient.accessToken(request, GitHubTokenResponse.class);

        this.accessToken = response.getAccessToken();
        this.accessTokenExpiry = response.getExpiresIn();
    }

    String getAccessToken() throws OAuthSystemException, OAuthProblemException {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            if (this.authorizationCode == null || this.authorizationCode.isEmpty()) {
                temp();
            }
            requestAccessToken();
        }
        return this.accessToken;
    }
}
package com.animationlibationstudios.rules.oauth2;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObsidianPortalClient {

    private String accessToken;

    private void requestAccessToken() throws OAuthSystemException, OAuthProblemException {
        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation("https://www.obsidianportal.com/oauth/access_token")
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId("6jXcenizL0dyfZUPlrY2")
                .setClientSecret("8deGcEUJNNHhGziVeJPdl2qy6bOjju6JFd0VLiAF")
                .setRedirectURI("http://localhost:8080")
                .buildQueryMessage();

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        GitHubTokenResponse response = oAuthClient.accessToken(request, GitHubTokenResponse.class);

        this.accessToken = response.getAccessToken();
    }

    public String getAccessToken() throws OAuthSystemException, OAuthProblemException {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            requestAccessToken();
        }
        return this.accessToken;
    }
}
package com.animationlibationstudios.rules.obsidian4j.authentication.oauth.repository;


import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.AccessToken;
import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.RequestToken;

/**
 * A token repository allows an OAuthClientFactory to store and retrieve OAuth access tokens and request tokens for users of your application.
 */
public interface TokenRepository {

	AccessToken getAccessToken(String userId);

	RequestToken getRequestToken(String userId);

	void setAccessToken(String userId, AccessToken accessToken);

	void setRequestToken(String userId, RequestToken requestToken);

}

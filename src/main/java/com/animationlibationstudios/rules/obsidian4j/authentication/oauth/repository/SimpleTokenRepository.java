package com.animationlibationstudios.rules.obsidian4j.authentication.oauth.repository;

import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.AccessToken;
import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.RequestToken;

import java.util.HashMap;
import java.util.Map;

public class SimpleTokenRepository implements TokenRepository {

	private final Map<String, RequestToken> requestTokens = new HashMap<>();
	private final Map<String, AccessToken> accessTokens = new HashMap<>();

	public AccessToken getAccessToken( String key ) {
		return accessTokens.get( key );
	}

	public RequestToken getRequestToken( String key ) {
		return requestTokens.get( key );
	}

	public void setAccessToken( String key, AccessToken value ) {
		accessTokens.put( key, value );
	}

	public void setRequestToken( String key, RequestToken value ) {
		requestTokens.put( key, value );
	}

}

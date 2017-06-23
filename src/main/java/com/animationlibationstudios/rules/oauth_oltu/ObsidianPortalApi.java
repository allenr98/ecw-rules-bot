package com.animationlibationstudios.rules.oauth_oltu;

import de.btobastian.javacord.utils.LoggerUtil;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//@Service
public class ObsidianPortalApi {

    /**
     * The logger of this class.
     */
    private static final Logger logger = LoggerUtil.getLogger(ObsidianPortalApi.class);

    @Autowired
    ObsidianPortalClient opClient;

    @PostConstruct
    private void init() {
        try {
            logger.info(String.format("OP Access Token retrieved '%s'.", opClient.getAccessToken()));
        } catch (OAuthSystemException|OAuthProblemException e) {
            logger.error("An exception occurred trying to request an OP access token.", e);
        }
    }
}

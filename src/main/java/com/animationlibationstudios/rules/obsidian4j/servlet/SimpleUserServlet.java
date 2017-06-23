package com.animationlibationstudios.rules.obsidian4j.servlet;

import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.client.AuthorizationRequiredException;
import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.repository.SimpleTokenRepository;
import com.animationlibationstudios.rules.obsidian4j.authentication.oauth.repository.TokenRepository;
import com.animationlibationstudios.rules.obsidian4j.obsidian.client.ObsidianClient;
import com.animationlibationstudios.rules.obsidian4j.obsidian.client.ObsidianClientFactory;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.User;
import com.animationlibationstudios.rules.obsidian4j.obsidian.service.UserService;
import com.animationlibationstudios.rules.obsidian4j.obsidian.service.web.WebServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * Created by robeal on 6/23/17.
 */
public class SimpleUserServlet extends HttpServlet {
    private static final String CUSTOMER_KEY = "6jXcenizL0dyfZUPlrY2";
    private static final String CUSTOMER_SECRET = "8deGcEUJNNHhGziVeJPdl2qy6bOjju6JFd0VLiAF";

    private final ObsidianClientFactory clientFactory;

    public SimpleUserServlet() {
        TokenRepository tokenRepository = new SimpleTokenRepository();
        clientFactory = new ObsidianClientFactory( CUSTOMER_KEY, CUSTOMER_SECRET, tokenRepository );
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userId = request.getParameter( "userId" );
        String verifier = request.getParameter( "verifier" );

        URL callbackUrl = new URL( request.getRequestURL().toString() );
        ObsidianClient client = null;

        if( verifier != null ) {
            client = clientFactory.createInstance( userId, verifier );
        } else {
            try {
                client = clientFactory.createInstance( userId, callbackUrl );
            } catch( AuthorizationRequiredException exception ) {
                response.sendRedirect( exception.getAuthorizationUrl() );
                return;
            }
        }

        UserService userService = WebServiceFactory.createUserService( client );
        User user = userService.getCurrentUser();

        response.getWriter().println( user.toString() );
        response.getWriter().flush();
        response.getWriter().close();
    }
}

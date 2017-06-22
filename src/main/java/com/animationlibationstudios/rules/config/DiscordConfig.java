package com.animationlibationstudios.rules.config;

import com.animationlibationstudios.rules.Application;
import com.animationlibationstudios.rules.commands.ApiCommandExecutor;
import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.utils.LoggerUtil;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/*
https://www.obsidianportal.com/oauth/access_token
    ?grant_type=authorization_code
    &client_secret=8deGcEUJNNHhGziVeJPdl2qy6bOjju6JFd0VLiAF
    &redirect_uri=http%3A%2F%2Flocalhost%3A8080
    &client_id=6jXcenizL0dyfZUPlrY2
*/

@Configuration
public class DiscordConfig implements FutureCallback<DiscordAPI> {

    /**
     * The logger of this class.
     */
    private static final Logger logger = LoggerUtil.getLogger(DiscordConfig.class);

    @Autowired
    private CommandExecutor[] commandExecutor;

    /**
     * Successfully connected to discord.
     *
     * @param api The discord api.
     */
    @Override
    public void onSuccess(DiscordAPI api) {
        logger.info("Amount of servers: {}", api.getServers().size());
        logger.info("Connected to discord account {}", api.getYourself());
        CommandHandler handler = new JavacordHandler(api);
        handler.addPermission(Application.adminId, "*");
        api.setMessageCacheSize(10000);

        // register commands
        for (CommandExecutor executor: commandExecutor) {
            if (executor instanceof ApiCommandExecutor) {
                ((ApiCommandExecutor) executor).setCommandHandler(handler);
            }

            handler.registerCommand(executor);
        }
    }

    /**
     * Connecting failed!
     *
     * @param throwable The reason why connection failed.
     */
    @Override
    public void onFailure(Throwable throwable) {
        logger.error("Could not connect to discord!", throwable);
    }


    private String token, adminId;

    /**
     * Attempts to login.
     *
     */
    @PostConstruct
    // public void login(String token, String adminId) {
    private void init() {
        if (null == token || token.isEmpty()) {
            this.token = Application.token;
            this.adminId = Application.adminId;
        }

        DiscordAPI api = Javacord.getApi(this.token, true);
        api.connect(this);
    }

    void login(String token, String adminId) {
        this.token = token;
        this.adminId = adminId;
        init();
    }
}

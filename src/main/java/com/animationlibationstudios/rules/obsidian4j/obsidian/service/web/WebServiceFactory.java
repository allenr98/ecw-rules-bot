package com.animationlibationstudios.rules.obsidian4j.obsidian.service.web;

import com.animationlibationstudios.rules.obsidian4j.obsidian.client.ObsidianClient;

public class WebServiceFactory {

	public static com.animationlibationstudios.rules.obsidian4j.obsidian.service.PageService createPageService( ObsidianClient client ) {
		return new PageService( client );
	}

	public static com.animationlibationstudios.rules.obsidian4j.obsidian.service.CampaignService createCampaignService( ObsidianClient client ) {
		return new CampaignService( client );
	}

	public static com.animationlibationstudios.rules.obsidian4j.obsidian.service.CreatureService createCreatureService( ObsidianClient client ) {
		return new CreatureService( client );
	}

	public static com.animationlibationstudios.rules.obsidian4j.obsidian.service.UserService createUserService( ObsidianClient client ) {
		return new UserService( client );
	}

}

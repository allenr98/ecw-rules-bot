package com.animationlibationstudios.rules.obsidian4j.obsidian.service.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animationlibationstudios.rules.obsidian4j.obsidian.client.ObsidianClient;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Campaign;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Membership;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Role;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.User;
import com.animationlibationstudios.rules.obsidian4j.obsidian.util.JSONBuilder;
import com.animationlibationstudios.rules.obsidian4j.obsidian.util.Properties;

class CampaignService extends Service<Campaign> implements com.animationlibationstudios.rules.obsidian4j.obsidian.service.CampaignService {

	protected CampaignService( ObsidianClient client ) {
		super( client );
	}

	@Override
	public Campaign getById( String id ) {
		return get( "/campaigns/" + id );
	}

	@Override
	public Campaign getBySlug( String slug ) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put( "use_slug", "true" );
		return get( "/campaigns/" + slug, parameters );
	}

	@Override
	protected Campaign adapt( Properties properties ) {

		Campaign campaign = new Campaign();

		campaign.setId( properties.getString( "id" ) );
		campaign.setName( properties.getString( "name" ) );
		campaign.setSlug( properties.getString( "slug" ) );
		campaign.setUrl( properties.getString( "campaign_url" ) );
		campaign.setVisibility( properties.getVisibility( "visibility" ) );
		campaign.setPlayStatus( properties.getPlayStatus( "play_status" ) );
		campaign.setLookingForPlayers( properties.getBoolean( "looking_for_players" ) );
		campaign.setDateCreated( properties.getDate( "created_at" ) );
		campaign.setDateUpdated( properties.getDate( "updated_at" ) );
		campaign.getLocation().setLatitude( properties.getDouble( "lat" ) );
		campaign.getLocation().setLongitude( properties.getDouble( "lng" ) );

		adaptMemberships( campaign, properties.getList( "player_ids", String.class ), Role.PLAYER );
		adaptMemberships( campaign, properties.getList( "fan_ids", String.class ), Role.FAN );

		return campaign;

	}

	private void adaptMemberships( Campaign campaign, List<String> ids, Role role ) {

		for( String id : ids ) {

			User user = new User();

			user.setId( id );

			Membership membership = new Membership();

			membership.setRole( role );
			membership.setUser( user );
			membership.setCampaign( campaign );

			campaign.getMembers().add( membership );

		}

	}

	@Override
	protected String adapt( Campaign model ) {
		JSONBuilder json = new JSONBuilder();
		return json.toString();
	}

}

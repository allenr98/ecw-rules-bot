package com.animationlibationstudios.rules.obsidian4j.obsidian.service.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.animationlibationstudios.rules.obsidian4j.obsidian.client.ObsidianClient;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Campaign;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Page;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Post;
import com.animationlibationstudios.rules.obsidian4j.obsidian.model.WikiPage;
import com.animationlibationstudios.rules.obsidian4j.obsidian.util.Properties;

class PageService extends Service<Page> implements com.animationlibationstudios.rules.obsidian4j.obsidian.service.PageService {

	public PageService( ObsidianClient client ) {
		super( client );
	}

	@Override
	public Page create( Page page ) {
		return post( String.format( "/campaigns/%s/wikis", page.getCampaign().getId() ), page );
	}

	@Override
	public void delete( Page page ) {
		delete( String.format( "/campaigns/%s/wikis/%s", page.getCampaign().getId(), page.getId() ) );
	}

	@Override
	public Page getById( Campaign campaign, String id ) {
		return get( String.format( "/campaigns/%s/wikis/%s", campaign.getId(), id ) );
	}

	@Override
	public Page getBySlug( Campaign campaign, String slug ) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put( "use_slug", "true" );
		return get( String.format( "/campaigns/%s/wikis/%s", campaign.getId(), slug ), parameters );
	}

	@Override
	public Set<Page> list( Campaign campaign ) {
		return list( String.format( "/campaigns/%s/wikis", campaign.getId() ) );
	}

	@Override
	public Page update( Page page ) {
		return put( String.format( "/campaigns/%s/wikis/%s", page.getCampaign().getId(), page.getId() ), page );
	}

	@Override
	protected Page adapt( Properties properties ) {

		Page page = null;

		String type = properties.getString( "type" );

		if( WikiPage.TYPE.equals( type ) ) {

			page = new WikiPage();

		} else if( Post.TYPE.equals( type ) ) {

			Post post = new Post();

			post.setTitle( properties.getString( "post_title" ) );
			post.setTagline( properties.getString( "post_tagline" ) );
			post.setDate( properties.getDate( "post_time" ) );

			page = post;

		}

		page.setId( properties.getString( "id" ) );
		page.setSlug( properties.getString( "slug" ) );
		page.setName( properties.getString( "name" ) );
		page.setUrl( properties.getString( "wiki_page_url" ) );
		page.getCampaign().setId( properties.getString( "campaign_id" ) );
		page.setDateCreated( properties.getDate( "created_at" ) );
		page.setDateUpdated( properties.getDate( "updated_at" ) );
		page.setGameMasterOnly( properties.getBoolean( "is_game_master_only" ) );
		page.getTags().addAll( properties.getList( "tags", String.class ) );
		page.setBodyAsText( properties.getString( "body" ) );
		page.setBodyAsHtml( properties.getString( "body_html" ) );
		page.setGameMasterInfoAsText( properties.getString( "game_master_info" ) );
		page.setGameMasterInfoAsHtml( properties.getString( "game_master_info_html" ) );

		return page;

	}

	@Override
	protected String adapt( Page page ) {

		if( page == null ) {
			return "";
		}

		return page.toString();

	}

}

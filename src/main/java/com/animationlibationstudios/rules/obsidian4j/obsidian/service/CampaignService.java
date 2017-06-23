package com.animationlibationstudios.rules.obsidian4j.obsidian.service;

import com.animationlibationstudios.rules.obsidian4j.obsidian.model.Campaign;

public interface CampaignService {

	public Campaign getById( String id );

	public Campaign getBySlug( String slug );

}

package com.animationlibationstudios.rules.obsidian4j.obsidian.service;

import com.animationlibationstudios.rules.obsidian4j.obsidian.model.User;

public interface UserService {

	public User getCurrentUser();

	public User getById( String id );

	public User getByName( String name );

}

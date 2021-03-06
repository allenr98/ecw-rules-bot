package com.animationlibationstudios.rules.obsidian4j.obsidian.model;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import com.animationlibationstudios.rules.obsidian4j.obsidian.util.JSONBuilder;

public class User {
	
	private String id;
	private String name;
	private String avatarUrl;
	private String profileUrl;
	private Date dateCreated;
	private Set<Membership> campaigns;
	private boolean ascendant;
	private Date dateLastSeen;
	private String timeZoneOffset;
	private Locale locale = Locale.getDefault();

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public Set<Membership> getCampaigns() {
		return campaigns;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateLastSeen() {
		return dateLastSeen;
	}

	public String getId() {
		return id;
	}

	public Locale getLocale() {
		return locale;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public String getName() {
		return name;
	}

	public boolean isAscendant() {
		return ascendant;
	}

	public void setAscendant( boolean ascendant ) {
		this.ascendant = ascendant;
	}

	public void setAvatarUrl( String avatarUrl ) {
		this.avatarUrl = avatarUrl;
	}

	public void setCampaigns( Set<Membership> campaigns ) {
		this.campaigns = campaigns;
	}

	public void setDateCreated( Date dateCreated ) {
		this.dateCreated = dateCreated;
	}

	public void setDateLastSeen( Date dateLastSeen ) {
		this.dateLastSeen = dateLastSeen;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public void setLocale( Locale locale ) {
		this.locale = locale;
	}

	public void setProfileUrl( String profileUrl ) {
		this.profileUrl = profileUrl;
	}

	public void setTimeZoneOffset( String timeZoneOffset ) {
		this.timeZoneOffset = timeZoneOffset;
	}

	public void setName( String name ) {
		this.name = name;
	}
	
	@Override
	public String toString() {
	
		JSONBuilder json = new JSONBuilder();
		
		json.beginObject();
		json.value( "avatar_url", getAvatarUrl() );
		json.value( "created_at", getDateCreated() );
		json.value( "last_seen", getDateLastSeen() );
		json.value( "id", getId() );
		json.value( "name", getName() );
		json.value( "profile_url", getProfileUrl() );
		json.value( "time_zone_offset", getTimeZoneOffset() );
		json.value( "locale", getLocale().toString() );
		json.value( "campaigns", getCampaigns() );
		json.endObject();

		return json.toString();

	}

}

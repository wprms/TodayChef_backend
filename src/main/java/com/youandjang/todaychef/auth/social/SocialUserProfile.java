package com.youandjang.todaychef.auth.social;

public class SocialUserProfile {
	private final String provider;
	private final String socialUserId;
	private final String email;

	public SocialUserProfile(String provider, String socialUserId, String email) {
		this.provider = provider;
		this.socialUserId = socialUserId;
		this.email = email;
	}

	public String getProvider() {
		return provider;
	}

	public String getSocialUserId() {
		return socialUserId;
	}

	public String getEmail() {
		return email;
	}
}

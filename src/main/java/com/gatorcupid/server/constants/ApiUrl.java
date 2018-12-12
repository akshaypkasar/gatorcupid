package com.gatorcupid.server.constants;

public class ApiUrl {
	
	public static final String API = "/gatorscupid";
	public static final String SIGNUP = "/user/signup";
	public static final String SIGNIN = "/user/signin";
	public static final String SIGNOUT = "/user/signout";
	public static final String UPDATE_USER_PROFILE = "/user/{id}";
	public static final String GET_USER_PROFILE = "/user/{id}";
	public static final String GET_BROWSE_PROFILE = "/user/{id}/browseList";
	public static final String LIKE_PROFILE = "/user/{id}/browseList/{id}/like";
	public static final String UNLIKE_PROFILE = "/user/{id}/browseList/{id}/unlike";
}

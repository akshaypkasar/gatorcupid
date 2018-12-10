package com.gatorcupid.server.service;

import java.text.ParseException;

import com.gatorcupid.server.beans.request.UpdateUserProfileRequest;
import com.gatorcupid.server.exception.GCException;
import com.gatorcupid.server.model.User;

public interface UserService {
	
	void sendSignupEmail(String email) throws Exception;
	
	User validateUserCredentials(String user, String code) throws GCException;
	
	void setUserPassword(User user);
	
	public void updateUserProfile(User user, UpdateUserProfileRequest request) throws ParseException;

}

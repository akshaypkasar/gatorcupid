package com.gatorcupid.server.service;

import java.text.ParseException;
import java.util.List;

import com.gatorcupid.server.beans.request.UpdateUserProfileRequest;
import com.gatorcupid.server.beans.response.UserResponse;
import com.gatorcupid.server.exception.GCException;
import com.gatorcupid.server.model.User;

public interface UserService {
	
	void sendSignupEmail(String email) throws Exception;
	
	User validateUserCredentials(String user, String code) throws GCException;
	
	void setUserPassword(User user);
	
	void updateUserProfile(User user, UpdateUserProfileRequest request) throws ParseException;
	
	//void updateUserPic(User user, UpdateUserPicRequest request);
	
	List<UserResponse> fetchBrowseList(User user, Integer offset, Integer pageSize);
}

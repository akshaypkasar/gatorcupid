package com.gatorcupid.server.service;

import com.gatorcupid.server.exception.GCException;
import com.gatorcupid.server.model.User;

public interface UserService {
	
	void sendSignupEmail(String email) throws Exception;
	
	User validateUserCredentials(String user, String code) throws GCException;
	
	void setUserPassword(User user);

}

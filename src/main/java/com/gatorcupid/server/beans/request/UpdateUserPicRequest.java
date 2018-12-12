/*
 * storing only single profile pics as of now hence profilePics are updated using updateuserprofile api
 * 
 * 
 */

/*

package com.gatorcupid.server.beans.request;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gatorcupid.server.exception.GCException;

public class UpdateUserPicRequest implements RequestBean {

	List<PicRequest> profilePics;
	
	@Override
	public void validateRequest() throws GCException {
		// TODO Auto-generated method stub
		
	}

	@JsonProperty("profilePics")
	public List<PicRequest> getProfilePics() {
		return profilePics;
	}

	public void setProfilePics(List<PicRequest> profilePics) {
		this.profilePics = profilePics;
	}

	@Override
	public String toString() {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			str =  mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
 */
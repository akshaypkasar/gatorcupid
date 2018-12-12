package com.gatorcupid.server.beans.request;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gatorcupid.server.exception.GCException;

public class UpdateUserProfileRequest implements RequestBean {
	
	private static final Logger logger = Logger.getLogger(UpdateUserProfileRequest.class);
	
	private String name;
    private String email;
	private Integer gender;
	private String birthYear;
	private Integer interestedIn;
	private Integer intention;
	private String about;
	private String major;
	private Integer isProfileCreated;
	//private List<String> profilePics;
	private String profilePic;
	private Integer profilePicAction;
	
	@Override
	public void validateRequest() throws GCException {	
		
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

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("gender")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@JsonProperty("birthYear")
	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	@JsonProperty("interestedIn")
	public Integer getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(Integer interestedIn) {
		this.interestedIn = interestedIn;
	}

	@JsonProperty("intention")
	public Integer getIntention() {
		return intention;
	}

	public void setIntention(Integer intention) {
		this.intention = intention;
	}

	@JsonProperty("about")
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@JsonProperty("major")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@JsonProperty("isProfileCreated")
	public Integer getIsProfileCreated() {
		return isProfileCreated;
	}

	public void setIsProfileCreated(Integer isProfileCreated) {
		this.isProfileCreated = isProfileCreated;
	}

	@JsonProperty("profilePic")
	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	@JsonProperty("profilePicAction")
	public Integer getProfilePicAction() {
		return profilePicAction;
	}

	public void setProfilePicAction(Integer profilePicAction) {
		this.profilePicAction = profilePicAction;
	}

}

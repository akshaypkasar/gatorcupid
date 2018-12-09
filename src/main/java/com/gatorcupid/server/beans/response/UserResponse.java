package com.gatorcupid.server.beans.response;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gatorcupid.server.constants.Gender;
import com.gatorcupid.server.constants.Intention;
import com.gatorcupid.server.constants.InterestedIn;
import com.gatorcupid.server.constants.State;
import com.gatorcupid.server.model.User;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	
	private String name;
	private String email;
	private Integer gender;
	private Integer birthYear;
	private Integer interestedIn;
	private Integer intention;
	private String about;
	private String major;
	private Integer isProfileCreated;
	
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
	public Integer getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Integer birthYear) {
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
	
	public UserResponse() {}
	
	public UserResponse(User user) {
		super();
		this.name = user.getName();
		this.email = user.getEmail();
		this.gender = user.getGender() != null ? user.getGender().getValue() : null;
		this.birthYear = user.getBirthYear();
		this.interestedIn = user.getInterestedIn() != null ? user.getInterestedIn().getValue() : null;
		this.intention = user.getIntention() != null ? user.getIntention().getValue() : null;
		this.about = user.getAbout();
		this.major = user.getMajor();
		this.isProfileCreated = user.getIsProfileCreated() != null ? user.getIsProfileCreated().getValue() : null;
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

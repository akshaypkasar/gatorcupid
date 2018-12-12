package com.gatorcupid.server.beans.response;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrowseResponse {
	
	@JsonProperty("profiles")
	List<UserResponse> profiles;

	public BrowseResponse() {}

	public BrowseResponse(List<UserResponse> profiles) {
		super();
		this.profiles = profiles;
	}

	public List<UserResponse> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<UserResponse> profiles) {
		this.profiles = profiles;
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

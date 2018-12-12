/*
 * storing only single profile pics as of now hence profilePics are updated using updateuserprofile api
 * 
 */

/*
package com.gatorcupid.server.beans.request;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gatorcupid.server.constants.Errorcode;
import com.gatorcupid.server.exception.GCException;

public class PicRequest implements RequestBean{

	Long id;
	String imageData;
	Integer actionType;
	
	@Override
	public void validateRequest() throws GCException {
		if(actionType == null) {
			throw new GCException(Errorcode.INVALID_ACTION_TYPE, "INVALID_ACTION_TYPE");
		}
	}

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("imageData")
	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	@JsonProperty("actionType")
	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
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
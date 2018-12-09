package com.gatorcupid.server.beans.request;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.apache.commons.validator.EmailValidator;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gatorcupid.server.constants.Errorcode;
import com.gatorcupid.server.exception.GCException;

public class SignInRequest implements RequestBean {
	
	private static final Logger logger = Logger.getLogger(SignInRequest.class);
	
	@JsonProperty("email")
	@NotNull
	private String email;
	
	@JsonProperty("code")
	private String code;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void validateRequest() throws Exception {
		
		EmailValidator validator = EmailValidator.getInstance();
		if (validator.isValid(email)) {
		   String domain = email.substring(email.indexOf('@'));
		   if(!domain.equals("@mail.sfsu.edu")) {
			   logger.error("INVALID_EMAIL_DOMAIN");
			   throw new GCException(Errorcode.INVALID_EMAIL_DOMAIN, "INVALID_EMAIL_DOMAIN");
		   }		  
		} else {
			logger.error("INVALID_EMAIL_ADDRESS");
		   throw new GCException(Errorcode.INVALID_EMAIL_ADDRESS, "INVALID_EMAIL_ADDRESS");
		}
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

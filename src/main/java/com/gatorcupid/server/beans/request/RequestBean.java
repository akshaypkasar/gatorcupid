package com.gatorcupid.server.beans.request;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface RequestBean {
	
	public void validateRequest() throws Exception;

}

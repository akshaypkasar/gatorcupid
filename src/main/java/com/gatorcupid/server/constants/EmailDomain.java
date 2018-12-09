package com.gatorcupid.server.constants;

public enum EmailDomain {
	STUDENT("@mail.sfsu.edu"), STAFF("@sfsu.edu");
	
	String value;
	
	EmailDomain(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

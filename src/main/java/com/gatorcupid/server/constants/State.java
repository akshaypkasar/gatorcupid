package com.gatorcupid.server.constants;

public enum State {

	FALSE(0), TRUE(1);
	
	int value;
	
	State(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}

package com.gatorcupid.server.constants;

public enum InterestedIn {
	
	MEN(1),WOMEN(2), BOTH(3), OTHER(4);
	
	int value;
	
	InterestedIn(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}

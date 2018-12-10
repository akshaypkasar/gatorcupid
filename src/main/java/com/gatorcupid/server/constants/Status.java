package com.gatorcupid.server.constants;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	
	INACTIVE(0), ACTIVE(1), DELETED(2);
	
	int value;
	
	Status(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	private static Map<Integer, Status> map = new HashMap<Integer, Status>();

    static {
        for (Status legEnum : Status.values()) {
            map.put(legEnum.value, legEnum);
        }
    }


    public static Status valueOf(int legNo) {
        return map.get(legNo);
    }
}

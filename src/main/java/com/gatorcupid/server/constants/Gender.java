package com.gatorcupid.server.constants;

import java.util.HashMap;
import java.util.Map;

public enum Gender {

	 OTHER(0), MALE(1), FEMALE(2);
	
	int value;
	private static Map<Integer, Gender> map = new HashMap<Integer, Gender>();
	
	Gender(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
    static {
        for (Gender gen : Gender.values()) {
            map.put(gen.value, gen);
        }
    }

    public static Gender valueOf(int legNo) {
        return map.get(legNo);
    }

}

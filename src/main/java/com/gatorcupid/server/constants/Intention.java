package com.gatorcupid.server.constants;

import java.util.HashMap;
import java.util.Map;

public enum Intention {
	
	SHORT_TERM_DATING(0), LONG_TERM_DATING(1), HOOKUP(2), FRIENDSHIP(3);
	
	int value;
	
	Intention(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

	private static Map<Integer, Intention> map = new HashMap<Integer, Intention>();

    static {
        for (Intention legEnum : Intention.values()) {
            map.put(legEnum.value, legEnum);
        }
    }


    public static Intention valueOf(int legNo) {
        return map.get(legNo);
    }
}

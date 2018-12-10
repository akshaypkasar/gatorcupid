package com.gatorcupid.server.constants;

import java.util.HashMap;
import java.util.Map;

public enum InterestedIn {
	
	MEN(1),WOMEN(2), BOTH(3), OTHER(4);
	
	int value;
	
	InterestedIn(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

	private static Map<Integer, InterestedIn> map = new HashMap<Integer, InterestedIn>();

    static {
        for (InterestedIn legEnum : InterestedIn.values()) {
            map.put(legEnum.value, legEnum);
        }
    }


    public static InterestedIn valueOf(int legNo) {
        return map.get(legNo);
    }
}

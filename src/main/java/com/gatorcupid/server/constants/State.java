package com.gatorcupid.server.constants;

import java.util.HashMap;
import java.util.Map;

public enum State {

	FALSE(0), TRUE(1);
	
	int value;
	
	State(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	private static Map<Integer, State> map = new HashMap<Integer, State>();

    static {
        for (State legEnum : State.values()) {
            map.put(legEnum.value, legEnum);
        }
    }


    public static State valueOf(int legNo) {
        return map.get(legNo);
    }
}

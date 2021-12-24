package com.tondeuse.demo.utils;

import java.util.HashMap;
import java.util.Map;

import com.tondeuse.demo.entites.Direction;

public  class   Utils {
	public  static Map<Character, Direction> myMap = new HashMap<Character, Direction>() {{
		put('N', new Direction('E','W'));
        put('S', new Direction('W','E'));
        put('W', new Direction('N','S'));
        put('E', new Direction('S','N'));
        
       
        
    }};

}

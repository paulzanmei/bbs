package com.paul.bs.util;

import java.util.UUID;

public class Uuid {
	
	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString();
	}
}

package com.swing.ex;

import java.util.HashMap;
import java.util.Map;

import com.swing.user.User;

public class Status {
	public final static int MAIN = 0;
	public final static String FIRSTPANEL = "first";
	public final static String SECOUNTPANEL = "second";
	public final static String DETAILPANEL = "DetailView";
	
	public final static String PREPANEL = "PreviousPanel";
	public final static String MEXTPANEL = "NextPanel";
	
	public final static String USER = "user";
	public final static String LOCTION = "viewLocation";
	//public final static int WRITEPPANEL = 3;
	
	private static Map<String, Object> values = new HashMap<String,Object>();
	
	public static void add(String key, Object value){
		values.put(key, value);
	}
	public static Object get(String key){
		return values.get(key);
	}
	public static boolean contains(String key){
		return values.containsKey(key);
	}
}

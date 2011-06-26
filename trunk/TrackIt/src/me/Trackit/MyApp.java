package me.Trackit;

import android.app.Application;

public class MyApp extends Application {

	 private String user_ID;

	 public String getStringValue() {
	        return user_ID;
	    }
	 
	    public void setStringValue(String value) {
	        user_ID = value;
	    }
	 
 }
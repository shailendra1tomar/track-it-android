package me.bustrackit;

import android.app.Application;

public class MyApp extends Application {

	 private String bus_ID;

	 public String getStringValue() {
	        return bus_ID;
	    }
	 
	    public void setStringValue(String value) {
	        bus_ID = value;
	    }
	 
 }
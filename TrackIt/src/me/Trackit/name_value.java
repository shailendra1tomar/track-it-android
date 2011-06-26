package me.Trackit;

import org.apache.http.NameValuePair;


public class name_value implements NameValuePair {

	String name;
	String value;
	
	public name_value(String name,String value)
	{
		this.name=name;
		this.value=value;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}

package me.bustrackit;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;



public class Getloc extends Activity {
	
	 String provider = LocationManager.GPS_PROVIDER;
	 LocationManager locationManager;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loc);
        

       
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(context);
        
       
        Location location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);
        
        }
        private void updateWithNewLocation(Location location){
        	String latLongString;
        	TextView myLocationText;
        	locationManager.requestLocationUpdates(provider, 1000, 0, new LocationListener() {
            	public void onLocationChanged(Location location) {}
            	public void onProviderDisabled(String provider){}
            	
            	public void onProviderEnabled(String provider){}
            	public void onStatusChanged(String provider, int status,
            	Bundle extras){}
            	});
        	myLocationText = (TextView) findViewById(R.id.myLocationText);
        	if(location != null) {
        		double lat = location.getLatitude();
        		double lng = location.getLongitude();
        		latLongString = "Lat: "+ lat +"\nLong"+ lng;
        	} else {
        		latLongString = "No Location found";
        	}
        	myLocationText.setText("Current location  \n"+latLongString);
        	
	}
        
}

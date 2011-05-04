package me.Trackit;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Map extends MapActivity {
	
	 protected boolean isRouteDisplayed() {
	        return false;
	    }
	  GeoPoint geoPoint;

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.map);
	      
	      
	        LocationManager locationManager;
	        String context = Context.LOCATION_SERVICE;
	        locationManager = (LocationManager) getSystemService(context);
	        
	        String provider = LocationManager.GPS_PROVIDER;
	        final Location location = locationManager.getLastKnownLocation(provider);
	        Button butloc = (Button) findViewById(R.id.buttonmap);
	        butloc.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					updateWithNewLocation(location);
				}
			});
	        
	 }
	        
	        private void updateWithNewLocation(Location location){
	        	if(location != null){
	        	double lat = location.getLatitude();
	    		double lng = location.getLongitude();
	        
	    		MapView mapview = (MapView) findViewById(R.id.mapview);
		        mapview.setBuiltInZoomControls(true);
		        mapview.setFocusableInTouchMode(true);
		        mapview.setSatellite(true);
	                
	       
	    		MapController mController = mapview.getController();
	        geoPoint = new GeoPoint((int) (lat *1E6), (int) (lng *1E6) );
	        
	        mController.animateTo(geoPoint);
	        mController.setCenter(geoPoint);
	        	}
	        	else{
	        		Context context = getApplicationContext();
	        		CharSequence text = "Turn on your GPS";
	        		int duration = Toast.LENGTH_SHORT;

	        		Toast toast = Toast.makeText(context, text, duration);
	        		toast.show();
	        	}
	        
	 
	        }
	
}
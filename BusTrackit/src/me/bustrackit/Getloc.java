package me.bustrackit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

 

public class Getloc extends Activity {
	double lat;
	double lng;
	InputStream is = null;
	String result2 = null;
    StringBuilder sb=null;
   String result;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loc);
        
        String provider = LocationManager.GPS_PROVIDER;		//GPS_PROVIDER
	   	 LocationManager locationManager;
	   	 String context = Context.LOCATION_SERVICE;
	     locationManager = (LocationManager) getSystemService(context); 
	     Location location = locationManager.getLastKnownLocation(provider);
	     updateWithNewLocation(location);
	     locationManager.requestLocationUpdates(provider, 5000, 5, locationListener);

   }
	     private void updateWithNewLocation(Location location) {
	    	 String latLongString;
	    	 TextView myLocationText;
	    	 myLocationText = (TextView)findViewById(R.id.myLocationText);
	    	 if (location != null) {
	    	  lat = location.getLatitude();
	    	  lng = location.getLongitude();
	    	  
	    	  String latS; 
	    	  String lngS;
	    	  
	    	  latS = Double.toString(lat);
	    	  lngS = Double.toString(lng);
	    	//Send lat & lng to server
	    	  MyApp bid = (MyApp)getApplicationContext();
	    	  result= bid.getStringValue();
		    	 
	    	  	  //Context context = getApplicationContext();
	      		  //int duration = Toast.LENGTH_SHORT;
	      		  //Toast toast = Toast.makeText(context,result , duration);
	      		  //toast.show();
		    	 
		    	 ArrayList<NameValuePair> nameValuePairsLOC = new ArrayList<NameValuePair>(); 
		    	 
		    	 	nameValuePairsLOC.add(new name_value("bus_id",result));
		    	 	nameValuePairsLOC.add(new name_value("lat", latS));
			        nameValuePairsLOC.add(new name_value("lng", lngS));
			        
			        try{
			             HttpClient httpclient = new DefaultHttpClient();
			             HttpPost httppost = new HttpPost("http://www.gotrackit.net/server/setbuscoord.php");
			             httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsLOC));
			             HttpResponse response = httpclient.execute(httppost);
			             HttpEntity entity = response.getEntity();
			             is = entity.getContent();
			        
			        }catch(Exception e){
			             Log.e("log_tag", "Error in http connection"+e.toString());
			        }
			      //convert response to string
			        try{
			            BufferedReader reader = new BufferedReader(
			              new InputStreamReader(is,"iso-8859-1"),8);
			              sb = new StringBuilder();
			              sb.append(reader.readLine() + "\n");
			              String line="0";
			              while ((line = reader.readLine()) != null) {
			                sb.append(line + "\n");
			              }
			              is.close();
			              result2=sb.toString();
			              
			              Context contextcheck = getApplicationContext();
			      		  int durations = Toast.LENGTH_LONG;
			      		  Toast toasts = Toast.makeText(contextcheck, result2, durations);
			      		  toasts.show();	
			      		 
			        }catch(Exception e){
			             Log.e("log_tag", "Error converting result "+e.toString());
			        }
	    	 latLongString = "Lat:" + lat + "\nLong:" + lng;
	    	 } 
	    	 else {
	    	 latLongString = "No location found";
	    	 }
	    	 myLocationText.setText("Your Current Position is:\n" +
	    	 latLongString);
	    	 
	     }
	     
	     private final LocationListener locationListener = new LocationListener() {
	    	 public void onLocationChanged(Location location) {
	    	 updateWithNewLocation(location);
	    	 }
	    	 public void onProviderDisabled(String provider){
	    	 updateWithNewLocation(null);
	    	 }
	    	 public void onProviderEnabled(String provider){ }
	    	 public void onStatusChanged(String provider, int status,
	    	 Bundle extras){ }
	    	 };

   
    

}

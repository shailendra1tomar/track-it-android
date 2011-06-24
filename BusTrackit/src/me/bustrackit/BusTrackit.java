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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class BusTrackit extends Activity {
    /** Called when the activity is first created. */
    
    //Login Authentication with server
	String result = null;
    InputStream is = null;
    StringBuilder sb=null;
    String username,password;
    EditText textusername, textpassword;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       
         textusername = (EditText) findViewById(R.id.editTextUsername);
         textpassword = (EditText) findViewById(R.id.editTextPassword);
        
        
        Button signinButton = (Button) findViewById(R.id.signinButton01);
        signinButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				username = textusername.getText().toString();
				password = textpassword.getText().toString();
				
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
		        nameValuePairs.add(new name_value("name",username));
		        nameValuePairs.add(new name_value("driver_pass", password));
		      

		        try{
		             HttpClient httpclient = new DefaultHttpClient();
		             HttpPost httppost = new HttpPost("http://www.gotrackit.net/server/check_driver.php");
		             httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
		              result=sb.toString();
		              
		              	             
		              
		        }catch(Exception e){
		             Log.e("log_tag", "Error converting result "+e.toString());
		        }
		        
		        
		        if (result.contains("reject")) {
		        	Context context = getApplicationContext();
		      		  int duration = Toast.LENGTH_SHORT;
		      		  String wrong ="Invalid  Username or Password";
		      		  Toast toast = Toast.makeText(context, wrong, duration);
		      		  toast.show();
		        	
		        }else{	
		        	
		        	MyApp bid = (MyApp)getApplicationContext();
		             bid.setStringValue(result);
		            
		        		Intent myintent = new Intent(v.getContext(), Getloc.class);
		        		 
		        	
						startActivityForResult(myintent, 0);
		        	}
		        
			}
			
		});
        
    }
}

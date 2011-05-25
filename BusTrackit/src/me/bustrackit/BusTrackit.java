package me.bustrackit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BusTrackit extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Login Authentication with server
        
        Button signinButton = (Button) findViewById(R.id.signinButton01);
        signinButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), Getloc.class);
				startActivityForResult(myintent, 0);
				
			}
		});
        
    }
}

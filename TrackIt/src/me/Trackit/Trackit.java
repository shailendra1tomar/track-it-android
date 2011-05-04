package me.Trackit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Trackit extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button mapButton = (Button) findViewById(R.id.mapButton);
        Button signinButton =(Button) findViewById(R.id.signinButton01);
        Button signupButton = (Button) findViewById(R.id.signupButton);
        signinButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), Signin.class);
				startActivityForResult(myintent, 0);
				
			}
		});
        
        signupButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View x) {
				// TODO Auto-generated method stub
				Intent xyintent = new Intent(x.getContext(),Signup.class);
				startActivityForResult(xyintent, 0);
			}
		});
        mapButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View m) {
				// TODO Auto-generated method stub
				Intent mintent = new Intent(m.getContext(),Map.class);
				startActivityForResult(mintent, 0);
			}
		});
    
    }
}
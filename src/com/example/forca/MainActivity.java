package com.example.forca;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//Cria os botões
	Button btnJogar;
	Button btnCreditos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnJogar = (Button)findViewById(R.id.btnJogar);
		btnCreditos = (Button)findViewById(R.id.btnCreditos);
        
        if(btnJogar !=  null) {
        	
        	btnJogar.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent i = new Intent();
        			i.setClass(getApplicationContext(), Forca.class);
        			startActivity(i);
					
				}
        	});
        }
        if(btnCreditos !=  null) {
        	
        	btnCreditos.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent();
        			intent.setClass(getApplicationContext(), Creditos.class);
        			startActivity(intent);
					
				}
        	});
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

package com.example.forca;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MenuIniciar extends Activity {
	
	Button btn_jogar;
	Button btn_ranking;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_jogar = (Button)findViewById(R.id.btn_jogar);
		btn_ranking = (Button)findViewById(R.id.btn_ranking);
		
		if(btn_jogar !=  null) {
        	
			btn_jogar.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent i = new Intent();
        			i.setClass(getApplicationContext(), MainActivity.class);
        			startActivity(i);
					
				}
        	});
        	
        }
		
		if(btn_ranking !=  null) {
        	
			btn_ranking.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent i = new Intent();
        			i.setClass(getApplicationContext(), Ranking.class);
        			startActivity(i);
					
				}
        	});
        	
        }
	}
}

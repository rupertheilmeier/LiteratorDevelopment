package de.projekt.literator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpActivity extends Activity {
	private Button helpButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		setupUI();
		setupButton();
	}

	private void setupButton() {
		helpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i= new Intent(HelpActivity.this,StartActivity.class);
			startActivity(i);
				
			}
		});
		
	}

	private void setupUI() {
		helpButton=(Button)findViewById(R.id.testHelp);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

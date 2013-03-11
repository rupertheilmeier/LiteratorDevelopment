package de.projekt.literator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {
	private Button about_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		setupUI();
		setupButton();
	}

	private void setupButton() {
		about_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i= new Intent(AboutActivity.this,StartActivity.class);
			startActivity(i);
				
			}
		});
		
	}

	private void setupUI() {
		about_back=(Button)findViewById(R.id.testabout);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

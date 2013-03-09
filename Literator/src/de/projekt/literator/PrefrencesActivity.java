package de.projekt.literator;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PrefrencesActivity extends Activity {
	private Spinner zitier_spinner;
	private Spinner sprache_spinner;
	private Button abschicken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prefrences);
		setupUI();
		setupButton();
		setupLanguageSpinner();
	}
	private void setupLanguageSpinner() {
	
	}
	private void setupButton() {
		abschicken.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PrefrencesActivity.this, "Gespeichert :" + "\nZitierweise: "+ String.valueOf(zitier_spinner.getSelectedItem())+ "\nSprache: "+ String.valueOf(sprache_spinner.getSelectedItem()), Toast.LENGTH_LONG).show();
				
			}
		});
		
	}
	private void setupUI() {
		zitier_spinner=(Spinner)findViewById(R.id.spinner_zitierstil);
		sprache_spinner=(Spinner)findViewById(R.id.spinner_sprache);
		abschicken=(Button)findViewById(R.id.button_abschicken);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

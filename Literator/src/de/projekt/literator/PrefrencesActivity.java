package de.projekt.literator;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TabHost;
import android.widget.Toast;

public class PrefrencesActivity extends Activity {
	private Spinner zitier_spinner;
	private Spinner sprache_spinner;
	private Button abschicken;
	 private String[] languages = { "English","Deutsch" };
	 private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prefrences);
		setupUI();
		setupButton();
		setupLanguageSpinner();
	}
	private void setupLanguageSpinner() {
		ArrayAdapter adapter = new ArrayAdapter(this,
			    android.R.layout.simple_spinner_item, languages);
			  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			  sprache_spinner.setAdapter(adapter);

			  sprache_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			   public void onItemSelected(AdapterView arg0, View arg1,
			     int arg2, long arg3) {
			    Configuration config = new Configuration();
			    switch (arg2) {
			    case 0:
			     config.locale = Locale.ENGLISH;
			     break;
			    case 1:
			     config.locale = Locale.GERMANY;
			     break;
			    default:
			     config.locale = Locale.GERMANY;
			     break;
			    }
			    getResources().updateConfiguration(config, null);
			   }

			   public void onNothingSelected(AdapterView arg0) {
			    // TODO Auto-generated method stub
			    
			   }
			  });

			 }
		
	private void setupButton() {
		abschicken.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PrefrencesActivity.this, "Gespeichert :" + "\nZitierweise: "+ String.valueOf(zitier_spinner.getSelectedItem())+ "\nSprache: "+ String.valueOf(sprache_spinner.getSelectedItem()), Toast.LENGTH_LONG).show();
				
				 startActivity(new Intent(getBaseContext(), StartActivity.class));
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

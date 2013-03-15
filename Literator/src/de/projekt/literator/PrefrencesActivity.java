package de.projekt.literator;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class PrefrencesActivity extends Activity {
	private Spinner zitier_spinner;
	private Spinner sprache_spinner;
	private Button abschicken;
	private String[] languages = {  "Deutsch", "English"};
	private String[] zitierStile={ "Apa", "Abnt","Turabian"};
	
	private EditText emailEdit;
	private Editor editor;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prefrences);
		setupUI();
		setupLanguageSpinner();
		setupZitierStilSpinner();
		loadPref();
		setupButton();
	}

	private void setupZitierStilSpinner() {
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, zitierStile);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		zitier_spinner.setAdapter(adapter);
		zitier_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					savePrefSpinner("spinnerSelectionZitier", 0);
					break;
				case 1:
					savePrefSpinner("spinnerSelectionZitier",1);
					break;
				case 2:
					savePrefSpinner("spinnerSelectionZitier",2);
					break;
				default:
					savePrefSpinner("spinnerSelectionZitier", 0);
					break;
				}
			}

			public void onNothingSelected(AdapterView arg0) {
				// TODO Auto-generated method stub

			}
		});

		
	}

	private void loadPref() {
		sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String emailString = sp.getString("email", "");
		sprache_spinner.setSelection(sp.getInt("spinnerSelectionLanguage",0));
		zitier_spinner.setSelection(sp.getInt("spinnerSelectionZitier", 0) );
		emailEdit.setText(emailString);

	}

	private void savePref(String key, String value) {
		sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		editor = sp.edit();
		editor.putString(key, value);
		editor.commit();

	}
	private void savePrefSpinner(String key, int value){
		sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	private void setupLanguageSpinner() {
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, languages);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprache_spinner.setAdapter(adapter);
		sprache_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView arg0, View arg1, int arg2,
					long arg3) {
				Configuration config = new Configuration();
				switch (arg2) {
				case 0:
					savePrefSpinner("spinnerSelectionLanguage", 0);
					config.locale = Locale.GERMANY;
					break;
				case 1:
					savePrefSpinner("spinnerSelectionLanguage",1);
					config.locale = Locale.ENGLISH;
					break;
				default:
					savePrefSpinner("spinnerSelectionLanguage", 0);
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
				savePref("email", emailEdit.getText().toString());
				Toast.makeText(
						PrefrencesActivity.this,
						"Gespeichert :"
								+ "\nZitierweise: "
								+ String.valueOf(zitier_spinner
										.getSelectedItem())
								+ "\nSprache: "
								+ String.valueOf(sprache_spinner
										.getSelectedItem()), Toast.LENGTH_LONG)
						.show();

				startActivity(new Intent(getBaseContext(), StartActivity.class));
			}
		});

	}

	private void setupUI() {
		zitier_spinner = (Spinner) findViewById(R.id.spinner_zitierstil);
		sprache_spinner = (Spinner) findViewById(R.id.spinner_sprache);
		abschicken = (Button) findViewById(R.id.button_abschicken);
		emailEdit = (EditText) findViewById(R.id.email_edittext);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

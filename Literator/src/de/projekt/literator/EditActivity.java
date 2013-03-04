package de.projekt.literator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class EditActivity extends Activity {

	private EditText authorLastName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_isbn);
		
		authorLastName= (EditText) findViewById(R.id.author_edittext);
		
		Bundle bundle=getIntent().getExtras();
		String ergebnis= bundle.getString("isbn");
		authorLastName.setText(ergebnis);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}


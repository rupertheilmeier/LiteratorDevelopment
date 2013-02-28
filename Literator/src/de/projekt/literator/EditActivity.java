package de.projekt.literator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class EditActivity extends Activity {

	private EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_isbn);
		editText= (EditText) findViewById(R.id.edittext);
		Bundle bundle=getIntent().getExtras();
		String ergebnis= bundle.getString("isbn");
		editText.setText(ergebnis);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

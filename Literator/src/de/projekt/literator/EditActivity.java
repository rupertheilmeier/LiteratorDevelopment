package de.projekt.literator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {

	EditText authorName;
	EditText title;
	EditText year;
	EditText publisher;
	EditText place;
	Button speichern;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_isbn);
		setupUI();
		populateUI();
		onClick();
	}

	private void onClick() {
		speichern.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(EditActivity.this, StartActivity.class);
				startActivity(i);

			}
		});
	}

	private void populateUI() {

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			authorName.setText(extras.getString("AuthorName"));
			title.setText(extras.getString("Title"));
			year.setText(extras.getString("Year"));
			publisher.setText(extras.getString("Publisher"));
			place.setText(extras.getString("Place"));
		}

	}

	private void setupUI() {
		authorName = (EditText) findViewById(R.id.author_name_edittext);
		title = (EditText) findViewById(R.id.title_edittext);
		year = (EditText) findViewById(R.id.year_edittext);
		publisher = (EditText) findViewById(R.id.publisher_edittext);
		place = (EditText) findViewById(R.id.place_edittext);
		speichern = (Button) findViewById(R.id.enter_button);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}

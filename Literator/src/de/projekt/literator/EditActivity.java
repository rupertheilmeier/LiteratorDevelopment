package de.projekt.literator;

import de.projekt.database.Literator_book;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity {

	EditText authorLastName;
	EditText title;
	EditText year;
	EditText publisher;
	EditText place;
	TextView isbn;
	Button speichern;
	private GetData gD;
	private Literator_book lB;
	private final static long DATABASE_ID = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_isbn);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setupUI();
		populateUI();
		onClick();
	}

	private void onClick() {
		speichern.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("Buch wurde gespeichert");

				if (!authorLastName.getText().toString().equals("")
						&& !title.getText().toString().equals("")) {
					Intent i = new Intent(EditActivity.this,
							StartActivity.class);
					lB = new Literator_book(
							authorLastName.getText().toString(),
							"authorFirstName", title.getText().toString(), year
									.getText().toString(), place.getText()
									.toString(),
							publisher.getText().toString(), "Edition", isbn
									.getText().toString(), DATABASE_ID);
					lB.saveToDB(getApplicationContext());

					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(),
							"Titel und Author mŸssen befŸllt sein",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void populateUI() {

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			if(extras.getString("AuthorName")!=null){
				
			authorLastName.setText(extras.getString("AuthorName"));
			title.setText(extras.getString("Title"));
			year.setText(extras.getString("Year"));
			publisher.setText(extras.getString("Publisher"));
			place.setText(extras.getString("Place"));
			isbn.setText(extras.getString("ISBN"));
			}else{
				long id= extras.getLong("book_id");
				Literator_book databaseBook= new Literator_book();
				databaseBook.loadFromDB(id, getApplicationContext());
				
				authorLastName.setText(databaseBook.getLastName());
				title.setText(databaseBook.getTitle());
				year.setText(databaseBook.getYear());
				publisher.setText(databaseBook.getPublisher());
				place.setText(databaseBook.getPlace());
				isbn.setText(databaseBook.getISBN());
			}
		}

	}

	private void setupUI() {
		authorLastName = (EditText) findViewById(R.id.author_name_edittext);
		title = (EditText) findViewById(R.id.title_edittext);
		year = (EditText) findViewById(R.id.year_edittext);
		publisher = (EditText) findViewById(R.id.publisher_edittext);
		place = (EditText) findViewById(R.id.place_edittext);
		speichern = (Button) findViewById(R.id.enter_button);
		isbn = (TextView) findViewById(R.id.isbn);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}

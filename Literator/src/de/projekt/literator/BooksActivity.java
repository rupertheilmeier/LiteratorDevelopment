package de.projekt.literator;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import de.projekt.database.LTDatababase;
import de.projekt.database.Literator_book;

public class BooksActivity extends Activity {
	
	private BooksAdapter adapter;
	private ArrayList<Literator_book> bookArrayList;
	private ListView booksList;
	private LTDatababase ltDBAdapter;
	private Cursor dbCursor;
	private SimpleCursorAdapter sca;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
		initAdapter();
		populateLtDatabase();
		setupBookListener();

	}

	private void initAdapter() {
		ltDBAdapter = new LTDatababase(this);
		ltDBAdapter.open();
		populateLtDatabase();
	}
	public void populateLtDatabase() {
		dbCursor = ltDBAdapter.getAllBooks();
		startManagingCursor(dbCursor);
		String[] from = new String[] { ltDBAdapter.KEY_BOOKS_TITLE, ltDBAdapter.KEY_BOOKS_AUTHOR_FIRSTNAME,
				ltDBAdapter.KEY_BOOKS_AUTHOR_LASTNAME };
		int[] to = new int[] { R.id.titel, R.id.autor_vorname, R.id.autor_nachname };

		sca = new SimpleCursorAdapter(this, R.layout.books_list_item,
				dbCursor, from, to);
		booksList.setAdapter(sca);

		updateList();
	}

	private void updateList() {
		dbCursor.requery();
		sca.notifyDataSetChanged();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ltDBAdapter.close();

	}
	
	private void setupBookListener() {
		booksList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> rg0, View arg1, int arg2,
					long arg3) {
				Intent i= new Intent(BooksActivity.this,EditActivity.class );
				int pos= dbCursor.getInt(ltDBAdapter.COLUMN_KEY);
				Literator_book litBook= (Literator_book)booksList.getItemAtPosition(arg2);
				ltDBAdapter.getBookByID(litBook.getDatabaseID());
				i.putExtra("book_id",litBook.getDatabaseID() );
//				Long id= litBook.getDatabaseID();
//				String titel=litBook.getTitle();
//				String lastName= litBook.getLastName();
//				String firstName= litBook.getFirstName();
//				String year= litBook.getYear();
//				String place= litBook.getPlace();
//				String publisher= litBook.getPublisher();
//				String isbn= litBook.getISBN();
//				String edition= litBook.getEdition();
//				
//				i.putExtra("titel",titel);
//				i.putExtra("lastName",lastName);
//				i.putExtra("firstName",firstName);
//				i.putExtra("year",year);
//				i.putExtra("place",place);
//				i.putExtra("publisher",publisher);
//				i.putExtra("isbn",isbn);
//				i.putExtra("edition",edition);
				startActivity(i);
			}
		
		
		});	
	}

	private void setupUI() {
		setContentView(R.layout.activity_books);
		booksList=(ListView)findViewById(R.id.booklistview);
		bookArrayList=new ArrayList<Literator_book>();
		adapter= new BooksAdapter(this, R.layout.books_list_item, bookArrayList);
		booksList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
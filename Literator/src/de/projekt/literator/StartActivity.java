package de.projekt.literator;

import de.projekt.literator.scanner.ScanActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class StartActivity extends TabActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupTabUI();
	}

	private void setupTabUI() {
		TabHost tabHost = getTabHost();
		
		TabSpec booksspec = tabHost.newTabSpec("books");
		booksspec.setIndicator(null, getResources().getDrawable(R.drawable.icon_books_tab));
        Intent booksIntent = new Intent(this, BooksActivity.class);
        booksspec.setContent(booksIntent);
        
        TabSpec bibliographList = tabHost.newTabSpec("lists");
		bibliographList.setIndicator(null, getResources().getDrawable(R.drawable.icon_bibliograph_list_tab));
        Intent bibliographIntent = new Intent(this, ListsActivity.class);
        bibliographList.setContent(bibliographIntent);
        
        TabSpec scanspec = tabHost.newTabSpec("scan");
		scanspec.setIndicator(null, getResources().getDrawable(R.drawable.icon_scan_tab));
        Intent scanIntent = new Intent(this, ScanActivity.class);
        scanspec.setContent(scanIntent);
        
        TabSpec mailspec = tabHost.newTabSpec("mail");
		mailspec.setIndicator(null, getResources().getDrawable(R.drawable.icon_mail_tab));
        Intent mailIntent = new Intent(this, MailActivity.class);
        mailspec.setContent(mailIntent);
        
        TabSpec prefrencesspec = tabHost.newTabSpec("prefrences");
		prefrencesspec.setIndicator(null, getResources().getDrawable(R.drawable.icon_prefrences_tab));
        Intent prefrencesIntent = new Intent(this, ListsActivity.class);
        prefrencesspec.setContent(prefrencesIntent);
        
        tabHost.addTab(booksspec);
        tabHost.addTab(bibliographList);
        tabHost.addTab(scanspec);
        tabHost.addTab(mailspec);
        tabHost.addTab(prefrencesspec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

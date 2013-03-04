package de.projekt.literator;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import de.projekt.literator.scanner.IntentIntegrator;
import de.projekt.literator.scanner.IntentResult;
import de.projekt.literator.scanner.ScanActivity;



public class StartActivity extends TabActivity {

	
	private TabHost tabHost;
	private ImageButton scanButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupTabUI();
	}

	private void setupTabUI() {
		tabHost = getTabHost();
		setTabs();
		
		scanButton = (ImageButton) findViewById(R.id.scan_button);
		
		scanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentIntegrator integrator = new IntentIntegrator(StartActivity.this);
				integrator.initiateScan();
				
				
			}
		});
		
		
	}
	
	
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		  if (scanResult != null) {
			  //hier stehen die ganzen scan daten drin
		    String barcode = scanResult.getContents();
		     // prüft ob es sich um eine isbn handelt
		    if (barcode.substring(0, 3).equals("978")||barcode.substring(0, 2).equals("979")){
		    	
//		    	isbnNumber.setText(barcode);
//		    	
//		    	this.barcode = barcode;
		    	
		    	Intent i = new Intent(StartActivity.this, EditActivity.class);
			    i.putExtra("isbn", barcode);
			    
			    startActivity(i);
			    
			    
		    }else{
		    	//alert dialog wenn es sich nciht um isbn handelt
		    	AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this).create();
		    	alertDialog.setTitle("No Book Scanned!");
		    	alertDialog.setMessage("The Code you scanned (" + barcode + ") is not an ISBN!");
		    	alertDialog.show();
		    	
		    }
		    
		    
		    
		    
		  }
		  // else continue with any other code you need in the method
		 
		}
	
	
	
	private void setTabs()
	{
		addTab("scanned_books", R.drawable.icon_books_tab, BooksActivity.class);
		addTab("list", R.drawable.icon_bibliograph_list_tab, ListsActivity.class);
		addTab("Fake", R.drawable.icon_books_tab, BooksActivity.class);
		addTab("Mail", R.drawable.icon_mail_tab, MailActivity.class);
		addTab("Prefrences", R.drawable.icon_prefrences_tab, PrefrencesActivity.class);
	}
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

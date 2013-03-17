package de.projekt.literator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import de.projekt.literator.scanner.IntentIntegrator;
import de.projekt.literator.scanner.IntentResult;

@SuppressWarnings("deprecation")
public class StartActivity extends TabActivity {

	private TabHost tabHost;
	private ImageButton scanButton;
	private String barcode;
	private GetData gD;

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
				IntentIntegrator integrator = new IntentIntegrator(
						StartActivity.this);
				integrator.initiateScan();
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanResult != null) {
			// hier stehen die ganzen scan daten drin
			barcode = scanResult.getContents();
			// prÙft ob es sich um eine isbn handelt
			if (barcode != null) {
				if (barcode.substring(0, 3).equals("978")
						|| barcode.substring(0, 2).equals("979")) {
					if (GetData
							.isConnectedWithInternet(getApplicationContext())) {
						startThread();
					} else {
						inItAllertDialogForNoInternetConnection();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Barcode wurde nicht erkannt", Toast.LENGTH_SHORT)
							.show();
					startMainActivityAgain();

				}
			}

		} else {
			alertDialogForNotAISBNValue();
		}
	}

	private void startThread() {

		final Handler h = new Handler();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				h.post(new Runnable() {
					@Override
					public void run() {
						System.out.println("start");
						gD = new GetData(barcode);
						System.out.println("Barcode" + barcode);

					}
				});
				h.post(new Runnable() {

					@Override
					public void run() {

						if (gD.getTitle(0).equals("")) {
							alertDialogForNoBookFound();

						}

						else {
							fillEditActivity();

						}

					}

					private void fillEditActivity() {

						Intent i = new Intent(StartActivity.this,
								EditActivity.class);
						i.putExtra("AuthorName", gD.getAuthor(0));
						i.putExtra("Title", gD.getTitle(0));
						i.putExtra("Year", gD.getYear(0));
						i.putExtra("Place", gD.getPlace(0));
						i.putExtra("Publisher", gD.getPublisher(0));
						i.putExtra("Edition", gD.getEdition(0));
						i.putExtra("ISBN", gD.getIsbn());
						System.out.println("ISBN1" + gD.getIsbn());
						startActivity(i);
					}
				});

			}

		});
		t.start();

	}

	private void startMainActivityAgain() {
		Intent i = new Intent(StartActivity.this, StartActivity.class);
		startActivity(i);
	}

	private void startEditActivity() {
		Intent i = new Intent(StartActivity.this, EditActivity.class);
		startActivity(i);
	}

	private void setTabs() {
		addTab("scanned_books", R.drawable.icon_books_tab, BooksActivity.class);
		addTab("list", R.drawable.icon_bibliograph_list_tab,
				ListsActivity.class);
		addTab("Fake", R.drawable.icon_books_tab, BooksActivity.class);
		addTab("Mail", R.drawable.icon_mail_tab, MailActivity.class);
		addTab("Prefrences", R.drawable.icon_prefrences_tab,
				PrefrencesActivity.class);
	}

	private void addTab(String labelId, int drawableId, Class<?> c) {
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.tab_indicator, getTabWidget(), false);
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

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help:
			Intent helpIntent = new Intent(StartActivity.this,
					HelpActivity.class);
			startActivity(helpIntent);
			return true;
		case R.id.about:
			Intent aboutIntent = new Intent(StartActivity.this,
					AboutActivity.class);
			startActivity(aboutIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void alertDialogForNoBookFound() {
		AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this)
				.create();
		alertDialog.setTitle("Keine Buch gefunden");

		alertDialog.setButton(Dialog.BUTTON_NEGATIVE,
				"Buch manuell hinzufŸgen",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {
						startEditActivity();

					}
				});
		alertDialog.setButton(Dialog.BUTTON_POSITIVE,
				"Buch nicht zur Liste hinzufŸgen",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {
						startMainActivityAgain();

					}
				});
		alertDialog.show();
	}

	private void alertDialogForNotAISBNValue() {
		AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this)
				.create();
		alertDialog.setTitle("No Book Scanned!");
		alertDialog.setMessage("The Code you scanned (" + barcode
				+ ") is not a ISBN!");
		alertDialog.show();
	}

	private void inItAllertDialogForNoInternetConnection() {
		AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this)
				.create();
		alertDialog.setTitle("Keine Verbindung zum Internet");

		alertDialog.setButton(Dialog.BUTTON_NEGATIVE,
				"Buch manuell hinzufŸgen",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {
						startEditActivity();

					}
				});
		alertDialog.setButton(Dialog.BUTTON_POSITIVE, "verbinden",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(final DialogInterface dialog,
							final int which) {
						startActivity(new Intent(
								android.provider.Settings.ACTION_WIRELESS_SETTINGS));

					}
				});
		alertDialog.show();
	}

}

package de.projekt.literator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MailActivity extends Activity {
	private String betreff="Literator_Awesome_app";
	private String content="test buch";
	private Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail);
		setupButton();
	}



	private void setupButton() {
		send=(Button)findViewById(R.id.mail_send);
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			sendEmail();
				
			}
		});
	}

	private void sendEmail() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String emailString = sp.getString("email", "");
		Intent i = new Intent(Intent.ACTION_SEND);

		// setType zum öffnen des richtigen Progamms
		i.setType("message/rfc822");
		// Für Empfänger

		i.putExtra(Intent.EXTRA_EMAIL, new String []{emailString});
		i.putExtra(Intent.EXTRA_SUBJECT, betreff);
		i.putExtra(Intent.EXTRA_TEXT, content);
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
			// Intent x = new Intent(MailActivity.this, StartActivity.class);
			// startActivity(x);

		} catch (android.content.ActivityNotFoundException ex) {

			// Toast.makeText(MailActivity.this,
			// "There are no email clients installed.", Toast.LENGTH_SHORT)
			// .show();
			// blabag
		}
		System.out.println("Email wurde versendet");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;

	}

	// private void sendEmail() {
	// sM = new SendMail("Hall", "Hallo", "Hallo");
	//
	// private void sendEmail() {
	// System.out.println("send");
	//
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_main, menu);
	// return true;
	// }

}
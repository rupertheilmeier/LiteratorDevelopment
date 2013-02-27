package de.projekt.literator.scanner;

import de.projekt.literator.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScanActivity extends Activity {
	
	
		
		private Button button;
		private EditText editText;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
//			button = (Button) findViewById(R.id.button);
//			editText= (EditText) findViewById(R.id.editText);
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_main, menu);
			return true;
		}
		
//		public void onClick(View view){
//			
//			IntentIntegrator integrator = new IntentIntegrator(this);
//			integrator.initiateScan();
//			
//		}
//		
//		public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//			  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//			  if (scanResult != null) {
//			    String barcode = scanResult.getContents();
//			    
//			    editText.setText(barcode);
//			  }
//			  // else continue with any other code you need in the method
//			 
//			}

	}



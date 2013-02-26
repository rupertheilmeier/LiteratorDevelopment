package de.projekt.literator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuLayout extends Activity {
	private static final int MENU_ITEM_1 = Menu.FIRST + 1;
	private static final int MENU_ITEM_2 = Menu.FIRST + 2;
	private static final int MENU_ITEM_3 = Menu.FIRST + 3;
	private static final int MENU_ITEM_4 = Menu.FIRST + 4;
	private static final int MENU_ITEM_5 = Menu.FIRST + 5;

	Toast msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, MENU_ITEM_1, Menu.NONE, null).setIcon(
				R.drawable.normal_scanned_books);

		menu.add(Menu.NONE, MENU_ITEM_2, Menu.NONE, null).setIcon(
				R.drawable.normal_bibliograph);

		menu.add(Menu.NONE, MENU_ITEM_3, Menu.NONE, null).setIcon(
				R.drawable.scan_isbn);
		
		menu.add(Menu.NONE, MENU_ITEM_4, Menu.NONE, null).setIcon(
				R.drawable.normal_mail);
		
		menu.add(Menu.NONE, MENU_ITEM_5, Menu.NONE, null).setIcon(
				R.drawable.normal_prefrences);

		return (super.onCreateOptionsMenu(menu));

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM_1:
			msg = Toast.makeText(MenuLayout.this, "Menu Item 1 Clicked",
					Toast.LENGTH_LONG);
			msg.show();
			break;

		case MENU_ITEM_2:
			msg = Toast.makeText(MenuLayout.this, "Menu Item 2 Clicked",
					Toast.LENGTH_LONG);
			msg.show();
			break;

		case MENU_ITEM_3:
			finish();
		}
		return (super.onOptionsItemSelected(item));
	}
}

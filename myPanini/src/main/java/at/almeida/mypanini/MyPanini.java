package at.almeida.mypanini;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import at.almeida.mypanini.activity.AlbumTabWidget;

public class MyPanini extends ListActivity {
	private Button mCreateAlbumButton;
	StickerAlbumDbAdapter stickerDbAdapter;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = new Intent(this, AlbumTabWidget.class);
		i.putExtra(StickerAlbumDbAdapter.KEY_ID, 1);
		startActivity(i);
//		setContentView(R.layout.album_list);
//
//		mCreateAlbumButton = (Button) this.findViewById(R.id.createAlbumButton);
//
//		mCreateAlbumButton.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//
//				createAlbum();
//			}
//		});
//
//		stickerDbAdapter = new StickerAlbumDbAdapter(this);
//		stickerDbAdapter.open();
//		fillData();
//		registerForContextMenu(getListView());

		

	}

	private void createAlbum() {
		Intent i = new Intent(this, AlbumCreate.class);
		startActivity(i);
	}

	private void fillData() {
		// Get all of the rows from the database and create the item list
		Cursor albumCursor = stickerDbAdapter.getAllAlbums();
		startManagingCursor(albumCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { StickerAlbumDbAdapter.KEY_NAME };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.text1 };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter albums = new SimpleCursorAdapter(this,
				R.layout.album_row, albumCursor, from, to);
		setListAdapter(albums);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			stickerDbAdapter.deleteAlbum(info.id);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, INSERT_ID, 1, R.string.add_album_button);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case INSERT_ID:
			createAlbum();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(this, AlbumTabWidget.class);
		i.putExtra(StickerAlbumDbAdapter.KEY_ID, id);
		startActivity(i);
		// Usado quando
		super.onListItemClick(l, v, position, id);
	}

}
package at.almeida.mypanini.adapters;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import at.almeida.mypanini.R;
import at.almeida.mypanini.model.StickerAlbumMemory;

/**
 * BaseAdapter for our sticker collection.
 * 
 * @author miguel
 * 
 */
public class SitckerAdapter extends BaseAdapter {

	private Activity context;

	private Set<Integer> currentlySelected = new HashSet<Integer>();
	private int size;
	private StickerAlbumDbAdapter stickerDbAdapter;

	private StickerAlbumMemory album;

	private Typeface font;

	public Long getAlbumId() {
		return album.getAlbumId();
	}

	public void setAlbumId(Long albumId) {
		album.setAlbumId(albumId);
	}

	public Typeface getFont() {
		return font;
	}

	public void setFont(Typeface font) {
		this.font = font;
	}

	public SitckerAdapter(Activity context) {
		buildFromAlbumId(context, null);
	}

	public SitckerAdapter(Activity context, Long albumId) {
		
		buildFromAlbumId(context, albumId);
	}

	private void buildFromAlbumId(Activity context, Long albumId) {
		stickerDbAdapter = new StickerAlbumDbAdapter(context);
		stickerDbAdapter.open();
		if (albumId != null) {
			Cursor missingStickers = stickerDbAdapter.getAlbumStickers(albumId);

			size = missingStickers.getCount();
			album = new StickerAlbumMemory(size);
			setAlbumId(albumId);

			missingStickers.moveToFirst();
			int numberIndex = missingStickers
					.getColumnIndex(StickerAlbumDbAdapter.KEY_NUMBER);
			int idIndex = missingStickers
					.getColumnIndex(StickerAlbumDbAdapter.KEY_ID);
			int countIndex = missingStickers
					.getColumnIndex(StickerAlbumDbAdapter.KEY_COUNT);
			while (missingStickers.isAfterLast() == false) {
				int position = missingStickers.getPosition();
				album.setStickerAtPosition(position,
						missingStickers.getString(numberIndex));

				album.setStickerIdAtPosition(position,
						missingStickers.getLong(idIndex));
	
				album.setStickerCountAtPosition(position,
						missingStickers.getInt(countIndex));

				missingStickers.moveToNext();
			}
		}

		this.context = context;

	}

	public int getCount() {
		return size;
	}

	/**
	 * Returns the sticker count at position. The information is stored in an
	 * array of this class
	 * 
	 * @param position
	 * @return
	 */
	public int getStickerCountAtPosition(int position) {
		return album.getStickerCountAtPosition(position);
	}

	public String getItem(int position) {
		return album.getStickerAtPosition(position);
	}

	/**
	 * Returns the sticker id at position. The information is stored in an array
	 * of this class
	 * 
	 * @param position
	 * @return
	 */
	public long getItemId(int position) {
		return album.getStickerIdAtPosition(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View myView = convertView;

		if (convertView == null) {
			/* we define the view that will display on the grid */

			// Inflate the layout
			LayoutInflater li = context.getLayoutInflater();
			myView = li.inflate(R.layout.missing_item_gridview, null, true);
		}
		// Add The Text!!!
		TextView tv = (TextView) myView
				.findViewById(R.id.missing_item_grid_text);
		// Change font if it has been set
		if (font != null) {
			tv.setTypeface(font);
		}
		if (album.getStickerCountAtPosition(position)> 0) {
			markAsHaveIt(myView);
		} else {
			markAsDontHaveIt(myView);
		}
		tv.setText(album.getStickerAtPosition(position));

		return myView;
	}

	public void changeFont(Typeface findFont) {
		font = findFont;

	}

	public void updateCurrentSelection(View v, int position,
			long stickerToChangeId) {
		if (iscurrentlySelected(position)) {
			album.setStickerCountAtPosition(position,0);
			markAsDontHaveIt(v);
			stickerDbAdapter.changeStickerCount(stickerToChangeId, 0);
		} else {
			album.setStickerCountAtPosition(position,1);
			markAsHaveIt(v);
			stickerDbAdapter.changeStickerCount(stickerToChangeId, 1);
		}

	}

	// TODO - Might be better off at StickerAdapter
	private boolean iscurrentlySelected(int position) {
		if (album.getStickerCountAtPosition(position) > 0)
			return true;
		else
			return false;
	}

	/**
	 * Changes the sticker's text view to visually show we have it
	 * 
	 * @param tv
	 */
	private void markAsHaveIt(View view) {
		ImageView iv = (ImageView) view
				.findViewById(R.id.missing_item_grid_image);
		iv.setImageResource(R.drawable.scratched);
		iv.setVisibility(View.VISIBLE);

	}

	/**
	 * Changes the sticker's text view to visually show we DON'T have it
	 * 
	 * @param tv
	 */
	private void markAsDontHaveIt(View view) {
		ImageView iv = (ImageView) view
				.findViewById(R.id.missing_item_grid_image);
		iv.setVisibility(View.GONE);

	}
}

package at.almeida.mypanini.adapters;

import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import at.almeida.mypanini.StickerAlbumDbAdapter;

/**
 * BaseAdapter for our sticker collection.
 * 
 * @author miguel
 * 
 */
public class SitckerAdapter extends BaseAdapter {

	private Context context;
	private String[] texts;
	private int size;
	private StickerAlbumDbAdapter stickerDbAdapter;
	private Long albumId;

	private Typeface font;

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Typeface getFont() {
		return font;
	}

	public void setFont(Typeface font) {
		this.font = font;
	}

	public SitckerAdapter(Context context) {
		buildFromAlbumId(context, null);
	}

	public SitckerAdapter(Context context, Long albumId) {
		setAlbumId(albumId);
		buildFromAlbumId(context, albumId);
	}

	private void buildFromAlbumId(Context context, Long albumId) {
		stickerDbAdapter = new StickerAlbumDbAdapter(context);
		stickerDbAdapter.open();
		if (albumId != null) {
			Cursor missingStickers = stickerDbAdapter
					.getAlbumMissingStickers(albumId);

			size = missingStickers.getCount();
			texts = new String[size];
			missingStickers.moveToFirst();
			int index = missingStickers.getColumnIndex(StickerAlbumDbAdapter.KEY_NUMBER);
			while (missingStickers.isAfterLast() == false) {
				texts[missingStickers.getPosition()] = missingStickers.getString(index);
				missingStickers.moveToNext();
			}
		}

		this.context = context;

	}

	public int getCount() {
		return size;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv;
		if (convertView == null) {
			tv = new TextView(context);
			tv.setLayoutParams(new GridView.LayoutParams(30, 30));
		} else {
			tv = (TextView) convertView;
		}

		// Change font if it has been set
		if (font != null) {
			tv.setTypeface(font);
		}
		tv.setText(texts[position]);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	public void changeFont(Typeface findFont) {
		font = findFont;

	}

}

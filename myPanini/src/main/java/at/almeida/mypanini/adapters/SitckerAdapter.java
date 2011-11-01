package at.almeida.mypanini.adapters;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
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
	private String[] stickers;
	private Long[] stickersId;
	private Integer[] stickersCount;
	private Set<Integer> currentlySelected = new HashSet<Integer>();
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
					.getAlbumStickers(albumId);

			size = missingStickers.getCount();
			stickers = new String[size];
			stickersId= new Long[size];
			stickersCount= new Integer[size];
			missingStickers.moveToFirst();
			int numberIndex = missingStickers.getColumnIndex(StickerAlbumDbAdapter.KEY_NUMBER);
			int idIndex = missingStickers.getColumnIndex(StickerAlbumDbAdapter.KEY_ID);
			int countIndex = missingStickers.getColumnIndex(StickerAlbumDbAdapter.KEY_COUNT);
			while (missingStickers.isAfterLast() == false) {
				stickers[missingStickers.getPosition()] = missingStickers.getString(numberIndex);
				stickersId[missingStickers.getPosition()] = missingStickers.getLong(idIndex);
				stickersCount[missingStickers.getPosition()] = missingStickers.getInt(countIndex);
				missingStickers.moveToNext();
			}
		}

		this.context = context;

	}

	public int getCount() {
		return size;
	}

	public String getItem(int position) {
		return stickers[position];
	}

	public long getItemId(int position) {
		return stickersId[position];
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
		if(stickersCount[position] >0){
			markAsHaveIt(tv);			
		}
		else{
			markAsDontHaveIt(tv);
		}
		tv.setText(stickers[position]);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}



	public void changeFont(Typeface findFont) {
		font = findFont;
		

	}

	public void updateCurrentSelection(View v,int position,long stickerToChangeId) {
		TextView textView =((TextView)v); 
		if( iscurrentlySelected(position)){
			stickersCount[position] = 0;
			markAsDontHaveIt(textView);
			stickerDbAdapter.changeStickerCount(stickerToChangeId, 0);
		}					
		else{
			stickersCount[position] = 1;
			markAsHaveIt(textView);
			stickerDbAdapter.changeStickerCount(stickerToChangeId, 1);
		}
			
	}

	//TODO - Might be better off at StickerAdapter
	private boolean iscurrentlySelected(int position) {
		if(stickersCount[position] >0)
			return true;
		else
			return false;	
	}
	
	/**
	 * Changes the sticker's text view to visually show we have it
	 * @param tv
	 */
	private void markAsHaveIt(TextView tv) {
		tv.setBackgroundColor(Color.RED);	
		tv.setPaintFlags( tv.getPaintFlags() |Paint.STRIKE_THRU_TEXT_FLAG);
		
	}
	
	/**
	 * Changes the sticker's text view to visually show we DON'T have it
	 * @param tv
	 */
	private void markAsDontHaveIt(TextView tv) {
		tv.setBackgroundColor(Color.WHITE);
		tv.setPaintFlags( tv.getPaintFlags()  & ~ Paint.STRIKE_THRU_TEXT_FLAG);
		
	}
}


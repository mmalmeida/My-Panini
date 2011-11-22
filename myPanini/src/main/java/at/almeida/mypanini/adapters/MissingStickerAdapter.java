package at.almeida.mypanini.adapters;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

/**
 * BaseAdapter for our sticker collection.
 * 
 * @author miguel
 * 
 */
public class MissingStickerAdapter extends AbstractStickerAdapter {

	public MissingStickerAdapter(Activity context) {
		super(context);
	}
	
	public MissingStickerAdapter(Activity context, Long albumId) {
		
		super(context,albumId);
	}
	
	
	public Cursor retrieveStickersFromDb(Long albumId) {
		
		return getStickerDbAdapter().getAlbumStickers(albumId);
	}
	
	
	public void updateCurrentSelection(View v, int position,
			long stickerToChangeId) {
		if (iscurrentlySelected(position)) {
			getAlbum().setStickerCountAtPosition(position,0);
			markAsDontHaveIt(v);
			 getStickerDbAdapter().changeStickerCount(stickerToChangeId, 0);
		} else {
			getAlbum().setStickerCountAtPosition(position,1);
			markAsHaveIt(v);
			 getStickerDbAdapter().changeStickerCount(stickerToChangeId, 1);
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		if (getAlbum().getStickerCountAtPosition(position)> 0) {
			markAsHaveIt(view);
		} else {
			markAsDontHaveIt(view);
		}
		return view;
		
	}
	
	

	
	
}

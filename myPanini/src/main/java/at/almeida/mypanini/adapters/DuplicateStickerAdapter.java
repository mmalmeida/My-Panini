package at.almeida.mypanini.adapters;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

public class DuplicateStickerAdapter extends AbstractStickerAdapter {

	public DuplicateStickerAdapter(Activity context) {
		super(context);
	}
	
	public DuplicateStickerAdapter(Activity context, Long albumId) {
		
		super(context,albumId);
	}

	@Override
	public Cursor retrieveStickersFromDb(Long albumId) {
		return getStickerDbAdapter().getDuplicateStickers(albumId);
	}

	@Override
	public void updateCurrentSelection(View v, int position,
			long stickerToChangeId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		markAsDontHaveIt(view);
		return view;
		
	}

}

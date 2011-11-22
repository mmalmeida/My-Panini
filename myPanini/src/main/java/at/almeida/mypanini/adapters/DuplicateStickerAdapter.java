package at.almeida.mypanini.adapters;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;

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

}

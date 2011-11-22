package at.almeida.mypanini.listeners;

import android.app.Activity;
import android.content.Context;
import at.almeida.mypanini.adapters.AbstractStickerAdapter;
import at.almeida.mypanini.adapters.MissingStickerAdapter;
import at.almeida.mypanini.adapters.StickerAlbumDbAdapter;
import at.almeida.mypanini.model.StickerAlbumMemory;
import at.almeida.picker.NumberPicker;
import at.almeida.picker.NumberPicker.OnChangedListener;

/**
 * Used by the unused sticker long press: changes the count number for that
 * sticker
 * 
 * @author miguel
 * 
 */
public class NumberPickerOnChangeListener implements OnChangedListener {

	long _currentStickerId;
	int _currentStickerPosition;
	StickerAlbumDbAdapter stickerDbAdapter;
	private Context _context;
	private AbstractStickerAdapter _stickerAdapter;

	public NumberPickerOnChangeListener(Context context, long currentStickerId) {
		_currentStickerId = currentStickerId;
		stickerDbAdapter = new StickerAlbumDbAdapter(context);
		stickerDbAdapter.open();
		_context = context;
	}

	public NumberPickerOnChangeListener(Activity activity,
			long currentStickerId, int position, AbstractStickerAdapter stickerAdapter) {
		this(activity, currentStickerId);
		_stickerAdapter = stickerAdapter;
		_currentStickerPosition = position;

	}

	// 1) use a model object you pass to the dialog that is also used by the
	// adapter
	// 2)edit the model object
	// 3) call adapter.notifyDataSetChanged()
	@Override
	public void onChanged(NumberPicker picker, int oldVal, int newVal) {

		stickerDbAdapter.changeStickerCount(_currentStickerId, newVal);
		StickerAlbumMemory album = _stickerAdapter.getAlbum();
		album.setStickerCountAtPosition(_currentStickerPosition, newVal);
		_stickerAdapter.notifyDataSetChanged();

	}

}

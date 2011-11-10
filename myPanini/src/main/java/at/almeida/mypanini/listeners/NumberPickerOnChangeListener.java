package at.almeida.mypanini.listeners;

import android.content.Context;
import at.almeida.mypanini.adapters.StickerAlbumDbAdapter;
import at.almeida.picker.NumberPicker;
import at.almeida.picker.NumberPicker.OnChangedListener;

/**
 * Used by the unused sticker long press: changes the count number for
 * that sticker
 * @author miguel
 *
 */
public class NumberPickerOnChangeListener implements OnChangedListener{

	long _currentStickerId;
	StickerAlbumDbAdapter stickerDbAdapter;
	private Context _context;
	
	
	public NumberPickerOnChangeListener(Context context,long currentStickerId) {
		_currentStickerId = currentStickerId;
		stickerDbAdapter = new StickerAlbumDbAdapter(context);
		stickerDbAdapter.open();
		_context = context;
	}

	@Override
	public void onChanged(NumberPicker picker, int oldVal, int newVal) {
		
		stickerDbAdapter.changeStickerCount(_currentStickerId, newVal);
//		1) use a model object you pass to the dialog that is also used by the adapter
//		2)edit the model object
//		3) call adapter.notifyDataSetChanged()
	}

}

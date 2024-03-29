package at.almeida.mypanini.listeners;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import at.almeida.mypanini.R;
import at.almeida.mypanini.adapters.AbstractStickerAdapter;
import at.almeida.mypanini.adapters.MissingStickerAdapter;
import at.almeida.mypanini.model.StickerAlbumMemory;
import at.almeida.picker.NumberPicker;
import at.almeida.picker.NumberPickerDialog;

public class MissingStickerLongItemClickListener implements
		OnItemLongClickListener, NumberPickerDialog.OnNumberSetListener {

	private Activity activity;
	private int _currentStickerCount;
	private AbstractStickerAdapter _stickerAdapter;
	private long _currentStickerId;

	public MissingStickerLongItemClickListener() {
		super();
	}

	public MissingStickerLongItemClickListener(Activity activity) {
		super();
		this.activity = activity;
	}

	public MissingStickerLongItemClickListener(Activity missingItemsActivity,
			AbstractStickerAdapter stickerAdapter) {
		this(missingItemsActivity);
		_stickerAdapter = stickerAdapter;
	}

	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {

		
		Dialog dialog = buildDialog(v,position);


		buildNumberPicker(dialog,position);

		return true;
	}

	
	
	/**
	 * Creates the Number builder in dialog.
	 * Sets the OnChangeListener of the picker
	 * @param dialog
	 * @param position
	 */
	private void buildNumberPicker(Dialog dialog, int position) {
		NumberPicker numberPicker = (NumberPicker) dialog
				.findViewById(R.id.sticker_count_nr_picker);
		
		_currentStickerCount = _stickerAdapter
				.getStickerCountAtPosition(position);
		_currentStickerId = _stickerAdapter.getItemId(position);
		numberPicker.setCurrent(_currentStickerCount);
		numberPicker.setOnChangeListener(new NumberPickerOnChangeListener(
				activity, _currentStickerId,position,_stickerAdapter));
		
	}

	/**
	 * Creates and populates the Dialog that is shown
	 * @param v
	 */
	private Dialog buildDialog(View v,int position) {
		Dialog dialog = new Dialog(v.getContext());

		dialog.setContentView(R.layout.sticker_count_changer_layout);
		dialog.setTitle("Sticker number " + _stickerAdapter
				.getAlbum().getStickerAtPosition(position));

		ImageView image = (ImageView) dialog
				.findViewById(R.id.sticker_count_image);
		image.setImageResource(R.drawable.icon);
		dialog.show();
		
		return dialog;
	}

	public void onNumberSet(int selectedNumber) {
		// TODO Auto-generated method stub

	}

}

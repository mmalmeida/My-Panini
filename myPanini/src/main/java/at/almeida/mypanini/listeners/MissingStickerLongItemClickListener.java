package at.almeida.mypanini.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import at.almeida.mypanini.R;
import at.almeida.mypanini.adapters.SitckerAdapter;
import at.almeida.picker.NumberPicker;
import at.almeida.picker.NumberPickerDialog;

public class MissingStickerLongItemClickListener implements
		OnItemLongClickListener, NumberPickerDialog.OnNumberSetListener  {
	
	private Activity activity;
	private int _currentStickerCount;
	private SitckerAdapter _stickerAdapter;
	private long _currentStickerId;
	
	public MissingStickerLongItemClickListener(){
		super();
	}
	
	

	public MissingStickerLongItemClickListener(Activity activity) {
		super();
		this.activity = activity;
	}



	public MissingStickerLongItemClickListener(
			Activity missingItemsActivity,
			SitckerAdapter stickerAdapter) {
		this(missingItemsActivity);
		_stickerAdapter = stickerAdapter;
	}



	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		
		
		Dialog dialog = new Dialog(v.getContext());

		dialog.setContentView(R.layout.sticker_count_changer_layout);
		dialog.setTitle("Custom Dialog");

		ImageView image = (ImageView) dialog.findViewById(R.id.sticker_count_image);
		image.setImageResource(R.drawable.icon);		
		dialog.show();
		

		NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.sticker_count_nr_picker);
		_currentStickerCount = _stickerAdapter.getStickerCountAtPosition(position);
		_currentStickerId = _stickerAdapter.getItemId(position);
		numberPicker.setCurrent(_currentStickerCount);
		numberPicker.setOnChangeListener(new NumberPicker.OnChangedListener() {
			
			
			public void onChanged(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				
			}
		});

		return true;
	}



	public void onNumberSet(int selectedNumber) {
		// TODO Auto-generated method stub
		
	}

}

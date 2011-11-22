package at.almeida.mypanini.activity;

import android.os.Bundle;
import at.almeida.mypanini.adapters.DuplicateStickerAdapter;

public class DuplicateItemsActivity  extends StickerAbstractActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       


    }
	
	@Override
	public DuplicateStickerAdapter createStickerAdapter() {
		return new DuplicateStickerAdapter(this,albumId);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}



}

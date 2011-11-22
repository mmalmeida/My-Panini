package at.almeida.mypanini.activity;

import at.almeida.mypanini.adapters.MissingStickerAdapter;

public class MissingItemsActivity extends StickerAbstractActivity {
    
	@Override
	public MissingStickerAdapter createStickerAdapter() {
		return new MissingStickerAdapter(this,albumId);
	}



}

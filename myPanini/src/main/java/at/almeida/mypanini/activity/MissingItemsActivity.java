package at.almeida.mypanini.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import at.almeida.mypanini.R;
import at.almeida.mypanini.adapters.SitckerAdapter;
import at.almeida.mypanini.adapters.StickerAlbumDbAdapter;
import at.almeida.mypanini.listeners.MissingStickerLongItemClickListener;

public class MissingItemsActivity extends StickerAbstractActivity {

	private Long albumId;
	
	StickerAlbumDbAdapter stickerDbAdapter;
	SitckerAdapter stickerAdapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        //open db
		stickerDbAdapter = new StickerAlbumDbAdapter(this);
		stickerDbAdapter.open();

        setContentView(R.layout.sticker_gridview);
        GridView gridview = (GridView) findViewById(R.id.stickergridview);
        
        albumId = retrieveAlbumId(savedInstanceState);
        
        stickerAdapter = new SitckerAdapter(this,albumId);
        stickerAdapter.changeFont(findFont(FONT_MIA));
		gridview.setAdapter(stickerAdapter);
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				long stickerToChangeId = parent.getAdapter().getItemId(position);			    
			    stickerAdapter.updateCurrentSelection(v,position,stickerToChangeId);
			}

        });
        
        //adds the functionality on long item press
        gridview.setOnItemLongClickListener(new MissingStickerLongItemClickListener(this,stickerAdapter));

    }
    
	private Long retrieveAlbumId(Bundle savedInstanceState) {
		Long id = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(StickerAlbumDbAdapter.KEY_ID);
        if (albumId == null) {
            Bundle extras = getIntent().getExtras();
            id = extras != null ? extras.getLong(StickerAlbumDbAdapter.KEY_ID)
                                    : null;
        }
        return id;
	}

}

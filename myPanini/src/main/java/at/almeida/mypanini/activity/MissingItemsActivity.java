package at.almeida.mypanini.activity;

import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import at.almeida.mypanini.R;
import at.almeida.mypanini.StickerAlbumDbAdapter;
import at.almeida.mypanini.adapters.SitckerAdapter;

public class MissingItemsActivity extends StickerAbstractActivity {

	private Long albumId;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sticker_gridview);
        GridView gridview = (GridView) findViewById(R.id.stickergridview);
        
        SitckerAdapter stickerAdapter = new SitckerAdapter(this);
        stickerAdapter.changeFont(findFont(FONT_MIA));
		gridview.setAdapter(stickerAdapter);
        gridview.setBackgroundColor(Color.WHITE);
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
			    if(((PaintDrawable)v.getBackground()).getPaint().getColor() ==Color.RED)
			    	v.setBackgroundColor(Color.WHITE);
			    else
			    	v.setBackgroundColor(Color.RED);				
			}
        });

        
        albumId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(StickerAlbumDbAdapter.KEY_ID);
        if (albumId == null) {
            Bundle extras = getIntent().getExtras();
            albumId = extras != null ? extras.getLong(StickerAlbumDbAdapter.KEY_ID)
                                    : null;
        }
    }

}

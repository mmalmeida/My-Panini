package at.almeida.mypanini;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import at.almeida.mypanini.adapters.StickerAlbumDbAdapter;

public class AlbumCreate extends Activity{

	Button albumCreateButton;
	StickerAlbumDbAdapter stickerDbAdapter;
	
	EditText albumName;
	EditText from;
	EditText to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.create_album);
		super.onCreate(savedInstanceState);
		
		stickerDbAdapter = new StickerAlbumDbAdapter(this);
		stickerDbAdapter.open();
		
		populateViewButtons();
		albumCreateButton =(Button) this.findViewById(R.id.albumSaveButton);
		
		albumCreateButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            	saveAlbum();
            }

			private void saveAlbum() {
				int fromSticker =  Integer.valueOf(from.getText().toString());
				int toSticker = Integer.valueOf(to.getText().toString());
				stickerDbAdapter.createAlbum(albumName.getText().toString(),fromSticker,toSticker, null);
				finish();
			}
          });
	}

	private void populateViewButtons() {
		albumName = (EditText) findViewById(R.id.albumName);
		from = (EditText) findViewById(R.id.stickerFrom);
		to = (EditText) findViewById(R.id.stickerTo);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	

}

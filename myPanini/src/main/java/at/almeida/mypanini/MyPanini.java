package at.almeida.mypanini;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import at.almeida.mypanini.activity.DuplicateItemsActivity;
import at.almeida.mypanini.activity.MissingItemsActivity;

public class MyPanini extends TabActivity {
	
		
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.album_details);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent(this, MissingItemsActivity.class);
	    intent.putExtra(StickerAlbumDbAdapter.KEY_ID, 1L);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("missing").setIndicator("Missing",
	                      res.getDrawable(R.drawable.ic_tab_missing))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent(this, DuplicateItemsActivity.class);
	    spec = tabHost.newTabSpec("duplicate").setIndicator("Duplicate",
	                      res.getDrawable(R.drawable.ic_tab_missing))
	                  .setContent(intent);
	    tabHost.addTab(spec);


	    tabHost.setCurrentTab(2);
	}

	
}
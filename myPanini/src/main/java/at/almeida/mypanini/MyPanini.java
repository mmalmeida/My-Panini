package at.almeida.mypanini;

import constants.Fonts;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import at.almeida.mypanini.activity.DuplicateItemsActivity;
import at.almeida.mypanini.activity.MissingItemsActivity;
import at.almeida.mypanini.adapters.StickerAlbumDbAdapter;

public class MyPanini extends TabActivity {

	private TabHost mTabHost;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.album_details);

		// Resources res = getResources(); // Resource object to get Drawables
		// TabHost tabHost = getTabHost(); // The activity TabHost
		// TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab
		//
		// // Create an Intent to launch an Activity for the tab (to be reused)

		//
		// // Initialize a TabSpec for each tab and add it to the TabHost
		// spec = tabHost.newTabSpec("missing").setIndicator("Missing",
		// res.getDrawable(R.drawable.ic_tab_missing))
		// .setContent(intent);
		// tabHost.addTab(spec);
		//
		// // Do the same for the other tabs
		// intent = new Intent(this, DuplicateItemsActivity.class);
		// intent.putExtra(StickerAlbumDbAdapter.KEY_ID, 1L);
		// spec = tabHost.newTabSpec("duplicate").setIndicator("Duplicate",
		// res.getDrawable(R.drawable.ic_tab_missing))
		// .setContent(intent);
		// tabHost.addTab(spec);
		//
		// tabHost.setCurrentTab(2);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		
		intent = new Intent(this, MissingItemsActivity.class);
		intent.putExtra(StickerAlbumDbAdapter.KEY_ID, 1L);
		setupTab(intent, "Missing");
		
		intent = new Intent(this, DuplicateItemsActivity.class);
		intent.putExtra(StickerAlbumDbAdapter.KEY_ID, 1L);
		setupTab(intent, "Duplicate");
		
		mTabHost.setCurrentTab(2);
	}

	private void setupTab(Intent intent, final String tag) {
		View tabview = createTabView(mTabHost.getContext(), tag);
		TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview)
//				.setContent(new TabContentFactory() {
//					public View createTabContent(String tag) {
//						return view;
//					}
//				});
				.setContent(intent);
		mTabHost.addTab(setContent);
	}

	private View createTabView(Context context, final String text) {
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = li.inflate(R.layout.tab_option, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		tv.setTypeface(Typeface.createFromAsset(getAssets(), Fonts.FONT_OHHAY));
		return view;
	}

}
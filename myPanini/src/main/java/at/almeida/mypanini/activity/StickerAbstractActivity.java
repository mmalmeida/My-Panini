package at.almeida.mypanini.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

public abstract class StickerAbstractActivity extends Activity {
	
	public static String FONT_MIA = "fonts/MiasScribblings.ttf"
		;
	protected void alterTextViewFont(TextView textview, String fontLocation) {
		Typeface tf = Typeface.createFromAsset(getAssets(),fontLocation);
        textview.setTypeface(tf);
	}

	public Typeface findFont(String fontPath){
		return Typeface.createFromAsset(getAssets(),fontPath);
	}
}

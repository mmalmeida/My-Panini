package at.almeida.mypanini.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class SitckerAdapter extends BaseAdapter {

	private Context context;
	private String[] texts;
	private int size;
	
	private Typeface font;
	
	
	public Typeface getFont() {
		return font;
	}

	public void setFont(Typeface font) {
		this.font = font;
	}

	public SitckerAdapter(Context context) {
		size = 300;
		texts = new String[size];
		for (int i = 0; i < size; i++) {
			texts[i] = String.valueOf(i);
		}
	    this.context = context;
	}

	public int getCount() {
	    return size;
	}

	public Object getItem(int position) {
	    return null;
	}

	public long getItemId(int position) {
	    return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	    TextView tv;
	    if (convertView == null) {
	        tv = new TextView(context);
	        tv.setLayoutParams(new GridView.LayoutParams(30, 30));
	    }
	    else {
	        tv = (TextView) convertView;
	    }

	    //Change font if it has been set
	    if(font != null){
	    	tv.setTypeface(font);
	    }
	    tv.setText(texts[position]);
	    tv.setGravity(Gravity.CENTER);
	    return tv;
	}

	public void changeFont(Typeface findFont) {
		font = findFont;
		
	}


}

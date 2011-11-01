package at.almeida.mypanini.activity;

import android.os.Bundle;
import android.widget.TextView;

public class DuplicateItemsActivity extends StickerAbstractActivity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Duplicate items tab");
        setContentView(textview);
     
        alterTextViewFont(textview,FONT_MIA);
    }
}

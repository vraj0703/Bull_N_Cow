package raj.bullncow.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import raj.bullncow.App;
import raj.bullncow.R;

/**
 * Created by Vishal Raj on
 * 10/22/2015
 * 12:50 AM
 */
public class CTextView extends TextView {


    public CTextView(Context context) {
        super(context);
        setTFace(context, null);
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTFace(context, attrs);
    }

    public CTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTFace(context, attrs);
    }


    public void setTFace(Context context, AttributeSet attrs) {
        if (attrs == null) {
            this.setTypeface(App.getTypeface(1));
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CTextView);
        int p = a.getInt(R.styleable.CTextView_typeface, 1);
        this.setTypeface(App.getTypeface(p));
    }
}

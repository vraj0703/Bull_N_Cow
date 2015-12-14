package raj.bullncow.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import raj.bullncow.R;

public class ScoreListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final String[] nItems, sItems;

    public ScoreListAdapter(Activity c, String[] name, String[] score) {
        mInflater = c.getLayoutInflater();
        nItems = name;
        sItems = score;
    }

    public int getCount() {
        return nItems.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_score_list, null);
        }
        CTextView n, s;
        n = (CTextView) convertView.findViewById(R.id.name_score_list);
        s = (CTextView) convertView.findViewById(R.id.score_score_list);
        n.setText(nItems[position]);
        s.setText(sItems[position]);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return nItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

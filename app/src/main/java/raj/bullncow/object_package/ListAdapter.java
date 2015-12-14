package raj.bullncow.object_package;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import raj.bullncow.R;

/**
 * Created by Vishal Raj on 8/14/2015.
 */
public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ListArray items;

    public ListAdapter(Activity a, ListArray items) {
        inflater = a.getLayoutInflater();
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.getLength();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.layout_list_bnc, null);
        TextView guess = (TextView) convertView.findViewById(R.id.guess_bnc);
        TextView bull = (TextView) convertView.findViewById(R.id.bull_bnc);
        TextView cow = (TextView) convertView.findViewById(R.id.cow_bnc);

        listItem l = items.getListItem(position);

        guess.setText(l.getNumber());
        bull.setText(l.getBull() + "");
        cow.setText(l.getCow() + "");

        return convertView;
    }
}

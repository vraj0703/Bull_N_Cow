package raj.bullncow.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import raj.bullncow.R;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.ScoreListAdapter;

/**
 * Created by Vishal Raj on
 * 12/12/2015
 * 12:37 PM
 */
public class ViewHighScoreFragment extends Fragment {

    public static String ARG_SECTION_NUMBER = "Section";
    CTextView heading;
    ListView score;
    int position;

    public ViewHighScoreFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_highscore, container, false);
        heading = (CTextView) v.findViewById(R.id.heading_hs);
        score = (ListView) v.findViewById(R.id.list_score);
        heading.setText("Level " + (position + 4));

        String[] name_arr = new String[10];
        String[] score_arr = new String[10];

        SQLiteDatabase db1 = getActivity().openOrCreateDatabase("HighScore", getActivity().MODE_PRIVATE, null);
        Cursor c;
        String table = "level" + (position + 4);

        c = db1.rawQuery("SELECT * FROM " + table
                + " ORDER BY score ASC", null);

        int i;
        if (c.getCount() == 0) {
            for (i = 0; i < 10; i++) {
                name_arr[i] = "";
                score_arr[i] = "";
            }
        } else {
            c.moveToFirst();
            i = 0;
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(),i++) {
                name_arr[i] = c.getString(1);
                score_arr[i] = c.getString(2);
            }
            /*while (c.moveToNext()) {
                if (i == 0)
                    c.moveToPrevious();
                name_arr[i] = c.getString(1);
                score_arr[i] = c.getString(2);
                i++;
            }*/
        }
        ScoreListAdapter files = new ScoreListAdapter(getActivity(), name_arr,
                score_arr);
        score.setAdapter(files);
        score.setDividerHeight(0);

        return v;
    }
}

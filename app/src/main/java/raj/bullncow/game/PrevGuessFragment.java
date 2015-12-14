package raj.bullncow.game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import raj.bullncow.R;
import raj.bullncow.object_package.ListAdapter;
import raj.bullncow.object_package.ListArray;
import raj.bullncow.object_package.listItem;

/**
 * Created by Vishal Raj on 8/12/2015.
 */
@SuppressLint("ValidFragment")
public class PrevGuessFragment extends Fragment {
    ListView preGuess;
    ListArray items;
    ListAdapter adapter;

    @SuppressLint("ValidFragment")
    public PrevGuessFragment(ListArray items) {
        this.items = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_previos_guess, container, false);
        preGuess = (ListView) v.findViewById(R.id.previous_guess_list);
        adapter = new ListAdapter(getActivity(), items);
        preGuess.setAdapter(adapter);
        return v;
    }

    public void infoNewItemAdded(listItem new_item) {
        items.add(new_item);
        adapter.notifyDataSetChanged();
    }
}

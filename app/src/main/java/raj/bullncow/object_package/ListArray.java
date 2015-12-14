package raj.bullncow.object_package;

import java.util.ArrayList;

/**
 * Created by Vishal Raj on 8/14/2015.
 */
public class ListArray {

    ArrayList<listItem> preGuess;

    public ListArray() {
        preGuess = new ArrayList<listItem>();
    }


    public ArrayList<listItem> getPreGuess() {
        return preGuess;
    }

    public void add(listItem l) {
        preGuess.add(l);
    }

    public boolean contain(String guess) {
        for (listItem l : preGuess) {
            if (l.getNumber() != null && l.getNumber().contains(guess))
                return true;
        }
        return false;
    }

    public int getLength() {
        return preGuess.size();
    }

    public listItem getListItem(int position) {
        return preGuess.get(position);
    }
}

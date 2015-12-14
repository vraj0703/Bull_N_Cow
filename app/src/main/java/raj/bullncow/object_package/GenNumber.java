package raj.bullncow.object_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Vishal Raj on 8/12/2015.
 */
public class GenNumber {

    int[] number_array;
    int length;
    String number_String;
    int[] hash;

    public GenNumber(int level) {
        number_array = new int[level];
        length = level;
        hash = new int[10];
        generate_number();
        number_String = numberToString();
    }

    private void generate_number() {
        Arrays.fill(hash, 0);
        int i = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j = 1; j <= 9; j++)
            list.add(j - 1, j);
        Collections.shuffle(list);
        while (i < length) {
            hash[list.get(i)] = 1;
            number_array[i] = list.get(i);
            i++;
        }
    }


    public int number_at(int pos) {
        return number_array[pos];
    }

    public boolean is_present(int number) {
        if (hash[number] == 1)
            return true;
        return false;
    }

    public boolean number_pos_right(int number, int pos) {
        if (number_array[pos] == number)
            return true;
        return false;
    }

    public String numberToString() {
        String result = "";
        for (int i = 0; i < length; i++)
            result = result + number_array[i];
        return result;
    }

    public String getNumber_String() {
        return number_String;
    }


    public int getBull(String k) {
        int c = 0;
        for (int i = 0; i < k.length(); i++) {
            if (number_pos_right(k.charAt(i) - '0', i))
                c++;
        }
        return c;
    }

    public int getCow(String k) {
        int c = 0;
        for (int i = 0; i < k.length(); i++) {
            if (!number_pos_right(k.charAt(i) - '0', i) && hash[k.charAt(i) - '0'] == 1)
                c++;
        }
        return c;
    }
}

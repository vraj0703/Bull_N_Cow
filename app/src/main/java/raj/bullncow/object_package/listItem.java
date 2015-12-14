package raj.bullncow.object_package;

/**
 * Created by Vishal Raj on 8/12/2015.
 */
public class listItem {

    int bull, cow;
    String number;

    public listItem(int bull, int cow, String number) {
        this.bull = bull;
        this.cow = cow;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public int getBull() {
        return bull;
    }

    public int getCow() {
        return cow;
    }
}

package raj.bullncow.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

import raj.bullncow.App;

@SuppressLint("ViewConstructor")
public class CustomToast extends Toast {

    public CustomToast(Context context, CharSequence text) {
        super(context);
        Vibrator v = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        long pattern[] = new long[]{0, 200, 100, 200};
        if (App.isVibration())
            v.vibrate(pattern, -1);
        super.makeText(context, text, LENGTH_LONG).show();
    }

    public CustomToast(Context context, CharSequence text, int duration) {
        super(context);
        Vibrator v = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        long pattern[] = new long[]{0, 200, 100, 200};
        if (App.isVibration())
            v.vibrate(pattern, -1);
        super.makeText(context, text, duration).show();
    }
}

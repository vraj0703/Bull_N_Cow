package raj.bullncow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raj.bullncow.App;
import raj.bullncow.R;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.FlatEditText;
import raj.bullncow.util.SoundEffects;
import raj.bullncow.util.Vibration;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 3:53 AM
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    CTextView sound, vibration;
    public FlatEditText playerName;
    public boolean isSound, isVibration;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        sound = (CTextView) v.findViewById(R.id.sound);
        vibration = (CTextView) v.findViewById(R.id.viber);
        playerName = (FlatEditText) v.findViewById(R.id.nick);

        sound.setOnClickListener(this);
        vibration.setOnClickListener(this);

        isSound = App.isSound();
        isVibration = App.isVibration();

        setSoundNVibration();
        playerName.setText(App.getPlayerName());

        return v;
    }

    private void setSoundNVibration() {
        if (isSound)
            sound.setText("ON");
        else
            sound.setText("OFF");

        if (isVibration)
            vibration.setText("ON");
        else
            vibration.setText("OFF");
    }

    @Override
    public void onClick(View v) {
        //SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        switch (v.getId()) {
            case R.id.sound:
                isSound = !isSound;
                break;
            case R.id.viber:
                isVibration = !isVibration;
                break;
        }
        //play sound when clicked is sound toggle button and arg1 is true
        if (v.getId() == R.id.sound && isSound) {
            SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK, isSound);
        }
        //vibrate when vibration toggle button is clicked and arg1 is true
        if (v.getId() == R.id.viber && isVibration) {
            Vibration.vibrate(getActivity(), 200, isVibration);
        }

        setSoundNVibration();
    }
}

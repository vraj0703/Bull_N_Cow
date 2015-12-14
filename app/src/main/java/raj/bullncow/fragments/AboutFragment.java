package raj.bullncow.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andexert.library.RippleView;

import raj.bullncow.R;
import raj.bullncow.util.CTextView;
import raj.bullncow.util.CustomToast;
import raj.bullncow.util.SoundEffects;

/**
 * Created by Vishal Raj on
 * 12/11/2015
 * 3:53 AM
 */
public class AboutFragment extends Fragment {

    CTextView heading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_lots, container, false);
        heading = (CTextView) v.findViewById(R.id.heading);
        heading.setText("About Us");
        ThisFragment thisFragment = new ThisFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lots_container, thisFragment).commit();
        return v;
    }

    public class ThisFragment extends Fragment implements RippleView.OnRippleCompleteListener, View.OnClickListener {

        RippleView share, fb, mail, rate, git;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_about, container, false);
            share = (RippleView) v.findViewById(R.id.share);
            fb = (RippleView) v.findViewById(R.id.fb);
            mail = (RippleView) v.findViewById(R.id.mail);
            rate = (RippleView) v.findViewById(R.id.four);
            git = (RippleView) v.findViewById(R.id.git);

            share.setOnRippleCompleteListener(this);
            fb.setOnRippleCompleteListener(this);
            mail.setOnRippleCompleteListener(this);
            rate.setOnRippleCompleteListener(this);
            git.setOnRippleCompleteListener(this);

            share.setOnClickListener(this);
            fb.setOnClickListener(this);
            mail.setOnClickListener(this);
            rate.setOnClickListener(this);
            git.setOnClickListener(this);

            return v;
        }

        public Intent getOpenFacebookIntent(Context context) {

            try {
                context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/100001598202010"));
            } catch (Exception e) {
                return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/vraj0703"));
            }
        }

        @Override
        public void onComplete(RippleView v) {
            switch (v.getId()) {
                case R.id.share:
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "Let me recommend you this code breaking mind game\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=raj.bullncow\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                    break;
                case R.id.fb:
                    startActivity(getOpenFacebookIntent(getActivity()));
                    break;
                case R.id.mail:
                    Intent iMail = new Intent(Intent.ACTION_SEND);
                    iMail.setType("message/rfc822");
                    iMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"vraj0703@gmail.com"});
                    iMail.putExtra(Intent.EXTRA_SUBJECT, "bull n cow appreciation mail");
                    try {
                        startActivity(Intent.createChooser(iMail, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        new CustomToast(getActivity(), "There are no email clients installed.");
                    }
                    break;
                case R.id.four:
                    Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    // To count with Play market backstack, After pressing back button,
                    // to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                    }
                    break;
                case R.id.git:
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/vraj0703/Bull_N_Cow"));
                    startActivity(browserIntent);
                    break;
            }

        }

        @Override
        public void onClick(View v) {
            SoundEffects.getInstance().playSound(SoundEffects.SOUND_CLICK);
        }
    }
}

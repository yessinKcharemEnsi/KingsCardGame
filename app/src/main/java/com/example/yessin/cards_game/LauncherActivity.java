package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LauncherActivity extends AppCompatActivity {


    private static final String TAG = "LauncherActivity";
    TextView gameName;

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameName=findViewById(R.id.gameName);

        final LottieAnimationView kingLaughing = findViewById(R.id.lottieAnimation);

        ObjectAnimator textOut = ObjectAnimator.ofFloat(gameName,"alpha",1,0);
        textOut.setDuration(0);

        textOut.start();

        ObjectAnimator kingFaderIn = ObjectAnimator.ofFloat(kingLaughing,"alpha",0,1);
        kingFaderIn.setDuration(500);

        final ObjectAnimator kingFaderOut = ObjectAnimator.ofFloat(kingLaughing,"alpha",1,0);
        kingFaderOut.setDuration(1500);


        final ObjectAnimator textFaderIn = ObjectAnimator.ofFloat(gameName,"alpha",0,1);
        textFaderIn.setDuration(500);


        final ObjectAnimator textFaderOut = ObjectAnimator.ofFloat(gameName,"alpha",1,0);
        textFaderOut.setDuration(1500);

        final MediaPlayer kingLaugh = MediaPlayer.create(getApplicationContext(),R.raw.king_laughter_01);

        final AnimatorSet faderOut = new AnimatorSet();
        faderOut.playTogether(kingFaderOut,textFaderOut);

        kingFaderOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        final ObjectAnimator sleep= Game.sleeper(200);
        sleep.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                textFaderOut.start();
                kingFaderOut.start();
            }
        });

        kingLaughing.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                kingLaugh.start();

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                sleep.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        kingLaughing.setSpeed(1.2f);
        kingFaderIn.start();
        kingLaughing.playAnimation();
        kingFaderIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                YoYo.with(Techniques.DropOut)
                        .duration(500)
                        .playOn(gameName);
            }
        });

















    }
}

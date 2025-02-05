package com.example.yessin.cards_game;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class LeaderBoard extends AppCompatActivity {

    private static final String TAG = "Leaderboard";

    @Override
    public void onBackPressed (){

    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Log.d(TAG, "fuck: focus changed ");
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
        if(Settings.sound) {
            Songs.gameSong.stop();
            Songs.gameSong.release();
        }
        Log.d(TAG, "fuck : in results");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
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

        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.scores_activity);
        mediaPlayer.start();

        final LottieAnimationView starsCenter = findViewById(R.id.lottieAnimationStarsCenter);
        starsCenter.playAnimation();
        final LottieAnimationView stars = findViewById(R.id.lottieAnimationStars);
        stars.playAnimation();
        final LottieAnimationView stars2 = findViewById(R.id.lottieAnimationStars2);
        stars2.playAnimation();
        final LottieAnimationView stars3 = findViewById(R.id.lottieAnimationStars3);
        stars3.playAnimation();
        final LottieAnimationView stars4 = findViewById(R.id.lottieAnimationStars4);
        stars4.playAnimation();

        final ObjectAnimator starsCenterFader = ObjectAnimator.ofFloat(starsCenter,"alpha",1,0);
        starsCenterFader.setDuration(1000);

        final ObjectAnimator starsFader = ObjectAnimator.ofFloat(stars,"alpha",1,0);
        starsFader.setDuration(1000);

        final ObjectAnimator stars2Fader = ObjectAnimator.ofFloat(stars2,"alpha",1,0);
        stars2Fader.setDuration(1000);

        final ObjectAnimator stars3Fader = ObjectAnimator.ofFloat(stars3,"alpha",1,0);
        starsFader.setDuration(1000);

        final ObjectAnimator stars4Fader = ObjectAnimator.ofFloat(stars4,"alpha",1,0);
        stars2Fader.setDuration(1000);

        starsCenterFader.setStartDelay(3000);
        starsFader.setStartDelay(2000);
        stars2Fader.setStartDelay(2000);
        stars3Fader.setStartDelay(2500);
        stars4Fader.setStartDelay(2800);

        starsCenterFader.start();
        starsFader.start();
        stars2Fader.start();
        stars3Fader.start();
        stars4Fader.start();





        SetPlayersNames();

        Button mainMenu = (Button)findViewById(R.id.mainMenu);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                BackToMainMenu();
            }
        });

        final ImageView goldMedial = (ImageView) findViewById(R.id.medal1);
        final ImageView silverMedial = (ImageView) findViewById(R.id.medal2);
        final ImageView bronzeMedial = (ImageView) findViewById(R.id.medal3);

        goldMedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FlipInY )
                        .duration(200)
                        .playOn(goldMedial);
            }
        });

        silverMedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Flash)
                        .duration(500)
                        .playOn(silverMedial);
            }
        });

        bronzeMedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Flash)
                        .duration(200)
                        .playOn(bronzeMedial);
            }
        });



    }
    void SetPlayersNames(){
        ArrayList<String> playerNames = getIntent().getStringArrayListExtra("names");


        TextView player1 = (TextView)findViewById(R.id.playerName1);
        TextView player2 = (TextView)findViewById(R.id.playerName2);
        TextView player3 = (TextView)findViewById(R.id.playerName3);
        TextView player4 = (TextView)findViewById(R.id.playerName4);

        player1.setText(playerNames.get(0));
        player2.setText(playerNames.get(1));
        player3.setText(playerNames.get(2));
        player4.setText(playerNames.get(3));

        if(playerNames.get(0).equals("north"))
            player1.setText(R.string.north);

        else if(playerNames.get(0).equals("west"))
            player1.setText(R.string.west);

        else if(playerNames.get(0).equals("south"))
            player1.setText("you");

        else
            player1.setText(R.string.east);




        if(playerNames.get(1).equals("north"))
            player2.setText(R.string.north);

        else if(playerNames.get(1).equals("west"))
            player2.setText(R.string.west);

        else if(playerNames.get(1).equals("south"))
            player2.setText("you");

        else
            player2.setText(R.string.east);



        if(playerNames.get(2).equals("north"))
            player3.setText(R.string.north);

        else if(playerNames.get(2).equals("west"))
            player3.setText(R.string.west);

        else if(playerNames.get(2).equals("south"))
            player3.setText("you");

        else
            player3.setText(R.string.east);



        if(playerNames.get(3).equals("north"))
            player4.setText(R.string.north);

        else if(playerNames.get(3).equals("west"))
            player4.setText(R.string.west);

        else if(playerNames.get(3).equals("south"))
            player4.setText("you");

        else
            player4.setText(R.string.east);





    }

    void BackToMainMenu(){
        Intent back = new Intent(this,MenuActivity.class);
        startActivity(back);
    }
}

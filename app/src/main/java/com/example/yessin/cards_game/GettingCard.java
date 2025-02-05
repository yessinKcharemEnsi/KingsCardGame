package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class GettingCard extends AppCompatActivity {
    ArrayList<Card> cards = new ArrayList<>();
    int choosenCard = -1;

    private static final String TAG = "GettingCard2";

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
        setContentView(R.layout.activity_getting_card);
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

        ConstraintLayout mainBoard = findViewById(R.id.mainBoard);

        if (Settings.cardBack == R.drawable.blue_back_up_low)
            mainBoard.setBackgroundResource(R.drawable.blue_comics_one);
        else if(Settings.cardBack == R.drawable.purple_back_up_low)
            mainBoard.setBackgroundResource(R.drawable.purple_comics);
        else
            mainBoard.setBackgroundResource(R.drawable.gray_comics);

        getPlayerCards();
        AddCardsToBoard();

    }




    void getPlayerCards(){
        ArrayList<Integer> values = getIntent().getExtras().getIntegerArrayList("values");
        ArrayList<Integer> suits = getIntent().getExtras().getIntegerArrayList("suits");
        String[] cardSuits = {"hearts","clubs","spades","diamonds"};
        // prepare the player cards from the values and the suits recieved from the main activity
        for (int i =0;i<values.size();i++) {
            cards.add(new Card(this, cardSuits[suits.get(i)], values.get(i)));
            cards.get(i).increaseCardResolution();
            cards.get(i).back.setTag(cards.get(i));
            cards.get(i).face.setTag(i);
            cards.get(i).back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChooseCard((Card)view.getTag());
                }
            });
        }
    }
    // go backToMenu to main acitivty
    // use getIntent().getExtra().getInt("Card") in the main activity to receive the id of the chosen card in the deck of the other player
    void BackToGamePlay(){
        String player=null;
        try {
            player = getIntent().getExtras().getString("sittingPosition");
        }catch (Exception e) {
        }
        Intent play = new Intent(getApplicationContext(),GamePlay.class);
        play.putExtra("card",choosenCard);
        play.putExtra("sittingPosition",player);
        setResult(1,play);
        finish();
    }
    void ChooseCard(final Card myCard){
        if(choosenCard == -1) {
            AnimatorSet choose = new AnimatorSet();
            ObjectAnimator sleep =ObjectAnimator.ofFloat(null,"translationX",1f);
            sleep.setDuration(1300);
            choosenCard = (int) myCard.face.getTag();
            AnimatorSet choose2 = new AnimatorSet();
            choose2.playTogether(myCard.setXtranslator(-myCard.positionX, 500),myCard.zoom(1.75f,500));
            AnimatorSet flipCard = new AnimatorSet();
            flipCard=myCard.flip(400);
            flipCard.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if(myCard.value==10){
                        MediaPlayer kingLaughter = MediaPlayer.create(getApplicationContext(),R.raw.king_laughter_03);
                        kingLaughter.start();
                    }

                }
            });
            choose.playSequentially(choose2,flipCard,sleep);
            choose.start();
            choose.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    BackToGamePlay();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            cards.remove(myCard);
            // make all other cards invisible
            for (Card c : cards) c.fadeOut(200).start();
            choose.start();
        }
    }
    void AddCardsToBoard(){
        ConstraintLayout table = findViewById(R.id.mainBoard);
        ArrayList<Animator> animators = new ArrayList<>();
        Collections.shuffle(cards);
        for(int i=0;i<cards.size();i++){
            cards.get(i).zoom(1.3f,0).start();
            cards.get(i).addToTable(table);
            animators.add(cards.get(i).setXtranslator(i*10 - (cards.size()-1)*5,500));
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.setStartDelay(500);
        animatorSet.start();
    }
}

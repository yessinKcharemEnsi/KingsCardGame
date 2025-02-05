package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;



public class Game {
    public Activity activity;
    private static final String TAG = "Game";
    public static ConstraintLayout table;

    public ArrayList<Card> fullDeck;  //all the ressources of cards

    public Point screenSize = new Point(); //screenSize.x = width

    public ArrayList<Player> players = new ArrayList<>();

    public ArrayList<String> winners = new ArrayList<>();

    public Button endTurn,casse;

    public static TextView board ;

    public static boolean firstTourEnds = false;

    MediaPlayer gameSound,menuSound;





    public Game (Activity activity) {
        this.activity=activity;
        table = activity.findViewById(R.id.constraintLayout);
        board = activity.findViewById(R.id.board);
        //table.setBackgroundColor(Color.parseColor("#1FAB89"));
        fullDeck = new ArrayList<>();
        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getSize(screenSize);
    //    gameSound=MediaPlayer.create(activity,R.raw.welcome);
       // menuSound=MediaPlayer.create(activity,R.raw.scores_activity);
        endTurn = activity.findViewById(R.id.endTurn);
        casse = activity.findViewById(R.id.casse);


        for (int i=1;i<=9;i++) {
            fullDeck.add(new Card(activity, "spades", i));
            fullDeck.add(new Card(activity, "hearts", i));
            fullDeck.add(new Card(activity, "clubs", i));
            fullDeck.add(new Card(activity, "diamonds", i));
        }

        fullDeck.add(new Card(activity,"diamonds",10));

        Collections.shuffle(fullDeck);

        for (int i=0;i<fullDeck.size();i++)
            fullDeck.get(i).ordre =i;

    }

    public void addPlayerToGame (Player player) {
        for(Player p : players) {
            if (p == player) {
                return;
            }
        }
        players.add(player);
        return;
    }

    public Player onHisRight (Player player){

        int index = players.indexOf(player);
        return players.get((index+1)%players.size());


    }

    public Player onHisLeft (Player player){

        Collections.reverse(players);
        Player next = onHisRight(player);
        Collections.reverse(players);
        return  next;
    }


    public AnimatorSet northCardsDistributer(Player north) {
        ArrayList<Animator> fadeInDeck = new ArrayList<>();

        for (int i=0;i<=8;i++) {
            fullDeck.get(i).addToTable(table, "south");
            fadeInDeck.add(fullDeck.get(i).fadeIn(1000));
        }


        ArrayList<Animator> animators0,animators1,animators2,animators3;
        animators0 = new ArrayList<>();
        animators1= new ArrayList<>();
        animators2 = new ArrayList<>();
        animators3 = new ArrayList<>();

        float marge=0;

        for (int i=0;i<=8;i++) {
            north.cards.add(fullDeck.get(i));

            animators0.add(north.cards.get(i).setYtranslator(-40,200));
            animators1.add(north.cards.get(i).setXtranslator(16,200));
            animators2.add(north.cards.get(i).setXtranslator(-marge,300));
            marge+=4f;
        }

        //    for (int i=9;i<fullDeck.size();i++) {
        //  animators3.add(fullDeck.get(9).rotate(-90,200));
        //    }

        AnimatorSet fader = new AnimatorSet();

        fader.playTogether(fadeInDeck);



        AnimatorSet distributer = new AnimatorSet();
        distributer.playSequentially(animators0);

        AnimatorSet translator1 = new AnimatorSet();
        translator1.playTogether(animators1);

        AnimatorSet translator2 = new AnimatorSet();
        translator2.playTogether(animators2);

        AnimatorSet rotator = new AnimatorSet();
        rotator.playTogether(animators3);

        AnimatorSet animatorSetForAll = new AnimatorSet();
        animatorSetForAll.playSequentially(fader,distributer,translator1,translator2,rotator);

        animatorSetForAll.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (int i=9;i<=17;i++) {
                    fullDeck.get(i).addToTable(table, "east");
                }

            }
        });

        return  animatorSetForAll;

    }

    public AnimatorSet westCardsDistributer(Player west) {

        ArrayList<Animator> fadeInDeck = new ArrayList<>();

        for (int i=9;i<=17;i++) {
            // fullDeck.get(i).addToTable(table, "east");
            fadeInDeck.add(fullDeck.get(i).fadeIn(1000));
        }


        ArrayList<Animator> animators0,animators1,animators2,animators3;

        animators0 = new ArrayList<>();
        animators1= new ArrayList<>();
        animators2 = new ArrayList<>();
        animators3 = new ArrayList<>();
        float marge=0;

        for (int i=9;i<=17;i++) {
            west.cards.add(fullDeck.get(i));
            animators0.add(west.cards.get(i-9).setXtranslator(-45,200));
            animators1.add(west.cards.get(i-9).setYtranslator(-20.8f,200));
            animators2.add(west.cards.get(i-9).setYtranslator(-marge,300));
            marge-=5.2;
        }

    /*    for (int i=18;i<fullDeck.size();i++) {
            animators3.add(fullDeck.get(i).rotate(-90,200));
        }*/

        AnimatorSet fader = new AnimatorSet();
        fader.playTogether(fadeInDeck);


        AnimatorSet distributer = new AnimatorSet();
        distributer.playSequentially(animators0);



        AnimatorSet translator1 = new AnimatorSet();
        translator1.playTogether(animators1);

        AnimatorSet translator2 = new AnimatorSet();
        translator2.playTogether(animators2);

        AnimatorSet rotator = new AnimatorSet();
        rotator.playTogether(animators3);

        AnimatorSet animatorSetForAll = new AnimatorSet();
        animatorSetForAll.playSequentially(fader,distributer,translator1,translator2,rotator);

        animatorSetForAll.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (int i=18;i<=27;i++) {
                    fullDeck.get(i).addToTable(table, "north");

                }

            }
        });



        return  animatorSetForAll;

    }

    public AnimatorSet southCardsDistributer(Player south) {
        ArrayList<Animator> fadeInDeck = new ArrayList<>();

        for (int i=18;i<=27;i++) {
            //   fullDeck.get(i).addToTable(table, "north");
            fadeInDeck.add(fullDeck.get(i).fadeIn(1000));
        }
        ArrayList<Animator> animators0,animators1,animators2,animators3,rotators;

        animators0 = new ArrayList<>();
        animators1= new ArrayList<>();
        animators2 = new ArrayList<>();
        animators3 = new ArrayList<>();
        //   rotators = new ArrayList<>();
        float marge=0;

        for (int i=18;i<=27;i++) {
            south.cards.add(fullDeck.get(i));
            animators0.add(south.cards.get(i-18).setYtranslator(40,200));
            animators1.add(south.cards.get(i-18).setXtranslator(-20.25f,200));
            animators2.add(south.cards.get(i-18).setXtranslator(marge,300));
            //   rotators.add(south.cards.get(i-18).flip(200));

            marge+=4.5;
        }
  /*      for (int i=28;i<fullDeck.size();i++) {
            animators3.add(fullDeck.get(i).rotate(-90,200));
        }*/
        AnimatorSet fader = new AnimatorSet();
        fader.playTogether(fadeInDeck);



        AnimatorSet translator0 = new AnimatorSet();
        translator0.playSequentially(animators0);


        AnimatorSet translator1 = new AnimatorSet();
        translator1.playTogether(animators1);

        AnimatorSet translator2 = new AnimatorSet();
        translator2.playTogether(animators2);

   /*     AnimatorSet rotator = new AnimatorSet();
        rotator.playSequentially(rotators);*/



        AnimatorSet deckRotator = new AnimatorSet();
        deckRotator.playTogether(animators3);



        AnimatorSet animatorSetForAll = new AnimatorSet();
        animatorSetForAll.playSequentially(fader,translator0,translator1,translator2/*,rotator*/,deckRotator);

        animatorSetForAll.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (int i=28;i<=36;i++) {
                    fullDeck.get(i).addToTable(table, "west");
                }
            }
        });





        return  animatorSetForAll;


    }

    public AnimatorSet eastCardsDistributer(Player east) {

        ArrayList<Animator> fadeInDeck = new ArrayList<>();

        for (int i=28;i<=36;i++) {
            //  fullDeck.get(i).addToTable(table, "west");
            fadeInDeck.add(fullDeck.get(i).fadeIn(1000));
        }
        ArrayList<Animator> animators0,animators1,animators2,animators3;

        animators0 = new ArrayList<>();
        animators1= new ArrayList<>();
        animators2 = new ArrayList<>();
        animators3 = new ArrayList<>();
        float marge=0;

        for (int i=28;i<=36;i++) {
            east.cards.add(fullDeck.get(i));
            animators0.add(east.cards.get(i-28).setXtranslator(45,200));
            animators1.add(east.cards.get(i-28).setYtranslator(20.8f,200));
            animators2.add(east.cards.get(i-28).setYtranslator(marge,300));
            marge-=5.2f;
        }

        //the extra card remains

   /*     for (int i=18;i<fullDeck.size();i++) {
            animators3.add(fullDeck.get(i).rotate(-90,200));
        }*/


        AnimatorSet fader = new AnimatorSet();
        fader.playTogether(fadeInDeck);

        AnimatorSet distributer = new AnimatorSet();
        distributer.playSequentially(animators0);



        AnimatorSet translator1 = new AnimatorSet();
        translator1.playTogether(animators1);

        AnimatorSet translator2 = new AnimatorSet();
        translator2.playTogether(animators2);

        AnimatorSet rotator = new AnimatorSet();
        rotator.playTogether(animators3);

        AnimatorSet animatorSetForAll = new AnimatorSet();
        animatorSetForAll.playSequentially(fader,distributer,translator1,translator2,rotator);



        return  animatorSetForAll;

    }

    public AnimatorSet distributeCards(Player north, Player west, Player south, Player east) {
        AnimatorSet distributer = new AnimatorSet();
        distributer.playSequentially(northCardsDistributer(north),westCardsDistributer(west),southCardsDistributer(south),eastCardsDistributer(east));
        return  distributer;
    }

    public AnimatorSet cardsBack(Player north,Player west,Player manual,Player east){
        //north backToMenu
        ArrayList<Animator> forNorth = new ArrayList<>();

        for(Card card : north.cards) {
            forNorth.add(card.setYtranslator(-7,500));
        }

        AnimatorSet northAnimation = new AnimatorSet();
        northAnimation.playTogether(forNorth);

        //south backToMenu
        ArrayList<Animator> forManual = new ArrayList<>();

        for(Card card : manual.cards) {
            forManual.add(card.setYtranslator(7,500));
        }

        AnimatorSet manualAnimation = new AnimatorSet();
        manualAnimation.playTogether(forManual);

        //west backToMenu
        ArrayList<Animator> forWest = new ArrayList<>();

        for(Card card : west.cards) {
            forWest.add(card.setXtranslator(-7,500));
        }

        AnimatorSet westAnimation = new AnimatorSet();
        westAnimation.playTogether(forWest);

        //east backToMenu
        ArrayList<Animator> forEast = new ArrayList<>();

        for(Card card : east.cards) {
            forEast.add(card.setXtranslator(7,500));
        }

        AnimatorSet eastAnimation = new AnimatorSet();
        eastAnimation.playTogether(forEast);





        AnimatorSet forAll = new AnimatorSet();
        forAll.playTogether(northAnimation,eastAnimation,manualAnimation,westAnimation);

        return forAll;


    }

    public static  void  removeCard(Card card) {
        table.removeView(card.face);
        table.removeView(card.back);
    }

    public static AnimatorSet setBoradText(final String msg, int durationFadeIn , int durationSleep , int DurationFadeOut, final int fontSize) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(board,"alpha",0,1);
        fadeIn.setDuration(durationFadeIn);
        fadeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                board.setTextSize(fontSize);
                if(msg.equals("north"))
                    board.setText(R.string.north_turn);
                else if(msg.equals("west"))
                    board.setText(R.string.west_turn);
                else if(msg.equals("east"))
                    board.setText(R.string.east_turn);
                else if(msg.equals("south"))
                    board.setText(R.string.south_turn);
                else
                    board.setText(R.string.game_starts_now);

            }
        });

        ObjectAnimator sleep = Game.sleeper(durationSleep);

        final ObjectAnimator fadeOut = ObjectAnimator.ofFloat(board,"alpha",1,0);
        fadeOut.setDuration(durationFadeIn);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                board.setText("");
            }
        });

        AnimatorSet writer = new AnimatorSet();
        writer.playSequentially(fadeIn,sleep);
        writer.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fadeOut.start();
            }
        });

        return writer;


    }






    public AnimatorSet autoPlayersTurn () {


        boolean gameEnds = false;
        ArrayList<Animator> animators = new ArrayList<>();

        ArrayList<Player> aux = new ArrayList<>();
        for(Player p : players) {
            if(p.sittingPosition != "south")
                aux.add(p);
        }

        for(Player p : aux) {
            if(players.size()> 1 && players.indexOf(p) != -1 ) {
                animators.add(p.play(onHisLeft(p)));
                if (onHisLeft(p).cards.size() == 0) {
                    winners.add(onHisLeft(p).sittingPosition);
                    players.remove(onHisLeft(p));


                }
                if (p.cards.size() == 0) {
                    Log.d(TAG, "fuck:  " + p.sittingPosition + " won");
                    winners.add(p.sittingPosition);
                    players.remove(p);

                }
            }

        }


        if(players.size()<=1) {
            if( ! players.get(0).sittingPosition.equals("south")) {
                animators.add(players.get(0).cards.get(0).flip(200));
                animators.add(sleeper(1000));
                winners.add(players.get(0).sittingPosition);
                gameEnds=true;
            }

        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);


        final boolean finalGameEnds = gameEnds;
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(finalGameEnds){
                    Intent intent = new Intent(activity,LeaderBoard.class);
                    intent.putExtra("names",winners);
                    activity.startActivity(intent);

                }

            }
        });


        return animatorSet;
    }

    public static ObjectAnimator sleeper(int time) {
        ObjectAnimator sleeper = ObjectAnimator.ofFloat(null,"alpha",1,1);
        sleeper.setDuration(time);
        return sleeper;
    }

}

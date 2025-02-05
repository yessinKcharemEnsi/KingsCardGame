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
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class GamePlay extends AppCompatActivity {

    Game game;
    int cardId;
    Player autoPlayer1, autoPlayer2, autoPlayer4, manualPlayer;
    private static final String TAG = "GamePlay";




    Button casse, endTurn,reveal;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cardId = data.getExtras().getInt("card");
        String playerId = data.getExtras().getString("sittingPosition");



        YoYo.with(Techniques.FadeInUp)
                .duration(1000)
                .playOn(casse);

        YoYo.with(Techniques.FadeInUp)
                .duration(1000)
                .playOn(endTurn);
        casse.setEnabled(true);
        endTurn.setEnabled(true);

        AnimatorSet animatorSet ;

        if (playerId.equals("north")) {
            animatorSet = manualPlayer.getCard2(autoPlayer1, cardId);
        } else if (playerId.equals("west"))
            animatorSet = manualPlayer.getCard2(autoPlayer2, cardId);
        else
            animatorSet = manualPlayer.getCard2(autoPlayer4, cardId);


        animatorSet.start();


        if(game.onHisLeft(manualPlayer).cards.size()==0) {
            game.winners.add(game.onHisLeft(manualPlayer).sittingPosition);
            game.players.remove(game.onHisLeft(manualPlayer));
        }







    }


    @Override
    public void onBackPressed (){

    }

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
        setContentView(R.layout.activity_manual_add);
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

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundResource(Settings.table);


        casse = (Button) findViewById(R.id.casse);
        endTurn = (Button) findViewById(R.id.endTurn);
        // reveal = findViewById(R.id.button);







        Songs.gameSong= MediaPlayer.create(getApplicationContext(),R.raw.jeuderoisong);
        if(Settings.sound)
            Songs.gameSong.start();





        game = new Game(this);
        autoPlayer1 = new AutoPlayer(this, "north");
        autoPlayer2 = new AutoPlayer(this, "west");
        manualPlayer = new ManualPlayer(this);
        autoPlayer4 = new AutoPlayer(this, "east");




        game.addPlayerToGame(autoPlayer4);
        game.addPlayerToGame(autoPlayer1);
        game.addPlayerToGame(autoPlayer2);
        game.addPlayerToGame(manualPlayer);

        YoYo.with(Techniques.FadeOutDown)
                .duration(200)
                .playOn(casse);

        YoYo.with(Techniques.FadeOutDown)
                .duration(200)
                .playOn(endTurn);


        casse.setEnabled(false);
        endTurn.setEnabled(false);

















        //       game.gameSound.start();





        final ObjectAnimator sleeper = ObjectAnimator.ofFloat(null,"alpha",1,1);
        sleeper.setDuration(200);

        final Thread manualPlayerPlay = new Thread(new Runnable() {
            @Override
            public void run() {
                //getUp
                ArrayList<Animator> getDown = new ArrayList<>();


                for (Card card : manualPlayer.cards) {
                    getDown.add(card.setYtranslator(-7, 500));
                }


                AnimatorSet get = new AnimatorSet();
                get.playTogether(getDown);

                get.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        manualPlayer.getCard(game.onHisLeft(manualPlayer));
                    }
                });
                get.start();

            }
        });



        Thread distributeCardsAndStartGame = new Thread(new Runnable() {
            @Override
            public void run() {
                AnimatorSet distributer ;


                distributer=game.distributeCards(autoPlayer1,autoPlayer2,manualPlayer,autoPlayer4);


                ArrayList<Animator> animators = new ArrayList<>();
                for(Card card : manualPlayer.cards)
                    animators.add(card.flip(200));

                final AnimatorSet animatorSet =new AnimatorSet();
                animatorSet.playSequentially(animators);
                AnimatorSet onBoard = Game.setBoradText("game starts now",500,400,500,30);
                AnimatorSet anim = new AnimatorSet();

                AnimatorSet playersGetCards = game.cardsBack(autoPlayer1,autoPlayer2,manualPlayer,autoPlayer4);

                anim.playSequentially(distributer,onBoard,playersGetCards,animatorSet,sleeper);

                anim.start();

                final AnimatorSet[] animatorSet1 = new AnimatorSet[1];


                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //  manualPlayerPlay.run();
                        animatorSet1[0] =game.autoPlayersTurn();

                        animatorSet1[0].addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {

                                super.onAnimationEnd(animation);

                                AnimatorSet onBord = Game.setBoradText(manualPlayer.sittingPosition,400,400,400,40);
                                onBord.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        manualPlayerPlay.run();
                                        if(game.players.size()==1){
                                            game.winners.add(manualPlayer.sittingPosition);

                                        }

                                    }
                                });
                                onBord.start();
                                //    manualPlayerPlay.run();

                            }
                        });

                        animatorSet1[0].start();



                    }
                });
            }
        });











        distributeCardsAndStartGame.run();




















        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Game.firstTourEnds=true;

                YoYo.with(Techniques.SlideOutDown)
                        .duration(500)
                        .playOn(casse);

                YoYo.with(Techniques.SlideOutDown)
                        .duration(500)
                        .playOn(endTurn);

                casse.setEnabled(false);
                endTurn.setEnabled(false);

                if (manualPlayer.cards.size()==0) {
                    game.winners.add(manualPlayer.sittingPosition);
                    game.players.remove(manualPlayer);

                }
                else if(game.players.size()==1 && game.players.get(0).sittingPosition=="south") {

                    Game.sleeper(1000).start();

                    game.winners.add(manualPlayer.sittingPosition);
                    game.gameSound.stop();
                    game.gameSound.release();
                    Intent intent = new Intent(getApplicationContext(),LeaderBoard.class);
                    intent.putExtra("names",game.winners);
                    startActivity(intent);

                }
                else {
                    AnimatorSet fixer = manualPlayer.fixDeck();
                    final ArrayList<Animator> getDown = new ArrayList<>();

                    for (Card card : manualPlayer.cards) {
                        getDown.add(card.setYtranslator(7, 500));
                    }
                    final AnimatorSet down = new AnimatorSet();
                    down.playTogether(getDown);
                    fixer.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            down.start();
                        }
                    });
                    fixer.start();
                }

                AnimatorSet autoPlayersTurn = new AnimatorSet();




                autoPlayersTurn=game.autoPlayersTurn();

                autoPlayersTurn.start();

                final AnimatorSet finalAutoPlayersTurn = autoPlayersTurn;
                autoPlayersTurn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if(manualPlayer.cards.size() != 0) {
                            if(game.players.size()>1) {
                                if(!Game.firstTourEnds) {
                                    AnimatorSet onBord = Game.setBoradText(manualPlayer.sittingPosition, 400, 100, 400, 40);
                                    onBord.addListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            manualPlayerPlay.run();

                                        }
                                    });
                                    onBord.start();
                                }
                                else
                                    manualPlayerPlay.run();

                            }

                            else {
                                Game.sleeper(1000).start();
                                game.winners.add(manualPlayer.sittingPosition);
                                Intent intent = new Intent(getApplicationContext(),LeaderBoard.class);
                                intent.putExtra("names",game.winners);
                                startActivity(intent);
                            }
                        }
                        else {
                            ArrayList<Animator> autoGame = new ArrayList<>();
                            while (game.players.size()>1)
                                autoGame.add(game.autoPlayersTurn());

                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.playSequentially(autoGame);
                            animatorSet.start();
                        }
                    }

                });












            }
        });


        casse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                manualPlayer.throwCards();



            }
        });



    }
}

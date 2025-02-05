package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yessin on 20/09/2020.
 */

public abstract class Player {

    public Activity activity;
    public String sittingPosition ; // north , east , south or west
    public ArrayList<Card> cards;
    TextView textView,textView2;

    public Player(Activity activity,String sittingPosition){
        this.activity=activity;
        cards = new ArrayList<>();
        this.sittingPosition=sittingPosition; //north , east , west ,south

    }

    public abstract AnimatorSet pickCard(Player player); //for auto players

    public AnimatorSet fixCardsAfterPull (int index) {
        //index is the index of the fathwa of the card that will be pulled
        ArrayList<Animator> fixers = new ArrayList<>();
        AnimatorSet player = new AnimatorSet();
        int firstHalf=index,secondHalf=(cards.size()-1)-index;
        if (sittingPosition=="west" || sittingPosition=="east") {

            if(firstHalf>secondHalf){
                if(sittingPosition=="west")
                    for(int i=index+1;i<cards.size();i++){
                        fixers.add(cards.get(i).setYtranslator(-5.2f,300));
                    }
                else
                    for(int i=index+1;i<cards.size();i++){
                        fixers.add(cards.get(i).setYtranslator(5.2f,300));
                    }

            }
            else {
                if(sittingPosition=="west")
                    for (int i = 0; i < index; i++) {
                        fixers.add(cards.get(i).setYtranslator(5.2f, 300));
                    }
                else
                    for (int i = 0; i < index; i++) {
                        fixers.add(cards.get(i).setYtranslator(-5.2f, 300));
                    }

            }



            player.playTogether(fixers);

        }

        else {

            if(firstHalf>secondHalf){
                if(sittingPosition=="north")
                    for(int i=index+1;i<cards.size();i++){
                        fixers.add(cards.get(i).setXtranslator(4f,300));
                    }
                else
                    for(int i=index+1;i<cards.size();i++){
                        fixers.add(cards.get(i).setXtranslator(-4.5f,300));
                    }

            }
            else {
                if(sittingPosition=="north")
                    for (int i = 0; i < index; i++) {
                        fixers.add(cards.get(i).setXtranslator(-4f, 300));
                    }
                else
                    for (int i = 0; i < index; i++) {
                        fixers.add(cards.get(i).setXtranslator(4.5f, 300));
                    }

            }



            player.playTogether(fixers);

        }
        return player;



    } //(just animation)

    public abstract AnimatorSet casse();


    public abstract void getCard(Player player); //for manual player
    public abstract AnimatorSet getCard2(Player player,int intCard);

    public AnimatorSet centerCards() {
        int newCenter ;
        float translationValue ;
        AnimatorSet mover =new AnimatorSet();
        ArrayList<Animator> movers = new ArrayList<>();


        if(cards.size()>0) {
            newCenter = cards.size() / 2;
            if (sittingPosition == "north" || sittingPosition=="south")
                translationValue = -cards.get(newCenter).positionX;
            else
                translationValue = -cards.get(newCenter).positionY;


            for (int i = 0; i < cards.size(); i++) {
                if (sittingPosition == "north" || sittingPosition=="south")
                    movers.add(cards.get(i).setXtranslator(translationValue, 400));
                else
                    movers.add(cards.get(i).setYtranslator(translationValue, 400));
            }

            mover.playTogether(movers);
        }




        return mover;
    } //(just animation
    public abstract void throwCards();
    public abstract void setCardsListeners();
    public abstract AnimatorSet play(Player next);
    public abstract AnimatorSet fixDeck ();
}

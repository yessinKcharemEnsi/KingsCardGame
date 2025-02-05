package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v4.util.Pair;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;



//AutoPlayer  inherites from class Player

public class AutoPlayer extends Player {

    private static final String TAG = "AutoPlayer";



    public AutoPlayer(Activity activity, String sittingPosition) {
        super(activity,sittingPosition);
    }

    public Pair lookForCasse() {
        for (int i=0;i<cards.size()-1;i++){
            for (int j=i+1;j<cards.size();j++){
                if(cards.get(j).value==cards.get(i).value) {
                    Pair<Integer,Integer> pair = new Pair<>(i,j);
                    return  pair;
                }
            }
        }
        Pair<Integer,Integer> pair = new Pair<>(-1,-1);
        return pair;
    }

    public AnimatorSet revealCasse(Pair pair) {
        int i=(int)pair.first;
        int j=(int)pair.second;
        return revealCasse(i,j);
    } //just animation

    public AnimatorSet revealCasse(int i, int j) {
        ObjectAnimator rest = ObjectAnimator.ofFloat(null,"translationY",0f);
        rest.setDuration(500);

        AnimatorSet revealI = new AnimatorSet();
        if (sittingPosition=="north")
            revealI.playSequentially(cards.get(i).setYtranslator(2f,100),cards.get(i).flip(400));
        else if(sittingPosition=="east")
            revealI.playSequentially(cards.get(i).setXtranslator(-2f,100),cards.get(i).flip(400));
        else if(sittingPosition=="west")
            revealI.playSequentially(cards.get(i).setXtranslator(2f,100),cards.get(i).flip(400));



        AnimatorSet animatorSetI =new AnimatorSet();
        animatorSetI.playSequentially(revealI,rest);


        AnimatorSet revealJ = new AnimatorSet();

        if(sittingPosition=="north")
            revealJ.playSequentially(cards.get(j).setYtranslator(2f,100),cards.get(j).flip(400));
        else if (sittingPosition=="east")
            revealJ.playSequentially(cards.get(j).setXtranslator(-2f,100),cards.get(j).flip(400));
        else if(sittingPosition=="west")
            revealJ.playSequentially(cards.get(j).setXtranslator(2f,100),cards.get(j).flip(400));



        AnimatorSet animatorSetJ =new AnimatorSet();

        animatorSetJ.playSequentially(revealJ,rest);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorSetI,animatorSetJ);



        //   return lastTouch;
        return animatorSet;
    } //just animation

    public AnimatorSet fixCardsAndCasse(final int first, final int second) {

        final Card casse1=cards.get(first);
        final Card casse2=cards.get(second);


        ArrayList<Animator> rightSideAnimations,leftSideAnimations;
        rightSideAnimations=new ArrayList<>();
        leftSideAnimations=new ArrayList<>();

        AnimatorSet rightAnimatorSet,leftAnimatorSet,leftAndRight;
        rightAnimatorSet=new AnimatorSet();
        leftAnimatorSet=new AnimatorSet();
        leftAndRight = new AnimatorSet();


        AnimatorSet casseI = new AnimatorSet();
        casseI.playTogether(cards.get(first).setYtranslator(-cards.get(first).positionY,400),cards.get(first).setXtranslator(-cards.get(first).positionX,400));

        AnimatorSet casseJ = new AnimatorSet();
        casseJ.playTogether(cards.get(second).setYtranslator(-cards.get(second).positionY,400),cards.get(second).setXtranslator(-cards.get(second).positionX,400));

        AnimatorSet casseWithNoFade = new AnimatorSet();
        casseWithNoFade.playTogether(casseI,casseJ);

        AnimatorSet fadeCasse = new AnimatorSet();
        fadeCasse.playTogether(cards.get(first).fadeOut(500),cards.get(second).fadeOut(500));

        AnimatorSet casse = new AnimatorSet();
        casse.playSequentially(casseWithNoFade,fadeCasse);



        if (first<5 && second>4){

            for (int i=0 ; i<first ; i++){
                if(sittingPosition=="north")
                    rightSideAnimations.add(cards.get(i).setXtranslator(-4,300));
                else if(sittingPosition=="east")
                    rightSideAnimations.add(cards.get(i).setYtranslator(-5.2f,300));
                else
                    rightSideAnimations.add(cards.get(i).setYtranslator(5.2f,300));
            }
            for (int i=cards.size()-1 ; i>second ; i--){
                if(sittingPosition=="north")
                    leftSideAnimations.add(cards.get(i).setXtranslator(4,300));
                else if(sittingPosition=="east")
                    leftSideAnimations.add(cards.get(i).setYtranslator(5.2f,300));
                else
                    leftSideAnimations.add(cards.get(i).setYtranslator(-5.2f,300));
            }
            rightAnimatorSet.playTogether(rightSideAnimations);
            leftAnimatorSet.playTogether(leftSideAnimations);
            leftAndRight.playTogether(rightAnimatorSet,leftAnimatorSet);

        }

        else if (second<5) {

            for (int i=0 ; i<first ; i++) {
                if(sittingPosition=="north")
                    rightSideAnimations.add(cards.get(i).setXtranslator(-8,300));
                else if(sittingPosition=="east")
                    rightSideAnimations.add(cards.get(i).setYtranslator(-10.4f,300));
                else
                    rightSideAnimations.add(cards.get(i).setYtranslator(10.4f,300));
            }
            for (int i=first+1;i<second;i++){
                if(sittingPosition=="north")
                    rightSideAnimations.add(cards.get(i).setXtranslator(-4,300));
                else if(sittingPosition=="east")
                    rightSideAnimations.add(cards.get(i).setYtranslator(-5.2f,300));
                else
                    rightSideAnimations.add(cards.get(i).setYtranslator(5.2f,300));
            }

            rightAnimatorSet.playTogether(rightSideAnimations);

        }

        else if (first>4){
            for (int i=cards.size()-1 ; i>second ; i--) {
                if(sittingPosition=="north")
                    leftSideAnimations.add(cards.get(i).setXtranslator(8,300));
                else
                    leftSideAnimations.add(cards.get(i).setYtranslator(10.4f,300));
            }
            for (int i=second-1;i>first;i--){
                if(sittingPosition=="north")
                    leftSideAnimations.add(cards.get(i).setXtranslator(4,300));
                else if(sittingPosition=="east")
                    leftSideAnimations.add(cards.get(i).setXtranslator(5.2f,300));
                else
                    leftSideAnimations.add(cards.get(i).setXtranslator(-5.2f,300));
            }
            leftAnimatorSet.playTogether(leftSideAnimations);
        }

        leftAndRight.playTogether(rightAnimatorSet,leftAnimatorSet);

        cards.remove(first);
        cards.remove(second-1);

        ObjectAnimator sleeper = ObjectAnimator.ofFloat(null,"translationY",0f);
        sleeper.setDuration(500);

        int newCenter ;
        float translationValue = 0;
        ArrayList<Animator> movers = new ArrayList<>();
        AnimatorSet mover = new AnimatorSet();
        AnimatorSet forAll =new AnimatorSet();
        if (cards.size() !=0 && (second<5 || first>4))  {
            newCenter=cards.size()/2;
            if (sittingPosition=="north")
                translationValue= - cards.get(newCenter).positionX;
            else
                translationValue= - cards.get(newCenter).positionY;
            for (int i=0;i<cards.size();i++){
                if(sittingPosition=="north")
                    movers.add(cards.get(i).setXtranslator(translationValue,400));
                else
                    movers.add(cards.get(i).setYtranslator(translationValue,400));
            }
            mover.playTogether(movers);

            forAll.playSequentially(leftAndRight,mover,sleeper) ;

            AnimatorSet lastTouch = new AnimatorSet();
            lastTouch.playTogether(casse,forAll);

            lastTouch.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Game.removeCard(casse1);
                    Game.removeCard(casse2);
                }
            });

            return lastTouch;

        }
        else {
            leftAndRight.playTogether(rightAnimatorSet, leftAnimatorSet,sleeper);

            AnimatorSet lastTouch = new AnimatorSet();
            lastTouch.playTogether(casse,leftAndRight);
            lastTouch.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Game.removeCard(casse1);
                    Game.removeCard(casse2);
                }
            });

            return lastTouch;
        }




    }





    public AnimatorSet casse() {


        Pair<Integer,Integer> pair = lookForCasse();
        int i = pair.first;
        int j =pair.second;

        ArrayList<Animator> animators = new ArrayList<>();
        while (i!=-1){
            animators.add(revealCasse(i,j));

            if(cards.size() != 0) {
                animators.add(fixCardsAndCasse(i, j));
            }
            pair=lookForCasse();
            i = pair.first;
            j =pair.second;

        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);
        return  animatorSet;
    }

    public AnimatorSet pickCard(final Player player) {
        //get a random card from player cards
        Random random = new Random();
        int randomN = random.nextInt(player.cards.size());
        final Card card = player.cards.get(randomN);

        float positionX = 0;
        float positionY = 0;
        ArrayList<Animator> less = new ArrayList<>();
        AnimatorSet lessTranslator, moreTranslator;
        AnimatorSet cardPlacer = new AnimatorSet();
        AnimatorSet rotator = null;
        int index=-1,cas=-1;

        if(player.cards.size()>0) {


            if (sittingPosition == "north") {

                if (cards.get(cards.size() - 1).ordre < card.ordre) {
                    cas = 1;
                    //coordinates of the new position of card
                    positionX = cards.get(cards.size() - 1).positionX - 4;
                    positionY = cards.get(cards.size() - 1).positionY;

                } else if (cards.get(0).ordre > card.ordre) {
                    cas = 2;
                    //coordinates of the new position of card
                    positionX = cards.get(0).positionX + 4;
                    positionY = cards.get(0).positionY;

                } else {
                    cas = 3;
                    for (int i = 0; i < cards.size() - 1; i++) {
                        if (cards.get(i).ordre < card.ordre && cards.get(i + 1).ordre > card.ordre) {
                            index = i;
                            break;
                        }

                    }
                    int firstHalf = index, secondHalf = (cards.size() - 1) - index;
                    if (firstHalf > secondHalf) {
                        positionX = cards.get(index + 1).positionX;
                        positionY = cards.get(index + 1).positionY;
                        for (int i = index + 1; i < cards.size(); i++) {
                            less.add(cards.get(i).setXtranslator(-4f, 200));
                        }
                    } else {
                        positionX = cards.get(index).positionX;
                        positionY = cards.get(index).positionY;
                        for (int i = 0; i <= index; i++)
                            less.add(cards.get(i).setXtranslator(4f, 200));
                    }
                }
                float newPositionX = -card.positionX + positionX;
                float newPositionY = -card.positionY + positionY;


                lessTranslator = new AnimatorSet();
                lessTranslator.playTogether(less);


                AnimatorSet fixPlayerCards;
                fixPlayerCards = player.fixCardsAfterPull(randomN);

                //the actual actions
                player.cards.remove(card);
                switch (cas) {
                    case 1:
                        cards.add(card);
                        break;
                    case 2:
                        cards.add(0, card);
                        break;
                    case 3:
                        cards.add(index + 1, card);
                        break;
                }


                AnimatorSet migrate = new AnimatorSet();
                if (player.sittingPosition == "east")
                    migrate.playSequentially(card.setXtranslator(newPositionX, 200),
                            card.rotate(-90f, 200), lessTranslator, card.setYtranslator(newPositionY, 200));
                else if (player.sittingPosition == "west")
                    migrate.playSequentially(card.setXtranslator(newPositionX, 200),
                            card.rotate(90f, 200), lessTranslator, card.setYtranslator(newPositionY, 200));
                else {
                    float firstOnY, secondOnY;
                    firstOnY = newPositionY + Card.height;
                    secondOnY = -Card.height;

                    AnimatorSet flipAnimation = card.flip(200);
                    flipAnimation.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            card.rotate(-180,300).start();
                        }
                    });
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(card.setYtranslator(firstOnY, 400),flipAnimation);
                    migrate.playSequentially(animatorSet, lessTranslator, card.setXtranslator(newPositionX, 200), card.setYtranslator(secondOnY, 200));
                }


                AnimatorSet playerDeckFixer = new AnimatorSet();
                playerDeckFixer.playSequentially(fixPlayerCards, player.centerCards());

                AnimatorSet migrateAndPlayerDeckFixer = new AnimatorSet();
                migrateAndPlayerDeckFixer.playTogether(migrate, playerDeckFixer);


                cardPlacer.playSequentially(migrateAndPlayerDeckFixer, centerCards());


            } else if (sittingPosition == "west") {


                if (cards.get(cards.size() - 1).ordre < card.ordre) {
                    cas = 1;
                    //coordinates of the new position of card
                    positionX = cards.get(cards.size() - 1).positionX;
                    positionY = cards.get(cards.size() - 1).positionY + 5.2f;

                } else if (cards.get(0).ordre > card.ordre) {
                    cas = 2;
                    //coordinates of the new position of card
                    positionX = cards.get(0).positionX;
                    positionY = cards.get(0).positionY - 5.2f;

                } else {
                    cas = 3;
                    for (int i = 0; i < cards.size() - 1; i++) {
                        if (cards.get(i).ordre < card.ordre && cards.get(i + 1).ordre > card.ordre) {
                            index = i;
                            break;
                        }

                    }
                    int firstHalf = index, secondHalf = (cards.size() - 1) - index;
                    if (firstHalf > secondHalf) {
                        positionX = cards.get(index + 1).positionX;
                        positionY = cards.get(index + 1).positionY;
                        for (int i = index + 1; i < cards.size(); i++) {
                            less.add(cards.get(i).setYtranslator(5.2f, 200));
                        }
                    } else {
                        positionX = cards.get(index).positionX;
                        positionY = cards.get(index).positionY;
                        for (int i = 0; i <= index; i++)
                            less.add(cards.get(i).setYtranslator(-5.2f, 200));
                    }
                }

                float newPositionX = -card.positionX + positionX;
                float newPositionY = -card.positionY + positionY;


                lessTranslator = new AnimatorSet();
                lessTranslator.playTogether(less);


                AnimatorSet fixPlayerCards;
                fixPlayerCards = player.fixCardsAfterPull(randomN);

                //the actual actions
                player.cards.remove(card);
                switch (cas) {
                    case 1:
                        cards.add(card);
                        break;
                    case 2:
                        cards.add(0, card);
                        break;
                    case 3:
                        cards.add(index + 1, card);
                        break;
                }


                AnimatorSet migrate = new AnimatorSet();
                if (player.sittingPosition == "north")
                    migrate.playSequentially(card.setYtranslator(newPositionY, 200),
                            card.rotate(-90f, 200), lessTranslator, card.setXtranslator(newPositionX, 200));
                else if (player.sittingPosition == "south") {
                    AnimatorSet firstHalfMigrate = new AnimatorSet();
                    firstHalfMigrate.playTogether(card.setYtranslator(newPositionY, 200), card.flip(200));
                    migrate.playSequentially(firstHalfMigrate, card.rotate(90f, 200), lessTranslator, card.setXtranslator(newPositionX, 200));
                } else {
                    float firstOnX, secondOnX;
                    firstOnX = newPositionX + Card.width * 1.5f;
                    secondOnX = -Card.width * 1.5f;
                    AnimatorSet firstAndFlipAnimations = new AnimatorSet();
                    firstAndFlipAnimations.playTogether(card.setXtranslator(firstOnX, 400),card.rotate(-540,400));
                    migrate.playSequentially(firstAndFlipAnimations, lessTranslator, card.setYtranslator(newPositionY, 200), card.setXtranslator(secondOnX, 200));
                }


                AnimatorSet playerDeckFixer = new AnimatorSet();
                playerDeckFixer.playSequentially(fixPlayerCards, player.centerCards());


                AnimatorSet migrateAndPlayerDeckFixer = new AnimatorSet();
                migrateAndPlayerDeckFixer.playTogether(migrate, playerDeckFixer);


                cardPlacer.playSequentially(migrateAndPlayerDeckFixer, centerCards());


            } else if (sittingPosition == "east") {
                if (cards.get(cards.size() - 1).ordre < card.ordre) {
                    cas = 1;
                    //coordinates of the new position of card
                    positionX = cards.get(cards.size() - 1).positionX;
                    positionY = cards.get(cards.size() - 1).positionY - 5.2f;

                } else if (cards.get(0).ordre > card.ordre) {
                    cas = 2;
                    //coordinates of the new position of card
                    positionX = cards.get(0).positionX;
                    positionY = cards.get(0).positionY + 5.2f;

                } else {
                    cas = 3;
                    for (int i = 0; i < cards.size() - 1; i++) {
                        if (cards.get(i).ordre < card.ordre && cards.get(i + 1).ordre > card.ordre) {
                            index = i;
                            break;
                        }

                    }
                    int firstHalf = index, secondHalf = (cards.size() - 1) - index;
                    if (firstHalf > secondHalf) {
                        positionX = cards.get(index + 1).positionX;
                        positionY = cards.get(index + 1).positionY;
                        for (int i = index + 1; i < cards.size(); i++) {
                            less.add(cards.get(i).setYtranslator(-5.2f, 200));
                        }
                    } else {
                        positionX = cards.get(index).positionX;
                        positionY = cards.get(index).positionY;
                        for (int i = 0; i <= index; i++)
                            less.add(cards.get(i).setYtranslator(+5.2f, 200));
                    }
                }

                float newPositionX = -card.positionX + positionX;
                float newPositionY = -card.positionY + positionY;


                lessTranslator = new AnimatorSet();
                lessTranslator.playTogether(less);


                AnimatorSet fixPlayerCards;
                fixPlayerCards = player.fixCardsAfterPull(randomN);

                //the actual actions
                player.cards.remove(card);
                switch (cas) {
                    case 1:
                        cards.add(card);
                        break;
                    case 2:
                        cards.add(0, card);
                        break;
                    case 3:
                        cards.add(index + 1, card);
                        break;
                }


                AnimatorSet migrate = new AnimatorSet();
                if (player.sittingPosition == "north")
                    migrate.playSequentially(card.setYtranslator(newPositionY, 200),
                            card.rotate(90f, 200), lessTranslator, card.setXtranslator(newPositionX, 200));
                else if (player.sittingPosition == "south") {
                    AnimatorSet firstHalfMigrate = new AnimatorSet();
                    firstHalfMigrate.playTogether(card.setYtranslator(newPositionY, 500), card.flip(500));
                    migrate.playSequentially(firstHalfMigrate, card.rotate(-90f, 200), lessTranslator, card.setXtranslator(newPositionX, 200));
                } else {
                    float firstOnX, secondOnX;
                    firstOnX = newPositionX - Card.width * 1.5f;
                    secondOnX = +Card.width * 1.5f;
                    AnimatorSet firstAndFlipAnimation =new AnimatorSet();
                    firstAndFlipAnimation.playTogether(card.setXtranslator(firstOnX, 400),card.rotate(-540,400));
                    migrate.playSequentially(firstAndFlipAnimation, lessTranslator, card.setYtranslator(newPositionY, 200), card.setXtranslator(secondOnX, 200));
                }


                AnimatorSet playerDeckFixer = new AnimatorSet();
                playerDeckFixer.playSequentially(fixPlayerCards, player.centerCards());


                AnimatorSet migrateAndPlayerDeckFixer = new AnimatorSet();
                migrateAndPlayerDeckFixer.playTogether(migrate, playerDeckFixer);


                cardPlacer.playSequentially(migrateAndPlayerDeckFixer, centerCards());
            }
        }

        return cardPlacer;





    }

    public AnimatorSet play(Player next) {
        AnimatorSet onBoard=new AnimatorSet();
        if(!Game.firstTourEnds)
            onBoard = Game.setBoradText(sittingPosition,400,400,400,30);
        else
            onBoard.setDuration(0);


        //getUp
        ArrayList<Animator> getUp = new ArrayList<>();

        if(sittingPosition=="north") {
            for (Card card : cards) {
                getUp.add(card.setYtranslator(7, 300));
            }
        }
        else if(sittingPosition=="west"){
            for (Card card : cards) {
                getUp.add(card.setXtranslator(7,300));
            }
        }
        else if (sittingPosition=="east"){
            for (Card card : cards) {
                getUp.add(card.setXtranslator(-7,300));
            }
        }

        AnimatorSet get = new AnimatorSet();
        get.playTogether(getUp);



        //*******************************************************************************

        AnimatorSet animatorSet1= pickCard(next);
        AnimatorSet animatorSet2= casse();

        //getDown
        ArrayList<Animator> getDown = new ArrayList<>();


        if(sittingPosition=="north") {
            for (Card card : cards) {
                getDown.add(card.setYtranslator(-7, 300));
            }
        }
        else if(sittingPosition=="west"){
            for (Card card : cards) {
                getDown.add(card.setXtranslator(-7,300));
            }
        }
        else if (sittingPosition=="east"){
            for (Card card : cards) {
                getDown.add(card.setXtranslator(7,300));
            }
        }
        AnimatorSet down = new AnimatorSet();
        down.playTogether(getDown);

        //*********************************************************************************

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(Game.sleeper(400),onBoard,get,animatorSet1,animatorSet2,down,Game.sleeper(500));
        Log.d(TAG, "fuck: "+sittingPosition+" succesuful play method");
        return animatorSet;

    }

    @Override
    public AnimatorSet fixDeck() {

        return  null;
    }


    //null methods
    @Override
    public void getCard(Player player) {

    }

    @Override
    public AnimatorSet getCard2(Player player, int intCard) {
        return  null;

    }

    @Override
    public void throwCards() {

    }

    @Override
    public void setCardsListeners() {

    }

}

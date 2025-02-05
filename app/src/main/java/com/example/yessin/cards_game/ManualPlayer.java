package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;



public class ManualPlayer extends Player {
    ArrayList<Card> CardsToThrow = new ArrayList<>();

    public ManualPlayer(Activity activity) {
        super(activity,"south");
    }





    // use this on a button to go to the other activity
    public void getCard(Player player){


        Intent getCard = new Intent(activity.getApplicationContext(),GettingCard.class);
        ArrayList<String> cardSuits = new ArrayList<>();
        cardSuits.add("hearts");cardSuits.add("clubs");cardSuits.add("spades");cardSuits.add("diamonds");

        // get the information about the player cards to send them to the other activity
        ArrayList<Integer> suits = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        for (int i=0;i<player.cards.size();i++){
            suits.add(cardSuits.indexOf(player.cards.get(i).suit));
            values.add(player.cards.get(i).value);
        }

        getCard.putExtra("suits",suits);
        getCard.putExtra("values",values);
        getCard.putExtra("sittingPosition",player.sittingPosition);


        activity.startActivityForResult(getCard,1);

    }

    public AnimatorSet getCard2(Player player, int intCard){

        final Card card = player.cards.get(intCard);

        float positionX ,positionY;
        ArrayList<Animator> less = new ArrayList<>();
        AnimatorSet lessTranslator;
        AnimatorSet cardPlacer = new AnimatorSet();
        int index=-1,cas;



        if (cards.get(cards.size() - 1).ordre < card.ordre) {
            cas=1;
            //coordinates of the new position of card
            positionX = cards.get(cards.size() - 1).positionX+4;
            positionY = cards.get(cards.size() - 1).positionY;

        }
        else if (cards.get(0).ordre > card.ordre) {
            cas=2;
            //coordinates of the new position of card
            positionX = cards.get(0).positionX-4;
            positionY = cards.get(0).positionY;

        }
        else {
            cas=3;
            for (int i = 0; i < cards.size()-1; i++) {
                if (cards.get(i).ordre < card.ordre && cards.get(i+1).ordre > card.ordre) {
                    index = i;
                    break;
                }

            }
            int firstHalf=index,secondHalf=(cards.size()-1)-index;
            if(firstHalf>secondHalf) {
                positionX = cards.get(index+1).positionX;
                positionY = cards.get(index+1).positionY;
                for (int i = index+1; i < cards.size(); i++) {
                    less.add(cards.get(i).setXtranslator(4f, 200));
                }
            }
            else {
                positionX = cards.get(index).positionX;
                positionY = cards.get(index).positionY;
                for (int i = 0; i <=index; i++)
                    less.add(cards.get(i).setXtranslator(-4f, 200));
            }
        }
        float newPositionX = -card.positionX + positionX;
        float newPositionY = -card.positionY + positionY;


        lessTranslator = new AnimatorSet();
        lessTranslator.playTogether(less);



        AnimatorSet fixPlayerCards;
        fixPlayerCards = player.fixCardsAfterPull(intCard);

        //the actual actions
        player.cards.remove(card);
        switch (cas) {
            case 1 :
                cards.add(card);
                break;
            case 2:
                cards.add(0,card);
                break;
            case 3:
                cards.add(index+1,card);
                break;
        }

        setCardsListeners();


        AnimatorSet migrate = new AnimatorSet();
        if(player.sittingPosition=="east") {
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSet zoomInandout=new AnimatorSet();
            AnimatorSet rotateAndZoom = new AnimatorSet();

            if(card.value==10) {
                zoomInandout.playSequentially(card.zoom(1.25f,350),card.zoom(0.8f,350));
                rotateAndZoom.playTogether(zoomInandout, card.rotate(450f, 700));
                migrate.playSequentially(card.setXtranslator(newPositionX, 300),rotateAndZoom, lessTranslator, animatorSet);
            }
            else
                migrate.playSequentially(card.setXtranslator(newPositionX, 300), card.rotate(90f, 200), lessTranslator,animatorSet);

            animatorSet.playTogether(card.flip(300),card.setYtranslator(newPositionY, 300));
        }
        else if(player.sittingPosition=="west") {
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSet zoomInandout=new AnimatorSet();
            AnimatorSet rotateAndZoom = new AnimatorSet();

            if(card.value==10) {
                zoomInandout.playSequentially(card.zoom(2f,350),card.zoom(0.5f,350));
                rotateAndZoom.playTogether(zoomInandout, card.rotate(-450f, 700));
                migrate.playSequentially(card.setXtranslator(newPositionX, 300),rotateAndZoom, lessTranslator, animatorSet);
            }
            else
                migrate.playSequentially(card.setXtranslator(newPositionX, 300), card.rotate(-90f, 200), lessTranslator,animatorSet);
            animatorSet.playTogether(card.flip(300),card.setYtranslator(newPositionY, 300));
        }
        else{
            float firstOnY,secondOnY;
            AnimatorSet animatorSet = new AnimatorSet();
            firstOnY=newPositionY-Card.height;
            secondOnY=Card.height;
            AnimatorSet firstOnYAndRotate = new AnimatorSet();
            firstOnYAndRotate.playTogether(card.setYtranslator(firstOnY, 400),card.rotate(-540,300));
            migrate.playSequentially(firstOnYAndRotate,lessTranslator,animatorSet,card.setYtranslator(secondOnY, 300));
            animatorSet.playTogether(card.flip(300),card.setXtranslator(newPositionX, 300));
        }






        AnimatorSet playerDeckFixer = new AnimatorSet();
        playerDeckFixer.playSequentially(fixPlayerCards, player.centerCards());

        AnimatorSet migrateAndPlayerDeckFixer = new AnimatorSet();
        migrateAndPlayerDeckFixer.playTogether(migrate,playerDeckFixer);



        cardPlacer.playSequentially(migrateAndPlayerDeckFixer,centerCards());

        return cardPlacer;







    }

    //  throw the cards if they match or bring them backToMenu to the deck if not
    public  void throwCards(){
        AnimatorSet Throwing = new AnimatorSet();
        if(CardsToThrow.size() == 2 ){
            if(CardsAreCompatible()) {
                final Card casse1 = CardsToThrow.get(0);
                final Card casse2 = CardsToThrow.get(1);
                Throwing.playTogether(CardsToThrow.get(0).setXtranslator(-CardsToThrow.get(0).positionX,400),CardsToThrow.get(0).setYtranslator(-CardsToThrow.get(0).positionY,400));
                Throwing.playTogether(CardsToThrow.get(1).setXtranslator(-CardsToThrow.get(1).positionX,400),CardsToThrow.get(1).setYtranslator(-CardsToThrow.get(1).positionY,400));
                AnimatorSet fader = new AnimatorSet();
                fader.playTogether(CardsToThrow.get(0).fadeOut(400),CardsToThrow.get(1).fadeOut(400));

                AnimatorSet lastTouch = new AnimatorSet();
                lastTouch.playSequentially(Throwing,fader);
                lastTouch.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Game.removeCard(casse1);
                        Game.removeCard(casse2);
                    }
                });

                lastTouch.start();
                cards.remove(CardsToThrow.get(0));
                cards.remove(CardsToThrow.get(1));
                ArrangeDeck();
            }else{
                CardsToThrow.get(0).setYtranslator(5,500).start();
                CardsToThrow.get(1).setYtranslator(5,500).start();
            }
        }else if(CardsToThrow.size() == 1){
            CardsToThrow.get(0).setYtranslator(5,400).start();
        }
        CardsToThrow.clear();
    }

    // Arrange Deck
    public void ArrangeDeck(){
        AnimatorSet arrange = new AnimatorSet();
        for(int i=0;i<cards.size();i++){
            //arrange.playSequentially();
            cards.get(i).setXtranslator(i*3.8f - (cards.size()-1)*1.9f - cards.get(i).positionX,500).start();
        }
    }

    // use this after you add the cards to the manual player to add to cards their click listener
    public void setCardsListeners(){
        View.OnClickListener ChooseCard = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardClicked(view);
            }
        };

        // add OnClickListener to all cards of the player
        for (int i=0;i<cards.size();i++){
            cards.get(i).face.setOnClickListener(ChooseCard);
            cards.get(i).face.setTag(cards.get(i));
        }
    }



    void CardClicked(View view){
        if(cards.contains((Card) view.getTag())) {
            // Translate the card down if it's already up
            if (CardsToThrow.contains((Card) view.getTag())) {
                ((Card) view.getTag()).setYtranslator(5f, 200).start();
                CardsToThrow.remove((Card) view.getTag());
            } else if (CardsToThrow.size() < 2) {
                ((Card) view.getTag()).setYtranslator(-5f, 200).start();
                CardsToThrow.add((Card) view.getTag());
            }
        }
    }

    // check if two cards are compatible
    boolean CardsAreCompatible(){
        if(CardsToThrow.get(0).value == CardsToThrow.get(1).value ){
            return true;
        }else{
            return false;
        }
    }

    public AnimatorSet play (Player player) {
     /*   Button casse = activity.findViewById(R.id.button2);
        Button endTurn = activity.findViewById(R.id.play);

        ObjectAnimator appearCasse = ObjectAnimator.ofFloat(casse,"alpha",0.0f,1.0f);
        ObjectAnimator appearEndTurn = ObjectAnimator.ofFloat(endTurn,"alpha",0.0f,1.0f);

        appearCasse.setDuration(200);
        appearEndTurn.setDuration(200);

        appearCasse.start();
        appearEndTurn.start();

        getCard(player);


        casse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throwCards();
            }
        });
        return new AnimatorSet();*/
        return  null;

    }

    public AnimatorSet fixDeck () {
        ArrayList<Animator> animators = new ArrayList<>();
        for(Card c : CardsToThrow) {
            animators.add(c.setYtranslator(5,300));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);

        CardsToThrow.clear();
        return animatorSet;
    }








    @Override
    public AnimatorSet pickCard(Player player) {
        return null;
    }

    @Override
    public AnimatorSet casse() {
        return null;
    }
}

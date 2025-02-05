package com.example.yessin.cards_game;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



//class that represents a card

public class Card {
    public Activity activity;
    public ImageView face;
    public ImageView back;
    public String suit;
    public int value;
    boolean isFront;
    int rotationDegree;
    float positionX,positionY;
    int ordre; //position of a card inside the full deck
    TextView textView,textView2;
    public Point screenSize = new Point(); //screenSize.x = width
    float scale;
    static float width,height;





    public Card(Activity activity,String suit,int value) {
        //sets imageViews , image resources ,heigh and width , position and angule of a card
        this.activity=activity;
        this.suit=suit;
        this.value=value;
        scale =1f;



        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getSize(screenSize);


        //backToMenu imageView
        back = new ImageView(activity);
        //   backToMenu.setBackgroundColor(Color.parseColor("#01ff90"));
        back.setImageResource(Settings.cardBack);

        //face ImageView
        face = new ImageView(activity);
        switch(value){
            case 1 :
                if(suit=="hearts") {
                    back.setId(R.id.oneOfHeartsBack);
                    face.setId(R.id.oneOfHearts);
                    face.setImageResource(R.drawable.one_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.oneOfSpadesBack);
                    face.setId(R.id.oneOfSpades);
                    face.setImageResource(R.drawable.one_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.oneOfClubsBack);
                    face.setId(R.id.oneOfClubs);
                    face.setImageResource(R.drawable.one_of_clubs_low);

                }

                else {
                    back.setId(R.id.oneOfDiamondsBack);
                    face.setId(R.id.oneOfDiamonds);
                    face.setImageResource(R.drawable.one_of_diamonds);
                }
                break;
            case 2 :
                if(suit=="hearts") {
                    back.setId(R.id.twoOfHeartsBack);
                    face.setId(R.id.twoOfHearts);
                    face.setImageResource(R.drawable.two_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.twoOfSpadesBack);
                    face.setId(R.id.twoOfSpades);
                    face.setImageResource(R.drawable.two_of_spades_low);
                }
                else if (suit=="clubs") {
                    back.setId(R.id.twoOfClubsBack);
                    face.setId(R.id.twoOfClubs);
                    face.setImageResource(R.drawable.two_of_clubs_low);


                }

                else {
                    back.setId(R.id.twoOfDiamondsBack);
                    face.setId(R.id.twoOfDiamonds);
                    face.setImageResource(R.drawable.two_of_diamonds_low);

                }
                break;
            case 3 :
                if(suit=="hearts") {
                    back.setId(R.id.threeOfHeartsBack);
                    face.setId(R.id.threeOfHearts);
                    face.setImageResource(R.drawable.three_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.threeOfSpadesBack);
                    face.setId(R.id.threeOfSpades);
                    face.setImageResource(R.drawable.three_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.threeOfClubsBack);
                    face.setId(R.id.threeOfClubs);
                    face.setImageResource(R.drawable.three_of_clubs_low);

                }

                else {
                    back.setId(R.id.threeOfDiamondsBack);
                    face.setId(R.id.threeOfDiamonds);
                    face.setImageResource(R.drawable.three_of_diamonds_low);

                }
                break;
            case 4 :
                if(suit=="hearts") {
                    back.setId(R.id.fourOfHeartsBack);
                    face.setId(R.id.fourOfHearts);
                    face.setImageResource(R.drawable.four_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.fourOfSpadesBack);
                    face.setId(R.id.fourOfSpades);
                    face.setImageResource(R.drawable.four_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.fourOfClubsBack);
                    face.setId(R.id.fourOfClubs);
                    face.setImageResource(R.drawable.four_of_clubs_low);

                }

                else {
                    back.setId(R.id.fourOfDiamondsBack);
                    face.setId(R.id.fourOfDiamonds);
                    face.setImageResource(R.drawable.four_of_diamonds_low);

                }
                break;
            case 5 :
                if(suit=="hearts") {
                    back.setId(R.id.fiveOfHeartsBack);
                    face.setId(R.id.fiveOfHearts);
                    face.setImageResource(R.drawable.five_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.fiveOfSpadesBack);
                    face.setId(R.id.fiveOfSpades);
                    face.setImageResource(R.drawable.five_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.fiveOfClubsBack);
                    face.setId(R.id.fiveOfClubs);
                    face.setImageResource(R.drawable.five_of_clubs_low);

                }

                else {
                    back.setId(R.id.fiveOfDiamondsBack);
                    face.setId(R.id.fiveOfDiamonds);
                    face.setImageResource(R.drawable.five_of_diamonds_low);

                }
                break;
            case 6 :
                if(suit=="hearts") {
                    back.setId(R.id.sixOfHeartsBack);
                    face.setId(R.id.sixOfHearts);
                    face.setImageResource(R.drawable.six_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.sixOfSpadesBack);
                    face.setId(R.id.sixOfSpades);
                    face.setImageResource(R.drawable.six_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.sixOfClubsBack);
                    face.setId(R.id.sixOfClubs);
                    face.setImageResource(R.drawable.six_of_clubs_low);

                }

                else {
                    back.setId(R.id.sixOfDiamondsBack);
                    face.setId(R.id.sixOfDiamonds);
                    face.setImageResource(R.drawable.six_of_diamonds_low);

                }
                break;
            case 7 :
                if(suit=="hearts") {
                    back.setId(R.id.sevenOfHeartsBack);
                    face.setId(R.id.sevenOfHearts);
                    face.setImageResource(R.drawable.seven_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.sevenOfSpadesBack);
                    face.setId(R.id.sevenOfSpades);
                    face.setImageResource(R.drawable.seven_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.sevenOfClubsBack);
                    face.setId(R.id.sevenOfClubs);
                    face.setImageResource(R.drawable.seven_of_clubs_low);
                }

                else {
                    back.setId(R.id.sevenOfDiamondsBack);
                    face.setId(R.id.sevenOfDiamonds);
                    face.setImageResource(R.drawable.seven_of_diamonds_low);

                }
                break;
            case 8 :
                if(suit=="hearts") {
                    back.setId(R.id.eightOfHeartsBack);
                    face.setId(R.id.eightOfHearts);
                    face.setImageResource(R.drawable.eight_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.eightOfSpadesBack);
                    face.setId(R.id.eightOfSpades);
                    face.setImageResource(R.drawable.eight_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.eightOfClubsBack);
                    face.setId(R.id.eightOfClubs);
                    face.setImageResource(R.drawable.eight_of_clubs_low);

                }

                else {
                    back.setId(R.id.eightOfDiamondsBack);
                    face.setId(R.id.eightOfDiamonds);
                    face.setImageResource(R.drawable.eight_of_diamonds_low);

                }
                break;
            case 9 :
                if(suit=="hearts") {
                    back.setId(R.id.nineOfHeartsBack);
                    face.setId(R.id.nineOfHearts);
                    face.setImageResource(R.drawable.nine_of_hearts_low);

                }

                else if (suit=="spades") {
                    back.setId(R.id.nineOfSpadesBack);
                    face.setId(R.id.nineOfSpades);
                    face.setImageResource(R.drawable.nine_of_spades_low);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.nineOfClubsBack);
                    face.setId(R.id.nineOfClubs);
                    face.setImageResource(R.drawable.nine_of_clubs_low);

                }

                else {
                    back.setId(R.id.nineOfDiamondsBack);
                    face.setId(R.id.nineOfDiamonds);
                    face.setImageResource(R.drawable.nine_of_diamonds_low);

                }
                break;
            case 10:
                back.setId(R.id.jhayyichBack);
                face.setId(R.id.jhayyich);
                face.setImageResource(Settings.king);

                break;

        }






        //height and width
        float _width=6;
        width=_width;

        float realWidth = (_width*screenSize.x)/100;
        float realHeight =  (realWidth*1.52f);

        height=(realHeight*100)/screenSize.y;



        ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams((int)realWidth,(int)realHeight);
        face.setLayoutParams(clpcontactUs);
        back.setLayoutParams(clpcontactUs);

        //orientation
        isFront=false;
        //angle and positions
        rotationDegree =0;
        int[]  location = new int[2]; //location[0] = Y , location[1] = Y
        face.getLocationOnScreen(location);
        positionX=location[0];
        positionX=location[1];

    }

    public void increaseCardResolution(){


        if(Settings.cardBack == R.drawable.blue_back_up_low)
            back.setImageResource(R.drawable.blue_back_up_high);
        else if(Settings.cardBack == R.drawable.purple_back_up_low)
            back.setImageResource(R.drawable.purple_back_up_high);
        else
            back.setImageResource(R.drawable.gray_back_up_high);
        switch(value){
            case 1 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.one_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.one_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.one_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.one_of_diamonds);

                }
                break;
            case 2 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.two_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.two_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.two_of_clubs);


                }

                else {
                    face.setImageResource(R.drawable.two_of_diamonds);

                }
                break;
            case 3 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.three_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.three_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.three_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.three_of_diamonds);

                }
                break;
            case 4 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.four_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.four_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.four_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.four_of_diamonds);

                }
                break;
            case 5 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.five_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.five_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.five_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.five_of_diamonds);

                }
                break;
            case 6 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.six_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.six_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.six_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.six_of_diamonds);

                }
                break;
            case 7 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.seven_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.seven_of_spades);

                }
                else if (suit=="clubs") {
                    back.setId(R.id.sevenOfClubsBack);
                    face.setId(R.id.sevenOfClubs);
                    face.setImageResource(R.drawable.seven_of_clubs);
                }

                else {
                    face.setImageResource(R.drawable.seven_of_diamonds);

                }
                break;
            case 8 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.eight_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.eight_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.eight_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.eight_of_diamonds);

                }
                break;
            case 9 :
                if(suit=="hearts") {
                    face.setImageResource(R.drawable.nine_of_hearts);

                }

                else if (suit=="spades") {
                    face.setImageResource(R.drawable.nine_of_spades);

                }
                else if (suit=="clubs") {
                    face.setImageResource(R.drawable.nine_of_clubs);

                }

                else {
                    face.setImageResource(R.drawable.nine_of_diamonds);

                }
                break;
            case 10:
                face.setImageResource(Settings.king);
                break;

        }
    }

    public int getOrdre (){
        return ordre;
    }


    public void addToTable(ConstraintLayout table, String orientation) {
        if (orientation=="west")
            this.rotate(-90,0).start();
        else if (orientation=="south")
            this.rotate(-180,0).start();
        else if (orientation=="east")
            this.rotate(90,0).start();
        //face add

        table.addView(face);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(table);
        constraintSet.connect(face.getId(), ConstraintSet.TOP, table.getId(), ConstraintSet.TOP,0);
        constraintSet.connect(face.getId(), ConstraintSet.LEFT, table.getId(), ConstraintSet.LEFT,0);
        constraintSet.connect(face.getId(), ConstraintSet.RIGHT, table.getId(), ConstraintSet.RIGHT,0);
        constraintSet.connect(face.getId(), ConstraintSet.BOTTOM, table.getId(), ConstraintSet.BOTTOM,0);
        constraintSet.setVerticalBias(face.getId(), (float)0.5);
        constraintSet.setHorizontalBias(face.getId(), (float)0.5);
        //backToMenu add
        constraintSet.connect(back.getId(), ConstraintSet.TOP, table.getId(), ConstraintSet.TOP,0);
        constraintSet.connect(back.getId(), ConstraintSet.LEFT, table.getId(), ConstraintSet.LEFT,0);
        constraintSet.connect(back.getId(), ConstraintSet.RIGHT, table.getId(), ConstraintSet.RIGHT,0);
        constraintSet.connect(back.getId(), ConstraintSet.BOTTOM, table.getId(), ConstraintSet.BOTTOM,0);
        constraintSet.setVerticalBias(back.getId(), (float)0.5);
        constraintSet.setHorizontalBias(back.getId(), (float)0.5);
        constraintSet.applyTo(table);
        table.addView(back);

    }
    public void addToTable(ConstraintLayout table) {
        addToTable(table,"north" );
    }

    public boolean isVertical(){
        if((rotationDegree==0 || rotationDegree==180 || rotationDegree==-180))
            return true;
        else
            return false;
    }


    public void flipWithClick(){
        final AnimatorSet frontAnim;
        final AnimatorSet backAnim;
        float scale = activity.getResources().getDisplayMetrics().density;
        face.setCameraDistance(8000*scale);
        back.setCameraDistance(8000*scale);
        frontAnim=(AnimatorSet) AnimatorInflater.loadAnimator(activity,R.animator.front_animator);
        backAnim=(AnimatorSet) AnimatorInflater.loadAnimator(activity,R.animator.back_animator);


        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront){
                    frontAnim.setTarget(face);
                    backAnim.setTarget(back);
                    frontAnim.start();
                    backAnim.start();
                    isFront =false;
                }
                else {
                    frontAnim.setTarget(back);
                    backAnim.setTarget(face);
                    frontAnim.start();
                    backAnim.start();
                    isFront =true;

                }
            }
        });
    }

    public AnimatorSet flip(int duration){


        AnimatorSet reveal1 = new AnimatorSet();
        float scale = activity.getResources().getDisplayMetrics().density;
        face.setCameraDistance(8000*scale);
        back.setCameraDistance(8000*scale);




        if (isVertical()) {


            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(null, "rotationY", 0.0f, 180f);
            objectAnimator1.setDuration(duration);

            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
            objectAnimator2.setStartDelay(duration / 2);
            objectAnimator2.setDuration(1);

            AnimatorSet frontAnimator = new AnimatorSet();
            frontAnimator.playTogether(objectAnimator1,objectAnimator2);
            //-----------------------------------------------------------------------------
            ObjectAnimator objectAnimator01 = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
            objectAnimator01.setDuration(0);

            ObjectAnimator objectAnimator02 = ObjectAnimator.ofFloat(null, "rotationY", -180f, 0f);
            objectAnimator02.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator02.setDuration(duration);

            ObjectAnimator objectAnimator03 = ObjectAnimator.ofFloat(null, "alpha", 0.0f, 1f);
            objectAnimator03.setStartDelay(duration / 2);
            objectAnimator03.setDuration(0);

            AnimatorSet backAnimator = new AnimatorSet();
            backAnimator.playTogether(objectAnimator01, objectAnimator02, objectAnimator03);

            if (!isFront) {
                objectAnimator1.setTarget(back);
                objectAnimator2.setTarget(back);


                objectAnimator01.setTarget(face);
                objectAnimator02.setTarget(face);
                objectAnimator03.setTarget(face);


                isFront = true;
                reveal1.playTogether(frontAnimator,backAnimator);
            }
            else {
                objectAnimator1.setTarget(face);
                objectAnimator2.setTarget(face);


                objectAnimator01.setTarget(back);
                objectAnimator02.setTarget(back);
                objectAnimator03.setTarget(back);



                ObjectAnimator reappearface = ObjectAnimator.ofFloat(face, "alpha", 0f, 1f);
                reappearface.setDuration(0);

                ObjectAnimator refix = ObjectAnimator.ofFloat(face, "rotationY", 180f, 0f);
                refix.setDuration(0);

                AnimatorSet returnToNormalState = new AnimatorSet();
                returnToNormalState.playTogether(refix,reappearface);

                isFront = false;
                AnimatorSet reveal0 = new AnimatorSet();
                reveal0.playTogether(frontAnimator,backAnimator);


                reveal1.playSequentially(reveal0,returnToNormalState);
            }

            return reveal1;

        }
        else {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(null, "rotationX", 0.0f, 180f);
            objectAnimator1.setDuration(duration);

            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
            objectAnimator2.setStartDelay(duration / 2);
            objectAnimator2.setDuration(1);

            AnimatorSet frontAnimator = new AnimatorSet();
            frontAnimator.playTogether(objectAnimator1,objectAnimator2);
            //-----------------------------------------------------------------------------
            ObjectAnimator objectAnimator01 = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
            objectAnimator01.setDuration(0);

            ObjectAnimator objectAnimator02 = ObjectAnimator.ofFloat(null, "rotationX", -180f, 0f);
            objectAnimator02.setRepeatMode(ValueAnimator.REVERSE);
            objectAnimator02.setDuration(duration);

            ObjectAnimator objectAnimator03 = ObjectAnimator.ofFloat(null, "alpha", 0.0f, 1f);
            objectAnimator03.setStartDelay(duration / 2);
            objectAnimator03.setDuration(0);

            AnimatorSet backAnimator = new AnimatorSet();
            backAnimator.playTogether(objectAnimator01, objectAnimator02, objectAnimator03);

            if (!isFront) {
                objectAnimator1.setTarget(back);
                objectAnimator2.setTarget(back);


                objectAnimator01.setTarget(face);
                objectAnimator02.setTarget(face);
                objectAnimator03.setTarget(face);


                isFront = true;
                reveal1.playTogether(frontAnimator,backAnimator);
            }
            else {
                objectAnimator1.setTarget(face);
                objectAnimator2.setTarget(face);


                objectAnimator01.setTarget(back);
                objectAnimator02.setTarget(back);
                objectAnimator03.setTarget(back);



                ObjectAnimator reappearface = ObjectAnimator.ofFloat(face, "alpha", 0f, 1f);
                reappearface.setDuration(0);

                ObjectAnimator refix = ObjectAnimator.ofFloat(face, "rotationX", 180f, 0f);
                refix.setDuration(0);

                AnimatorSet returnToNormalState = new AnimatorSet();
                returnToNormalState.playTogether(refix,reappearface);

                isFront = false;
                AnimatorSet reveal0 = new AnimatorSet();
                reveal0.playTogether(frontAnimator,backAnimator);


                reveal1.playSequentially(reveal0,returnToNormalState);
            }
            return reveal1;
        }


    }


    public AnimatorSet setXtranslator(float value, int duration, final MediaPlayer mediaPlayer ){
        //value en pourcentage

        float realValue = (value*screenSize.x)/100;
        float realPositionX = (positionX*screenSize.x)/100;



        ObjectAnimator objectAnimatorX0 = ObjectAnimator.ofFloat(face,"translationX",realPositionX,realPositionX+realValue);
        final ObjectAnimator objectAnimatorX1 = ObjectAnimator.ofFloat(back,"translationX",realPositionX,realPositionX+realValue);

        positionX+=value;

        objectAnimatorX0.setDuration(duration);
        objectAnimatorX1.setDuration(duration);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorX0,objectAnimatorX1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(mediaPlayer != null ) {
                    //     float speed = 0.5f;
                    //     mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                    mediaPlayer.start();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.reset();
                }



            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });







        return animatorSet;

    }
    public AnimatorSet setXtranslator(float value, int duration ) {
        return setXtranslator(value, duration,null);
    }



    public AnimatorSet setYtranslator(float value, final int duration, final MediaPlayer mediaPlayer) {
        float realValue = (value*screenSize.y)/100;
        float realPositionY = (positionY*screenSize.y)/100;

        ObjectAnimator objectAnimatorY0 = ObjectAnimator.ofFloat(face,"translationY",realPositionY,realPositionY+realValue);
        final ObjectAnimator objectAnimatorY1 = ObjectAnimator.ofFloat(back,"translationY",realPositionY,realPositionY+realValue);
        positionY+=value;

        objectAnimatorY0.setDuration(duration);
        objectAnimatorY1.setDuration(duration);




        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorY0,objectAnimatorY1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mediaPlayer != null) {
                    //       mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                    //       mediaPlayer.start();
                    mediaPlayer.start();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.reset();
                }



            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        return animatorSet;

    }
    public AnimatorSet setYtranslator(float value, int duration ) {
        return setYtranslator(value, duration,null);
    }


    public AnimatorSet rotate(float value,int duration, final MediaPlayer mediaPlayer) { //avec signe (-) c'est le sense dircet
        ObjectAnimator rotator = ObjectAnimator.ofFloat(face,"rotation",rotationDegree,rotationDegree+value);
        ObjectAnimator rotator1 = ObjectAnimator.ofFloat(back,"rotation",rotationDegree,rotationDegree+value);
        rotationDegree += value;
        rotationDegree=rotationDegree - (rotationDegree/360)*360;



        rotator.setDuration(duration);
        rotator1.setDuration(duration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotator,rotator1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mediaPlayer != null)
                    mediaPlayer.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.reset();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        return animatorSet;


    }
    public AnimatorSet rotate(float value,int duration ) {
        return rotate(value,duration,null );
    }

    public AnimatorSet fadeOut(int duration) {
        return fadeOut(duration,null);
    }

    public AnimatorSet fadeOut(int duration, final MediaPlayer mediaPlayer){
        ObjectAnimator fader = ObjectAnimator.ofFloat(back,"alpha",1,0);
        ObjectAnimator fader1 = ObjectAnimator.ofFloat(face,"alpha",1,0);



        if(isFront) {
            fader.setDuration(0);
            fader1.setDuration(duration);
        }
        else {
            fader.setDuration(duration);
            fader1.setDuration(0);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(fader1,fader);


        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mediaPlayer != null)
                    mediaPlayer.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.reset();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animatorSet;

    }

    public ObjectAnimator fadeIn(int duration, final MediaPlayer mediaPlayer){
        final ObjectAnimator fader = ObjectAnimator.ofFloat(back,"alpha",0,1);
        final ObjectAnimator fader1 = ObjectAnimator.ofFloat(face,"alpha",0,1);



        if(isFront) {
            fader1.setDuration(duration);
            fader1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    final ObjectAnimator fader10 = ObjectAnimator.ofFloat(back,"alpha",1,0);
                    fader10.setDuration(0);
                    fader10.start();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    fader.setDuration(0);
                    fader.start();

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            return fader1;
        }
        else {

            fader.setDuration(duration);

            fader.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    final ObjectAnimator fader10 = ObjectAnimator.ofFloat(face,"alpha",1,0);
                    fader10.setDuration(0);
                    fader10.start();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    fader1.setDuration(0);
                    fader1.start();

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            return fader;
        }

  /*      AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(fader1,fader);


        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mediaPlayer != null)
                    mediaPlayer.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.reset();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animatorSet;*/

    }

    public ObjectAnimator fadeIn(int duration) {
        return fadeIn(duration,null);
    }

    public AnimatorSet zoom(float toScale, int duration) {

        Animator faceAnimatorX = ObjectAnimator.ofFloat(face, "scaleX", scale, scale *toScale);
        Animator backAnimatorX = ObjectAnimator.ofFloat(back, "scaleX", scale, scale *toScale);


        Animator faceAnimatorY = ObjectAnimator.ofFloat(face, "scaleY", scale, scale *toScale);
        Animator backAnimatorY = ObjectAnimator.ofFloat(back, "scaleY", scale, scale *toScale);

        faceAnimatorX.setDuration(duration);
        backAnimatorX.setDuration(duration);

        faceAnimatorY.setDuration(duration);
        backAnimatorY.setDuration(duration);

        scale = scale *toScale;

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(faceAnimatorX,faceAnimatorY,backAnimatorX,backAnimatorY);






        return animatorSet;

    }
}

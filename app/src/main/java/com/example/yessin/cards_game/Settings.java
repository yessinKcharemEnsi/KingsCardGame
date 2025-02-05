package com.example.yessin.cards_game;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Yessin on 23/09/2020.
 */

public  class Settings {
    public static int king=R.drawable.the_new_king_high_two_gold;
    public static int table=R.drawable.blue_background_one;
    public static boolean sound = true;
    public  static String lang ="en";
    public  static  int cardBack=R.drawable.blue_back_up_low;
    public  static SharedPreferences gamePrefs ;



    public static void writePrefs(Activity activity){
        gamePrefs = activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = gamePrefs.edit();

        myEdit.putString("lang",lang);
        myEdit.putInt("king",king);
        myEdit.putInt("table",table);
        myEdit.putInt("card",cardBack);
        myEdit.putBoolean("sound",sound);

        myEdit.commit();

    }

    public static void readPrefs(Activity activity){
        gamePrefs = activity.getSharedPreferences("data", Context.MODE_PRIVATE);

        king = gamePrefs.getInt("king",R.drawable.the_new_king_high_two_gold);
        table = gamePrefs.getInt("table",R.drawable.blue_background_one);
        cardBack = gamePrefs.getInt("card",R.drawable.blue_back_up_low);
        sound = gamePrefs.getBoolean("sound",true);
        lang = gamePrefs.getString("lang","en");

    }
}

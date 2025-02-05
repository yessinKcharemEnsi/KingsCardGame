package com.example.yessin.cards_game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";
    Switch sound;
    RadioButton goldKing, crowns_king, coolKing,table1,table2,table3, blueCardBack,purpleCardBack, grayCardBack,french,english,deutsch;


    Button backToMenu;
    boolean languageChanged;

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

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
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












//views initialisation

        backToMenu = findViewById(R.id.back);

        sound =findViewById(R.id.switch1);

        goldKing =findViewById(R.id.gold_king);
        crowns_king =findViewById(R.id.crowns_king);
        coolKing =findViewById(R.id.cool_king);

        table1=findViewById(R.id.table1);
        table2=findViewById(R.id.table2);
        table3=findViewById(R.id.table3);

        blueCardBack =findViewById(R.id.blue_card_back);
        purpleCardBack=findViewById(R.id.purple_card_back);
        grayCardBack =findViewById(R.id.gray_card_back);

        french=findViewById(R.id.frensh);
        english=findViewById(R.id.english);
        deutsch=findViewById(R.id.german);


//check what was checked
        if(Settings.sound==true)
            sound.setChecked(true);


        //for language
        if(Settings.lang.equals("en"))
            english.setChecked(true);

        else if(Settings.lang.equals("fr"))
            french.setChecked(true);

        else if(Settings.lang.equals("de"))
            deutsch.setChecked(true);

        //for king
        if(Settings.king == R.drawable.the_new_king_high_two_gold)
            goldKing.setChecked(true);

        else if(Settings.king == R.drawable.the_new_king_high_two_)
            crowns_king.setChecked(true);

        else if(Settings.king == R.drawable.the_new_king_high)
            coolKing.setChecked(true);

        //for table
        if(Settings.table == R.drawable.blue_background_one)
            table1.setChecked(true);

        else if(Settings.table == R.drawable.gray_table)
            table2.setChecked(true);

        else if(Settings.table == R.drawable.purple_table)
            table3.setChecked(true);



        //for card
        if(Settings.cardBack == R.drawable.blue_back_up_low) {
            blueCardBack.setChecked(true);

        }

        else if(Settings.cardBack == R.drawable.purple_back_up_low) {

            purpleCardBack.setChecked(true);
        }

        else if(Settings.cardBack == R.drawable.gray_back_up_low) {
            grayCardBack.setChecked(true);

        }

        sound.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.sound = isChecked;
            }
        });





        goldKing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crowns_king.setChecked(false);
                coolKing.setChecked(false);
                Settings.king=R.drawable.the_new_king_high_two_gold;
            }
        });

        crowns_king.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldKing.setChecked(false);
                coolKing.setChecked(false);
                Settings.king=R.drawable.the_new_king_high_two_;
            }
        });

        coolKing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crowns_king.setChecked(false);
                goldKing.setChecked(false);
                Settings.king=R.drawable.the_new_king_high;
            }
        });


        table3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table2.setChecked(false);
                table1.setChecked(false);
                Settings.table=R.drawable.purple_table;            }
        });

        table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table1.setChecked(false);
                table3.setChecked(false);
                Settings.table=R.drawable.gray_table;
            }
        });

        table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table2.setChecked(false);
                table3.setChecked(false);
                Settings.table=R.drawable.blue_background_one;
            }
        });


        blueCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grayCardBack.setChecked(false);
                purpleCardBack.setChecked(false);
                Settings.cardBack=R.drawable.blue_back_up_low;

            }
        });

        grayCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpleCardBack.setChecked(false);
                blueCardBack.setChecked(false);
                Settings.cardBack=R.drawable.gray_back_up_low;
            }
        });

        purpleCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grayCardBack.setChecked(false);
                blueCardBack.setChecked(false);
                Settings.cardBack=R.drawable.purple_back_up_low;
            }
        });



        languageChanged=false;

        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deutsch.setChecked(false);
                english.setChecked(false);
                if(! Settings.lang.equals("fr")){
                    Settings.lang = "fr";
                    languageChanged=true;
                    MenuActivity.activity.finish();
                }


            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deutsch.setChecked(false);
                french.setChecked(false);
                if(! Settings.lang.equals("en")){
                    Settings.lang = "en";
                    languageChanged=true;
                    MenuActivity.activity.finish();
                }

            }

        });

        deutsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                french.setChecked(false);
                english.setChecked(false);
                if(! Settings.lang.equals("de")){
                    Settings.lang = "de";
                    languageChanged=true;
                    MenuActivity.activity.finish();
                }
            }
        });


        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent goBack = new Intent(getApplicationContext(),MenuActivity.class);
            //    startActivity(goBack)
                Settings.writePrefs(activity);

                if(languageChanged) {
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    Songs.menuSong.stop();
                    Songs.menuSong.release();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                else {


                    Intent goBack = new Intent(getApplicationContext(), MenuActivity.class);
                    setResult(1, goBack);
                    finish();
                }

            }
        });

    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);



    } //cant put after on create


    private void setApplicationLocale(String locale) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(locale.toLowerCase()));
        } else {
            config.locale = new Locale(locale.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }
    private void setLocaleImem(String lang)
    {
        Locale locale =new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void refreshActivity(){
        Intent refresh = new Intent(getApplicationContext(), MenuActivity.class);
        finish();
        startActivity(refresh);
    }




}

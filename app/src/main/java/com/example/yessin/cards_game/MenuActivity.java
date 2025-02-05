package com.example.yessin.cards_game;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
    Button play,settings,help,exit;
    Locale myLocale;


    @Override
    public void onBackPressed (){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

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

    public static Activity activity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        activity=this;

        Settings.readPrefs(this);

        setLocale(Settings.lang);
        setContentView(R.layout.activity_menu);

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












        Songs.menuSong = MediaPlayer.create(getApplicationContext(),R.raw.menu_music_two_moons_inn);
        Songs.menuSong.start();
        play=findViewById(R.id.buttonPlay);
        help=findViewById(R.id.buttonHelp);
        settings=findViewById(R.id.buttonSettings);
        exit=findViewById(R.id.buttonExit);





        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Bounce)
                        .duration(200)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                Songs.menuSong.stop();
                                Songs.menuSong.release();
                                startActivity(new Intent(getApplication(),GamePlay.class));
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            }
                        })
                        .playOn(play);

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Bounce)
                        .duration(200)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {

                                startActivityForResult(new Intent(getApplicationContext(),HowToPlayActivity.class),1);




                            }
                        })
                        .playOn(help);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Bounce)
                        .duration(200)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                Songs.menuSong.stop();
                                Songs.menuSong.release();
                                finishAffinity();
                            }
                        })
                        .playOn(exit);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Bounce)
                        .duration(200)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                startActivityForResult(new Intent(getApplicationContext(),SettingsActivity.class),1);

                            }
                        })
                        .playOn(settings);
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

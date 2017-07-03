package com.wisata.wisata;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wisata.wisata.ui.MainMenu;

public class MainActivity extends AppCompatActivity {

    private static int splashInterval = 5000;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
MediaPlayer audioBackground;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioBackground = MediaPlayer.create(this, R.raw.madura_ok);
        //Set looping ke true untuk mengulang audio jika telah selesai
        audioBackground.setLooping(true);
        //Set volume audio agar berbunyi
        audioBackground.setVolume(1, 1);
        //Memulai audio
        audioBackground.start();

        View splash = (View)findViewById(R.id.logo);
        Animation splashAni = AnimationUtils.loadAnimation(this, R.anim.mysplash_anim);
        splash.startAnimation(splashAni);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MainMenu.class);
                startActivity(i);

                //jeda selesai SplashScreen
                this.finish();
            }

            private void finish(){

            }
        }, splashInterval);


    }
    ;
}

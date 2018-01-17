package com.example.rajiv.gameof15;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Homesplash extends AppCompatActivity {
    TextView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homesplash);
        game = (TextView) findViewById(R.id.game);
        game.setVisibility(View.INVISIBLE);
        Log.i("hello","bhjxjxj");
        a.setDuration(2500);
        game.startAnimation(a);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                game.setVisibility(View.VISIBLE);

                        Intent i = new Intent(Homesplash.this, MainActivity.class);
                        startActivity(i);




            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
              }

    }


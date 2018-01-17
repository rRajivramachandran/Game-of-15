package com.example.rajiv.gameof15;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class level_select extends AppCompatActivity {
    Button easy,med,hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        Animation hellfire = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hell);
        easy = (Button) findViewById(R.id.easy);
        med = (Button) findViewById(R.id.med);
        hard = (Button) findViewById(R.id.hard);
        easy.startAnimation(hellfire);
        med.startAnimation(hellfire);
        hard.startAnimation(hellfire);
    }
    public void easy(View view)
    {
        select_helper(4,35);
    }
    public void medium(View view)
    {
        select_helper(7,50);
    }
    public void hard(View view)
    {
        select_helper(12,90);
    }
    public void home(View view)
    {
        Intent p = new Intent(this,MainActivity.class);
        startActivity(p);
    }
    public void onBackPressed()
    {

    }
    public void select_helper(int moves, int time)
    {
        Intent p = new Intent(this,home.class);
        p.putExtra("moves",moves);
        p.putExtra("time",time);
        startActivity(p);
    }

}



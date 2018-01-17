package com.example.rajiv.gameof15;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView welcome;
    int visible=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = (TextView) findViewById(R.id.welcome);

        new CountDownTimer(300000,500)
        {

            @Override
            public void onTick(long l) {
                if(visible==1)
                {
                    welcome.setVisibility(View.INVISIBLE);
                    visible=-1;
                }
                else
                {
                    welcome.setVisibility(View.VISIBLE);
                    visible=1;
                }
            }

            @Override
            public void onFinish() {welcome.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void play(View view)
    {
        Intent p = new Intent(this,level_select.class);
        startActivity(p);
    }
    public void instruct(View view)
    {
        Intent p = new Intent(this,instruct.class);
        startActivity(p);
    }
    public void sound(View view)
    {
        Intent p = new Intent(this,soundsetting.class);
        startActivity(p);
    }

    public void onBackPressed()
    {

    }
}

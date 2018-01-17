package com.example.rajiv.gameof15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class congrats extends AppCompatActivity {
    TextView time;
    EditText namer;
    long score;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle x = getIntent().getExtras();
        score = x.getLong("SCORE");
        level = x.getString("LEVEL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        time = (TextView) findViewById(R.id.timez);
        time.setText("FINISHED IN:  "+score+" s");
        namer = (EditText) findViewById(R.id.namer);
        namer.setText("");
    }
    public void enter(View view)
    {
        String nam = "";
        nam=namer.getText().toString();
        Log.i("hi:",nam);
        if(nam.length()==0)
        {
            Toast t= Toast.makeText(this,"Enter a name please",Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP|Gravity.LEFT,50,50);
            t.show();
        }
        else if(nam.length()>6)
        {
            Toast t= Toast.makeText(this,"Enter a shorter name please",Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP|Gravity.LEFT,50,50);
            t.show();
        }

        else {
            Intent i = new Intent(this, top_scorers.class);
            i.putExtra("SCORE", score);
            i.putExtra("Name", nam);
            i.putExtra("LEVEL",level);
            startActivity(i);
        }
    }
    public void onBackPressed()
    {

    }
}

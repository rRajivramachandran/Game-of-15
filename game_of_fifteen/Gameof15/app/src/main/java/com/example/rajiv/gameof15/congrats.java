package com.example.rajiv.gameof15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class congrats extends AppCompatActivity {
    TextView time;
    EditText namer;
    long score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle x = getIntent().getExtras();
        score = x.getLong("SCORE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        time = (TextView) findViewById(R.id.timez);
        time.setText("FINISHED IN:  "+score+" s");
        namer = (EditText) findViewById(R.id.namer);
    }
    public void entre(View view)
    {
        String nam = namer.getText().toString();
        Intent i = new Intent(this,top_scorers.class);
        i.putExtra("SCORE",score);
        i.putExtra("Name",nam);
        startActivity(i);
    }
    public void onBackPressed()
    {

    }
}

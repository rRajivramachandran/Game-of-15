package com.example.rajiv.gameof15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class level_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
    }
    public void easy(View view)
    {
        Intent p = new Intent(this,home.class);
        p.putExtra("moves",10);
        p.putExtra("time",10);
        startActivity(p);
    }
    public void medium(View view)
    {
        Intent p = new Intent(this,home.class);
        p.putExtra("moves",15);
        p.putExtra("time",15);
        startActivity(p);
    }
    public void hard(View view)
    {
        Intent p = new Intent(this,home.class);
        p.putExtra("moves",20);
        p.putExtra("time",20);
        startActivity(p);
    }
    public void onBackPressed()
    {

    }
}

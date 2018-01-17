package com.example.rajiv.gameof15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class instruct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruct);
    }
    public void home(View view)
    {
        Intent p = new Intent(this,home.class);
        startActivity(p);
    }
}

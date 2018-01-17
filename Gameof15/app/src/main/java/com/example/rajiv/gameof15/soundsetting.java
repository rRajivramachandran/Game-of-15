package com.example.rajiv.gameof15;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class soundsetting extends AppCompatActivity {
    Button mario, gun, bird, home, gunfinalize, mariofinalize, birdfinalize;

    private ProgressBar spin;
    SQLiteDatabase sounddb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundsetting);
        sounddb =openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        spin = (ProgressBar) findViewById(R.id.spinn);
        spin.setVisibility(View.INVISIBLE);
        mario = (Button) findViewById(R.id.mario);
        bird = (Button) findViewById(R.id.bird);
        birdfinalize = (Button) findViewById(R.id.birdfinalise);
        gun = (Button) findViewById(R.id.gun);
        home = (Button) findViewById(R.id.home);
        gunfinalize = (Button) findViewById(R.id.gunfinalise);
        mariofinalize = (Button) findViewById(R.id.mariofinalise);






    }

    public void mario(View view) {

        final MediaPlayer mar = MediaPlayer.create(soundsetting.this, R.raw.supermario);
        bird.setVisibility(View.INVISIBLE);
        gun.setVisibility(View.INVISIBLE);
        mar.start();
        mar.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                bird.setVisibility(View.VISIBLE);
                gun.setVisibility(View.VISIBLE);
               // mar.release();
            }
        });

    }

    public void bird(View view) {

        mario.setVisibility(View.INVISIBLE);
        gun.setVisibility(View.INVISIBLE);
        final MediaPlayer bi = MediaPlayer.create(soundsetting.this, R.raw.bird);
        bi.start();
        bi.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mario.setVisibility(View.VISIBLE);
                gun.setVisibility(View.VISIBLE);
               // bi.release();

            }
        });

    }

    public void gun(View view) {
       Log.i("imhere","kooop");
        bird.setVisibility(View.INVISIBLE);
        mario.setVisibility(View.INVISIBLE);
        final MediaPlayer gu = MediaPlayer.create(soundsetting.this, R.raw.gun);
        gu.start();
        gu.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                bird.setVisibility(View.VISIBLE);
                mario.setVisibility(View.VISIBLE);
              //  gu.release();
            }
        });

    }

    public void home(View view) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    private class finalise extends AsyncTask<Integer,Void,Void>

    {







        @Override
        protected Void doInBackground(Integer... score) {
            publishProgress();
            sounddb.execSQL("CREATE TABLE IF NOT EXISTS soundopt ( 'Sound' VARCHAR)");





            if(score[0]==1)
            {
                sounddb.delete("soundopt", null, null);
                sounddb.execSQL("Insert INTO soundopt VALUES('mario')");
            }
            else if(score[0]==2)
            {
                sounddb.delete("soundopt", null, null);
                sounddb.execSQL("Insert INTO soundopt VALUES('gun')");
            }
            else
            {
                sounddb.delete("soundopt", null, null);
                sounddb.execSQL("Insert INTO soundopt VALUES('bird')");
            }



            return null;
        }
        protected void onProgressUpdate(Void x){spin.setVisibility(View.VISIBLE);}

        @Override
        protected void onPostExecute (Void y){spin.setVisibility(View.INVISIBLE);
            Toast t = Toast.makeText(soundsetting.this,"Updated successfully",Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER|Gravity.BOTTOM,0,30);
            t.show();
            Cursor set1 = sounddb.rawQuery("select * from soundopt",null);
        set1.moveToFirst();
        Log.i("slova",set1.getString(0));}

        //Log.i("yaaaay", set1.getString(0)+"");




    }

    public void mariofinalise(View view) {
        Log.i("mariofinal","ji");
        finalise taskw = new finalise();
        taskw.execute(1);

    }
    public void gunfinalise(View view) {

        finalise taskw = new finalise();
        taskw.execute(2);

    }
    public void birdfinalise(View view) {
        finalise taskw = new finalise();
        taskw.execute(3);

    }
    public void onBackPressed()
    {

    }


}

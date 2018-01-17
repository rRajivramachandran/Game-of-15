package com.example.rajiv.gameof15;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class splash extends AppCompatActivity {
    SQLiteDatabase mydatabase;
    String level;
    long score;
    RelativeLayout q;
    ImageView impact,hammer;
    Intent t;
    TextView leader,congrads;
    int green = Color.argb(255,0,255,0);
    int white = Color.argb(255,255,255,165);
    int red = Color.argb(255,255,0,0);
    int blue = Color.argb(255,0,0,255);
    int black = Color.argb(255,0,0,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congrats_splash);
        congrads = (TextView) findViewById(R.id.congrads);
        leader = (TextView) findViewById(R.id.congo);
        impact  =(ImageView) findViewById(R.id.impact);
        congrads.setVisibility(View.INVISIBLE);
        leader.setVisibility(View.INVISIBLE);


        hammer = (ImageView) findViewById(R.id.winhammer);
        hammer.setVisibility(View.INVISIBLE);
        Bundle p = getIntent().getExtras();
        score = p.getLong("SCORE");
        level = p.getString("LEVEL");

        mydatabase=  openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);
        checkbase task = new checkbase();
        q  = (RelativeLayout) findViewById(R.id.lay);
        impact.setVisibility(View.INVISIBLE);
        t = new Intent(this,congrats.class);

        t.putExtra
                ("SCORE",score);
        t.putExtra("LEVEL",level);

        task.execute(score);

    }

    private class checkbase extends AsyncTask<Long,Void,Void>
    {
        Cursor set1;
        long status;
        @Override
        protected Void doInBackground(Long...score) {

            publishProgress();
            Log.i("tesrttt", score[0] + "");

        if(level.equals("easy"))

        {
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Toppereasy ( 'NAME' VARCHAR,'TIME' INTEGER)");
            set1 = mydatabase.rawQuery("Select * from Toppereasy Order By TIME ", null);
        }
        else if(level.equals("medium"))
        {
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Toppermed ( 'NAME' VARCHAR,'TIME' INTEGER)");
            set1 = mydatabase.rawQuery("Select * from Toppermed Order By TIME ", null);
        }
        else
        {
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Topperhard ( 'NAME' VARCHAR,'TIME' INTEGER)");
            set1 = mydatabase.rawQuery("Select * from Topperhard Order By TIME ", null);
        }

            Log.i("JIYUUUU:",""+set1.getCount());

            if (set1.getCount() < 10)
                status = 1;

            else {
                set1.moveToFirst();

                for (int i = 1; i <= 9; i++)
                    set1.moveToNext();

                if (set1.getInt(1) > score[0])//insert
                    status = 1;
                else
                    status = -1;


                set1.close();

            }

            return null;

        }
            protected void onProgressUpdate(Void x){}

            @Override
            protected void onPostExecute (Void y){



                final Animation anime = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeinout);

                final Animation animatorhammer = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                hammer.startAnimation(animatorhammer);
                final MediaPlayer mediaPlayerhappy = MediaPlayer.create(splash.this, R.raw.happy);
                final MediaPlayer mediaPlayersad = MediaPlayer.create(splash.this, R.raw.sad);


              //  MediaPlayer play = new MediaPlayer(getApplicationContext());

                anime.setRepeatCount(6);
                Log.i("KILLL",status+""+score+""+level);
                animatorhammer.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    if(status==-1)
                        mediaPlayersad.start();
                        else
                            mediaPlayerhappy.start();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if(status!=-1) {
                            impact.setVisibility(View.VISIBLE);
                            q.setBackgroundResource(R.drawable.congo);
                            congrads.setAnimation(anime);
                            leader.setAnimation(anime);

                            hammer.setVisibility(View.VISIBLE);

                        }
                        else
                        {

                            impact.setVisibility(View.VISIBLE);
                            q.setBackgroundResource(R.drawable.fail);
                            congrads.setAnimation(anime);
                            congrads.setText("ITS OK");
                            leader.setAnimation(anime);
                            leader.setTextColor(white);
                            leader.setText("NEXT TIME");
                            hammer.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

             anime.setAnimationListener(new Animation.AnimationListener() {
                 @Override
                 public void onAnimationStart(Animation animation) {

                 }

                 @Override
                 public void onAnimationEnd(Animation animation) {
                     congrads.setVisibility(View.VISIBLE);
                     leader.setVisibility(View.VISIBLE);
                     if(status==-1) {
                         mediaPlayersad.stop();
                         mediaPlayersad.release();
                     }
                     else
                     {
                         mediaPlayerhappy.stop();
                         mediaPlayerhappy.release();
                     }
                    startActivity(t);

                 }

                 @Override
                 public void onAnimationRepeat(Animation animation) {

                 }
             });


            }

            }
    public void onBackPressed()
    {

    }
        }


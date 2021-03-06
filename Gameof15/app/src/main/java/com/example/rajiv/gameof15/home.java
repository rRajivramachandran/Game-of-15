package com.example.rajiv.gameof15;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import static com.example.rajiv.gameof15.R.id.c11;

public class home extends AppCompatActivity {
    public int[][] board = new int[4][4];
    TextView best;
    int home=-1;
    public int blank_pos_x, blank_pos_y,win=-1;
    TextView t00, t01, t02, t03, t10, t11, t12, t13, t20, t21, t22, t23, t30, t31, t32, t33,time,relax,timereamin;
    ImageView timeicon;
    int blue = Color.argb(255,0,0,255);
    int white = Color.argb(255,255,255,255);
    int red = Color.argb(255,255,0,0);
    LinearLayout fina;
    int flag =1;
    Button homeid;
    String sound;
    Cursor set2;
    TextView ins;
    SQLiteDatabase database,topbase;
    long win_tim;
    boolean is_board_assigned=false;
    int win_time_set=0;
    int moves,timez;
    Animation animation;
    ProgressBar tip;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i, j;
       Bundle x = getIntent().getExtras();
        moves = x.getInt("moves");
        timez = x.getInt("time");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        timeicon=(ImageView) findViewById(R.id.icontime);
        database =openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS soundopt ( 'Sound' VARCHAR)");
        set2 = database.rawQuery("Select * from soundopt",null);
        set2.moveToFirst();
        topbase  =openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        best = (TextView) findViewById(R.id.best);
        best.setVisibility(View.INVISIBLE);
        ins = (TextView) findViewById(R.id.ins);
        ins.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeinout);



     //   timeicon.startAnimation(animation);

        tip = (ProgressBar) findViewById(R.id.tip);
        tip.setVisibility(View.INVISIBLE);
        t00 = (TextView) findViewById(R.id.c00);
        t01 = (TextView) findViewById(R.id.c01);
        t02 = (TextView) findViewById(R.id.c02);
        t03 = (TextView) findViewById(R.id.c03);
        t10 = (TextView) findViewById(R.id.c10);
        t11 = (TextView) findViewById(c11);
        t12 = (TextView) findViewById(R.id.c12);
        t13 = (TextView) findViewById(R.id.c13);
        t20 = (TextView) findViewById(R.id.c20);
        t21 = (TextView) findViewById(R.id.c21);
        t22 = (TextView) findViewById(R.id.c22);
        t23 = (TextView) findViewById(R.id.c23);
        t30 = (TextView) findViewById(R.id.c30);
        t31 = (TextView) findViewById(R.id.c31);
        t32 = (TextView) findViewById(R.id.c32);
        t33 = (TextView) findViewById(R.id.c33);
        time = (TextView) findViewById(R.id.timeremain);
        fina = (LinearLayout) findViewById(R.id.fina);
        start = (Button) findViewById(R.id.begin);
        relax = (TextView) findViewById(R.id.relax);
        homeid = (Button) findViewById(R.id.homeid);

       // time.startAnimation(animation);
        fina.setBackgroundResource(R.drawable.game);
        relax.setVisibility(View.GONE);
        //board_assign(moves);
        Log.i("movezzz",moves+" ");

      makegone();


    }
    public void letit(View view)
    {
      start.setVisibility(View.GONE);
        timeicon.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        timeicon.startAnimation(animation);
        time.setAnimation(animation);
        board_assign(moves);
        ins.setVisibility(View.GONE);
            fetch_top best = new fetch_top();
            best.execute(moves);



        is_board_assigned=true;
        new CountDownTimer(timez*1000,1000)
        {
            public void onTick(long time_remain)
            {
                if(win!=1)
                    time.setText("You have:"+time_remain/1000+"s");
                else {
                    if(win_time_set==0)
                    {

                        win_tim = (timez*1000 - time_remain) / 1000;
                        win_time_set=1;

                        //time.setText("Your time:" +win_tim);
                        // tip.setVisibility(View.VISIBLE);
                        //relax.setVisibility(View.VISIBLE);
                        //fina.setBackgroundColor(blue);
                        execute1();
                    }
                    // relax.setText("Hold ON for:"+time_remain/1000+"s");





                }

            }

            @Override
            public void onFinish() {
                time.setText("Time up");
                fina.setBackgroundResource(R.drawable.alt);
                if(check_win()!=true&&home==-1) {
                    final MediaPlayer ring = MediaPlayer.create(home.this, R.raw.end);

                    ring.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer player) {
                            player.start();
                        }
                    });
                    ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            ring.release();
                        }
                    });
                }
                flag=-1;
                homeid.setVisibility(View.VISIBLE);


            }



        }.start();
    }
    public void makegone()
    {
        time.setVisibility(View.INVISIBLE);
        timeicon.setVisibility(View.INVISIBLE);
        t00.setVisibility(View.INVISIBLE);
        t01.setVisibility(View.INVISIBLE);
        t02.setVisibility(View.INVISIBLE);
        t03.setVisibility(View.INVISIBLE);
        t10.setVisibility(View.INVISIBLE);
        t11.setVisibility(View.INVISIBLE);
        t12.setVisibility(View.INVISIBLE);
        t13.setVisibility(View.INVISIBLE);
        t20.setVisibility(View.INVISIBLE);
        t21.setVisibility(View.INVISIBLE);
        t22.setVisibility(View.INVISIBLE);
        t23.setVisibility(View.INVISIBLE);
        t30.setVisibility(View.INVISIBLE);
        t31.setVisibility(View.INVISIBLE);
        t32.setVisibility(View.INVISIBLE);
        t33.setVisibility(View.INVISIBLE);

    }
    public void execute1()
    {
      /*  if(win!=1)
        {
            time.setText("Time up");
            fina.setBackgroundColor(red);
            flag=-1;
            homeid.setVisibility(View.VISIBLE);
        }*/

            Log.i("wintim",win_tim+" ");
            String level;
            if(moves==4)
             level="easy";
            else if(moves==7)
                level="medium";
            else
                level="hard";
            Intent t = new Intent(this,splash.class);
            t.putExtra
                    ("SCORE",win_tim);
            t.putExtra("LEVEL",level);
            startActivity(t);

    }

    public void homeclick(View view)
    {
        Intent q = new Intent(this,MainActivity.class);
        startActivity(q);
        home=0;
    }
    public void onBackPressed()
    {

    }

    public void c00(View view) {

        Log.i("hided",is_board_assigned+"");

        if (blank_pos_x == 0 && blank_pos_y == 1)
            move_right(0, 0);

         else if (blank_pos_x == 1 && blank_pos_y == 0)
            move_down(0, 0);



    }

    public void c01(View view) {
        if(is_board_assigned)


        if (blank_pos_x == 0 && blank_pos_y == 0) {
            move_left(0, 1);


        } else if (blank_pos_x == 0 && blank_pos_y == 2) {
            move_right(0, 1);

        } else if (blank_pos_x == 1 && blank_pos_y == 1) {
            move_down(0, 1);

        }

    }

    public void c02(View view) {


        if (blank_pos_x == 0 && blank_pos_y == 1) {
            move_left(0, 2);


        } else if (blank_pos_x == 0 && blank_pos_y == 3) {
            move_right(0, 2);

        } else if (blank_pos_x == 1 && blank_pos_y == 2) {
            move_down(0, 2);

        }

    }

    public void c03(View view) {


        if (blank_pos_x == 0 && blank_pos_y == 2) {
            move_left(0, 3);

        } else if (blank_pos_x == 1 && blank_pos_y == 3) {
            move_down(0, 3);

        }


    }

    public void c10(View view) {


        if (blank_pos_x == 1 && blank_pos_y == 1) {
            move_right(1, 0);

        } else if (blank_pos_x == 0 && blank_pos_y == 0) {
            move_up(1, 0);



        } else if (blank_pos_x == 2 && blank_pos_y == 0) {
            move_down(1, 0);

        }


    }


    public void c11(View view) {

        if (blank_pos_x == 0 && blank_pos_y == 1) {
            move_up(1, 1);

        } else if (blank_pos_x == 1 && blank_pos_y == 2) {
            move_right(1, 1);

        } else if (blank_pos_x == 2 && blank_pos_y == 1) {
            move_down(1, 1);

        } else if (blank_pos_x == 1 && blank_pos_y == 0) {
            move_left(1, 1);

        }


    }

    public void c12(View view) {


        if (blank_pos_x == 0 && blank_pos_y == 2) {
            move_up(1, 2);

        } else if (blank_pos_x == 1 && blank_pos_y == 3) {
            move_right(1, 2);

        } else if (blank_pos_x == 2 && blank_pos_y == 2) {
            move_down(1, 2);

        } else if (blank_pos_x == 1 && blank_pos_y == 1) {
            move_left(1, 2);

        }


    }

    public void c13(View view) {


        if (blank_pos_x == 0 && blank_pos_y == 3) {
            move_up(1, 3);

        } else if (blank_pos_x == 2 && blank_pos_y == 3) {
            move_down(1, 3);

        } else if (blank_pos_x == 1 && blank_pos_y == 2) {
            move_left(1, 3);

        }


    }

    public void c20(View view) {


        if (blank_pos_x == 1 && blank_pos_y == 0) {
            move_up(2, 0);

        } else if (blank_pos_x == 2 && blank_pos_y == 1) {
            move_right(2, 0);

        } else if (blank_pos_x == 3 && blank_pos_y == 0) {
            move_down(2, 0);

        }


    }

    public void c21(View view) {


        if (blank_pos_x == 2 && blank_pos_y == 0) {
            move_left(2, 1);

        } else if (blank_pos_x == 2 && blank_pos_y == 2) {
            move_right(2, 1);

        } else if (blank_pos_x == 1 && blank_pos_y == 1) {
            move_up(2, 1);

        } else if (blank_pos_x == 3 && blank_pos_y == 1) {
            move_down(2, 1);

        }


    }

    public void c22(View view) {


        if (blank_pos_x == 1 && blank_pos_y == 2) {
            move_up(2, 2);

        } else if (blank_pos_x == 2 && blank_pos_y == 3) {
            move_right(2, 2);

        } else if (blank_pos_x == 3 && blank_pos_y == 2) {
            move_down(2, 2);

        } else if (blank_pos_x == 2 && blank_pos_y == 1) {
            move_left(2, 2);

        }


    }


    public void c23(View view) {


        if (blank_pos_x == 1 && blank_pos_y == 3) {
            move_up(2, 3);

        } else if (blank_pos_x == 2 && blank_pos_y == 2) {
            move_left(2, 3);

        } else if (blank_pos_x == 3 && blank_pos_y == 3) {
            move_down(2, 3);

        }


    }

    public void c30(View view) {


        if (blank_pos_x == 2 && blank_pos_y == 0) {
            move_up(3, 0);

        } else if (blank_pos_x == 3 && blank_pos_y == 1) {
            move_right(3, 0);

        }


    }

    public void c31(View view) {


        if (blank_pos_x == 2 && blank_pos_y == 1) {
            move_up(3, 1);

        } else if (blank_pos_x == 3 && blank_pos_y == 2) {
            move_right(3, 1);

        } else if (blank_pos_x == 3 && blank_pos_y == 0) {
            move_left(3, 1);

        }


    }

    public void c32(View view) {


        if (blank_pos_x == 2 && blank_pos_y == 2) {
            move_up(3, 2);

        } else if (blank_pos_x == 3 && blank_pos_y == 3) {
            move_right(3, 2);

        } else if (blank_pos_x == 3 && blank_pos_y == 1) {
            move_left(3, 2);

        }


    }

    public void c33(View view) {


        if (blank_pos_x == 2 && blank_pos_y == 3) {
            move_up(3, 3);

        } else if (blank_pos_x == 3 && blank_pos_y == 2) {
            move_left(3, 3);

        }


    }


    public void board_assign(int shuffle_count) {
        int i, j, k, temp;
        blank_pos_x = 3;
        blank_pos_y = 3;
        k = 1;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                board[i][j] = k;
                k++;
            }

        }

        board[3][3] = 0;
        k = 1;
        while (k <= shuffle_count) {
            int deg_freedom, movement;
            Random ran = new Random();
            int gen;
            if ((blank_pos_x == 0 || blank_pos_x == 3) && (blank_pos_y == 0 || blank_pos_y == 3)) {
                deg_freedom = 2;
                gen = ran.nextInt(deg_freedom) + 1;
                if (gen == 1)//up or down
                {
                    if (blank_pos_x - 1 == -1)//move down
                    {
                        move_down(blank_pos_x, blank_pos_y);
                        blank_pos_x += 1;
                    } else {
                        move_up(blank_pos_x, blank_pos_y);
                        blank_pos_x -= 1;

                    }
                }
                if (gen == 2) {
                    if (blank_pos_y - 1 == -1)//move right
                    {
                        move_right(blank_pos_x, blank_pos_y);
                        blank_pos_y += 1;

                    } else {
                        move_left(blank_pos_x, blank_pos_y);
                        blank_pos_y -= 1;

                    }
                }
            } else if (blank_pos_x == 0 || blank_pos_x == 3 || blank_pos_y == 0 || blank_pos_y == 3) {
                deg_freedom = 3;
                gen = ran.nextInt(deg_freedom) + 1;
                if (gen == 1) {
                    if (blank_pos_x == 0 || blank_pos_x == 3) {
                        move_left(blank_pos_x, blank_pos_y);
                        blank_pos_y -= 1;
                    } else {
                        move_up(blank_pos_x, blank_pos_y);
                        blank_pos_x -= 1;

                    }

                } else if (gen == 2) {
                    if (blank_pos_x == 0 || blank_pos_x == 3) {
                        move_right(blank_pos_x, blank_pos_y);
                        blank_pos_y += 1;
                    } else {
                        move_down(blank_pos_x, blank_pos_y);
                        blank_pos_x += 1;

                    }
                } else {
                    if (blank_pos_x - 1 == -1) {
                        move_down(blank_pos_x, blank_pos_y);
                        blank_pos_x += 1;
                    } else if (blank_pos_x + 1 == 4) {
                        move_up(blank_pos_x, blank_pos_y);
                        blank_pos_x -= 1;
                    } else if (blank_pos_y == 0) {
                        move_right(blank_pos_x, blank_pos_y);
                        blank_pos_y += 1;
                    } else {
                        move_left(blank_pos_x, blank_pos_y);
                        blank_pos_y -= 1;
                    }
                }

            } else {
                gen = ran.nextInt(4) + 1;
                if (gen == 1) {
                    move_up(blank_pos_x, blank_pos_y);
                    blank_pos_x -= 1;
                } else if (gen == 2) {
                    move_down(blank_pos_x, blank_pos_y);
                    blank_pos_x += 1;
                } else if (gen == 3) {
                    move_left(blank_pos_x, blank_pos_y);
                    blank_pos_y -= 1;

                } else {
                    move_right(blank_pos_x, blank_pos_y);
                    blank_pos_y += 1;
                }

            }


            k++;
            Log.i("X:" + blank_pos_x + " Y:" + blank_pos_y, "post");

        }
        display();


    }

    public void move_up(int x, int y) {
        int temp;
        sound_control();
        temp = board[x][y];
        board[x][y] = board[x - 1][y];
        board[x - 1][y] = temp;
        display();

    }

    public void move_down(int x, int y) {
        int temp;
        sound_control();

        temp = board[x][y];
        board[x][y] = board[x + 1][y];
        board[x + 1][y] = temp;
        display();
    }

    public void move_left(int x, int y) {
        int temp;
        sound_control();

        temp = board[x][y];
        board[x][y] = board[x][y - 1];
        board[x][y - 1] = temp;
        display();
    }

    public void move_right(int x, int y) {
        int temp;
        sound_control();
        temp = board[x][y];
        board[x][y] = board[x][y + 1];
        board[x][y + 1] = temp;
        display();
    }

    public void sound_control()
    {
        if(set2.getCount()!=0) {
            Log.i("testf", set2.getString(0));
            if (set2.getString(0).equals("mario")) {
                final MediaPlayer sound_effect = MediaPlayer.create(this, R.raw.supermario);
                if(is_board_assigned&&sound_effect.isPlaying()==false)
                    sound_effect.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer player) {
                            player.start();
                        }

                    });
                sound_effect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound_effect.release();
                    }
                });

            }
            else if (set2.getString(0).equals("bird"))
            {
                final MediaPlayer sound_effect = MediaPlayer.create(this, R.raw.bird);
                if(is_board_assigned&&sound_effect.isPlaying()==false)
                    sound_effect.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer player) {
                            player.start();
                        }
                    });
                sound_effect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound_effect.release();
                    }
                });
            }
            else
            {
                final MediaPlayer sound_effect = MediaPlayer.create(this, R.raw.gun);
                if(is_board_assigned&&sound_effect.isPlaying()==false)
                    sound_effect.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer player) {
                            player.start();
                        }
                    });
                sound_effect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        sound_effect.release();
                    }
                });
            }

        }
        else{//default sound
            final MediaPlayer sound_effect = MediaPlayer.create(this, R.raw.gun);
            if(is_board_assigned&&sound_effect.isPlaying()==false)
                sound_effect.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer player) {
                        player.start();
                    }
                });
            sound_effect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    sound_effect.release();
                }
            });
        }



    }

    public void display() {

        t00.setText(board[0][0] + "");
        t01.setText(board[0][1] + "");
        t02.setText(board[0][2] + "");
        t03.setText(board[0][3] + "");
        t10.setText(board[1][0] + "");
        t11.setText(board[1][1] + "");
        t12.setText(board[1][2] + "");
        t13.setText(board[1][3] + "");
        t20.setText(board[2][0] + "");
        t21.setText(board[2][1] + "");
        t22.setText(board[2][2] + "");
        t23.setText(board[2][3] + "");
        t30.setText(board[3][0] + "");
        t31.setText(board[3][1] + "");
        t32.setText(board[3][2] + "");
        t33.setText(board[3][3] + "");

        t00.setVisibility(View.VISIBLE);
        t01.setVisibility(View.VISIBLE);
        t02.setVisibility(View.VISIBLE);
        t03.setVisibility(View.VISIBLE);
        t10.setVisibility(View.VISIBLE);
        t11.setVisibility(View.VISIBLE);
        t12.setVisibility(View.VISIBLE);
        t13.setVisibility(View.VISIBLE);
        t20.setVisibility(View.VISIBLE);
        t21.setVisibility(View.VISIBLE);
        t22.setVisibility(View.VISIBLE);
        t23.setVisibility(View.VISIBLE);
        t30.setVisibility(View.VISIBLE);
        t31.setVisibility(View.VISIBLE);
        t32.setVisibility(View.VISIBLE);
        t33.setVisibility(View.VISIBLE);

        if(board[0][0]==0)
        {
            t00.setVisibility(View.INVISIBLE);
            blank_pos_x=0;
            blank_pos_y=0;
        }
        else if(board[0][1]==0)
        {
            t01.setVisibility(View.INVISIBLE);
            blank_pos_x=0;
            blank_pos_y=1;
        }
        else if(board[0][2]==0)
        {
            t02.setVisibility(View.INVISIBLE);
            blank_pos_x=0;
            blank_pos_y=2;
        }
        else if(board[0][3]==0)
        {
            t03.setVisibility(View.INVISIBLE);
            blank_pos_x=0;
            blank_pos_y=3;
        }
        else if(board[1][0]==0)
        {
            t10.setVisibility(View.INVISIBLE);
            blank_pos_x=1;
            blank_pos_y=0;
        }
        else if(board[1][1]==0)
        {
            t11.setVisibility(View.INVISIBLE);
            blank_pos_x=1;
            blank_pos_y=1;
        }
        else if(board[1][2]==0)
        {
            t12.setVisibility(View.INVISIBLE);
            blank_pos_x=1;
            blank_pos_y=2;
        }
        else if(board[1][3]==0)
        {
            t13.setVisibility(View.INVISIBLE);
            blank_pos_x=1;
            blank_pos_y=3;
        }
        else if(board[2][0]==0)
        {
            t20.setVisibility(View.INVISIBLE);
            blank_pos_x=2;
            blank_pos_y=0;
        }
        else if(board[2][1]==0)
        {
            t21.setVisibility(View.INVISIBLE);
            blank_pos_x=2;
            blank_pos_y=1;
        }
        else if(board[2][2]==0)
        {
            t22.setVisibility(View.INVISIBLE);
            blank_pos_x=2;
            blank_pos_y=2;
        }
        else if(board[2][3]==0)
        {
            t23.setVisibility(View.INVISIBLE);
            blank_pos_x=2;
            blank_pos_y=3;
        }
        else if(board[3][0]==0)
        {
            t30.setVisibility(View.INVISIBLE);
            blank_pos_x=3;
            blank_pos_y=0;
        }
        else if(board[3][1]==0)
        {
            t31.setVisibility(View.INVISIBLE);
            blank_pos_x=3;
            blank_pos_y=1;
        }
        else if(board[3][2]==0)
        {
            t32.setVisibility(View.INVISIBLE);
            blank_pos_x=3;
            blank_pos_y=2;
        }
        else
        {
            t33.setVisibility(View.INVISIBLE);
            blank_pos_x=3;
            blank_pos_y=3;
        }

        if(check_win()==true)
        {

            Log.i("hereeee","jkknk");

        }
        else
        {
            Log.i("FAILLL","nn");
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                    Log.i("Board:::"+i+":"+j+":"+board[i][j],"mmnn");
            }
        }

    }


    public boolean check_win() {
        int i, j, k = 1;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if(k<=15) {


                    if (board[i][j] != k)
                        return false;
                    else
                        k++;

                }
            }
        }
        win=1;
        if(flag==1)
            return true;
        else
            return false;
    }
private class fetch_top extends AsyncTask<Integer,Void,Long>
{

    @Override
    protected Long doInBackground(Integer... difficulty) {

        if(difficulty[0]==2)
        {
            topbase.execSQL("CREATE TABLE IF NOT EXISTS Toppereasy ( 'NAME' VARCHAR,'TIME' INTEGER)");
            Cursor set1 = topbase.rawQuery("Select * from Toppereasy Order By TIME ", null);
            set1.moveToFirst();
            if(set1.getCount()==0)
                return (long)(-1);
            else
                return set1.getLong(1);
        }
        else if(difficulty[0]==4)
        {
            topbase.execSQL("CREATE TABLE IF NOT EXISTS Toppermed ( 'NAME' VARCHAR,'TIME' INTEGER)");
            Cursor set1 = topbase.rawQuery("Select * from Toppermed Order By TIME ", null);
            set1.moveToFirst();
            if(set1.getCount()==0)
                return (long)(-1);
            else
            return set1.getLong(1);
        }
        else
        {
            topbase.execSQL("CREATE TABLE IF NOT EXISTS Topperhard ( 'NAME' VARCHAR,'TIME' INTEGER)");
            Cursor set1 = topbase.rawQuery("Select * from Topperhard Order By TIME ", null);
            set1.moveToFirst();
            if(set1.getCount()==0)
                return (long)(-1);
            else
                return set1.getLong(1);
        }


    }
    @Override
    protected void onPostExecute (Long y) {
        if(y!=-1)
        {
            best.setVisibility(View.VISIBLE);
            best.setText("Your best:"+y+" s");
        }

        else
            best.setVisibility(View.INVISIBLE);
    }
    }
}


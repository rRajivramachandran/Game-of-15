package com.example.rajiv.gameof15;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class top_scorers extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    players[] top = new players[11];
    players temp;
    EditText name;
    long new_entry_time;
    String new_entry_name;
    Button name_entry;
    private ProgressBar spinner;
    checkdb task = new checkdb();
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Bundle x = getIntent().getExtras();
        long score = x.getLong("SCORE");
        mydatabase=  openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);
        new_entry_time = score;
        setContentView(R.layout.activity_top_scorers);
        t1 = (TextView) findViewById(R.id.one);
        t2 = (TextView) findViewById(R.id.two);
        t3 = (TextView) findViewById(R.id.three);
        t4 = (TextView) findViewById(R.id.four);
        t5 = (TextView) findViewById(R.id.five);
        t6 = (TextView) findViewById(R.id.six);
        t7 = (TextView) findViewById(R.id.seven);
        t8 = (TextView) findViewById(R.id.eight);
        t9 = (TextView) findViewById(R.id.nine);
        t10 = (TextView) findViewById(R.id.ten);
        spinner = (ProgressBar) findViewById(R.id.spin);
        spinner.setVisibility(View.GONE);
        new_entry_name = x.getString("Name");
        checkdb taskx = new checkdb();
        taskx.execute();


    }



    private class checkdb extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {

            publishProgress();

            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Topper ( 'NAME' VARCHAR,'TIME' INTEGER)");


                String ins = "INSERT INTO Topper VALUES('"+new_entry_name+"','"+new_entry_time+"')";

                Cursor set1 = mydatabase.rawQuery("Select * from Topper Order By TIME ",null);

                if(set1.getCount()<10)
                    mydatabase.execSQL(ins);

                else {
                    set1.moveToFirst();

                    for (int i = 1; i <= 9; i++)
                        set1.moveToNext();
                    if (set1.getInt(1) > new_entry_time)//insert
                    {
                        String delete_name = set1.getString(0);
                        long delete_time = set1.getInt(1);
                        set1 = mydatabase.rawQuery("Select * from Topper Order By TIME ",null);
                        Log.i("before del",set1.getCount()+"");
                        String del = "DELETE FROM Topper Where NAME='"+delete_name+"' AND TIME='"+delete_time+"'";
                        mydatabase.execSQL(del);
                        set1 = mydatabase.rawQuery("Select * from Topper Order By TIME ",null);
                        Log.i("Afterdel",set1.getCount()+"");
                        mydatabase.execSQL(ins);


                    }
                    else
                        Log.i("NO", "NO");

                }
                set1.close();
               return null;
        }
         protected void onProgressUpdate(Void...x)
        {
            spinner.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(Void y)
        {
            spinner.setVisibility(View.GONE);
            Cursor set2 = mydatabase.rawQuery("Select * from Topper Order By TIME ",null);

                set2.moveToNext();
                    t1.setText("1                   "+set2.getInt(1)+"                   "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t2.setText("2                   "+set2.getInt(1)+"                   "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t3.setText("3                   "+set2.getInt(1)+"                   "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t4.setText("4                    "+set2.getInt(1)+"                  "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t5.setText("5                    "+set2.getInt(1)+"                  "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t6.setText("6                    "+set2.getInt(1)+"                  "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t7.setText("7                    "+set2.getInt(1)+"                  "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t8.setText("8                    "+set2.getInt(1)+"                  "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t9.setText("9                    "+set2.getInt(1)+"                  "+set2.getString(0));
                set2.moveToNext();
                if(set2.getPosition()!=set2.getCount())
                    t10.setText("10                   "+set2.getInt(1)+"                 "+set2.getString(0));

            set2.close();
        }
    }
    public void home(View view)
    {
        Intent r = new Intent(this,MainActivity.class);
        startActivity(r);
    }
    public void onBackPressed()
    {

    }

}

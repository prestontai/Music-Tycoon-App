package com.example.prest.musictycoon;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Vector;

public class Goals extends AppCompatActivity {
    Vector<String> goalsInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new startGoals().execute(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        updateText();
    }
    public void updateText(){
        TableLayout table = (TableLayout)findViewById(R.id.table1);
        for( int i = 0; i < goalsInfo.size(); ++i )
        {
            TableRow row = new TableRow(getBaseContext());
            TextView t4 = new TextView(getBaseContext());
            TextView t5 = new TextView(getBaseContext());
            t4.setText(goalsInfo.get(i++));
            t5.setText(goalsInfo.get(i));
            if( i > goalsInfo.size() - 2 )
            {
                t4.setVisibility(View.INVISIBLE);
            }
            t4.setTextColor(Color.rgb(200, 50, 150));
            t5.setTextColor(Color.rgb(200, 150, 150));
            t4.setTextSize(18);
            t5.setTextSize(18);
            row.addView(t4);
            row.addView(t5);
            table.addView(row);
        }
    }
    private class startGoals extends AsyncTask<Integer, Integer, Integer>
    {
        protected Integer doInBackground( Integer ... params) {
            goalsInfo = MainActivity.e.show_goals_v();
            return 0;
        }

    }


}

package com.example.prest.musictycoon;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.*;
import java.util.*;


public class SingleArtist extends AppCompatActivity {
    Vector <String> artInfo;
    Vector <String> discog_info;
    private long lastClick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new startBoards().execute(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_artist);

        updateText();

        Button button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 1000)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                MainActivity.e.ev_choice(1, ManageArtists.currentArtist);
                updateStatic();
            }
        });
        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 1000)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                MainActivity.e.ev_choice(2,ManageArtists.currentArtist);
                updateStatic();
            }
        });
        Button button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 1000)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                MainActivity.e.ev_choice(3,ManageArtists.currentArtist);
                updateStatic();
            }
        });

    }
    public void updateText()
    {
        updateStatic();
        updateArt();
        updateDiscog();
    }
    public void updateStatic()
    {
        TextView tv1 =  findViewById(R.id.tv1);
        TextView tv2 =  findViewById(R.id.tv2);
        tv1.setText("Weeks: " + MainActivity.e.weeks);
        tv2.setText("Balance:\n" + MainActivity.e.myBal);
        Button button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button1.setText(MainActivity.e.inter_req(ManageArtists.currentArtist));
        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button1.setText(MainActivity.e.inter_req(ManageArtists.currentArtist));
        button2.setText(MainActivity.e.mv_req(ManageArtists.currentArtist));
        button3.setText(MainActivity.e.tour_req(ManageArtists.currentArtist));
    }
    public void updateArt()
    {
        TableLayout table = (TableLayout)findViewById(R.id.table1);
//        Vector <String> artInfo = ManageArtists.currentArtist.art_butt();
        for( int i = 0; i < artInfo.size(); ++i )
        {
            TableRow row = new TableRow(getBaseContext());
            TextView t4 = new TextView(getBaseContext());
            TextView t5 = new TextView(getBaseContext());
            t4.setText(artInfo.get(i++));
            t5.setText(artInfo.get(i));
            if( i > 8 )
            {
                t4.setVisibility(View.INVISIBLE);
            }
            t4.setTextColor(Color.rgb(0, 200, 30));
            t5.setTextColor(Color.rgb(0, 200, 30));
            row.addView(t4);
            row.addView(t5);
            table.addView(row);
        }
    }
    public void updateDiscog()
    {
        TextView t6 = findViewById(R.id.tv6);
        t6.setText("Streams    Name                                  Pop   Weeks");
        t6.setTextColor(Color.rgb(0, 150, 250));
        TableLayout scrollTable = (TableLayout)findViewById(R.id.table2);
//        Vector <String> discog_info = MainActivity.e.discog_v(ManageArtists.currentArtist);
        for( int i = 0; i < discog_info.size(); ++i )
        {
            TableRow row = new TableRow(getBaseContext());
            TextView t7 = new TextView(getBaseContext());
            TextView t8 = new TextView(getBaseContext());
            TextView t9 = new TextView(getBaseContext());
            TextView t10 = new TextView(getBaseContext());
            t7.setText(discog_info.get(i++));
            t8.setText(discog_info.get(i++));
            t9.setText(discog_info.get(i++));
            t10.setText(discog_info.get(i));
            t7.setTextColor(Color.rgb(200, 80, 60));
            t8.setTextColor(Color.rgb(200, 80, 60));
            t9.setTextColor(Color.rgb(200, 80, 60));
            t10.setTextColor(Color.rgb(200, 80, 60));
            row.addView(t7);
            row.addView(t8);
            row.addView(t9);
            row.addView(t10);

            scrollTable.addView(row);
        }
    }
    private class startBoards extends AsyncTask<Integer, Integer, Integer>
    {
        protected Integer doInBackground( Integer ... params) {
            discog_info = MainActivity.e.discog_v(ManageArtists.currentArtist);
            artInfo = ManageArtists.currentArtist.art_butt();
            return 0;
        }

    }

}

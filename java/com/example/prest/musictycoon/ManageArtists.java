package com.example.prest.musictycoon;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

public class ManageArtists extends AppCompatActivity {
    public static OArtists currentArtist;
    private long lastClick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_artists);

        updateText();
        Button button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();

                openSingle();
                currentArtist = MainActivity.e.artists_v.get(0);
            }
        });
        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(1);
            }
        });
        Button button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(2);
            }
        });
        Button button4 =  findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(3);
            }
        });
        Button button5 =  findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(4);
            }
        });
        Button button6 =  findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(5);
            }
        });
        Button button7 =  findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(6);
            }
        });
        Button button8 =  findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(7);
            }
        });
        Button button9 =  findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 300)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openSingle();
                currentArtist = MainActivity.e.artists_v.get(8);
            }
        });


    }
    public void updateText()
    {
        Vector<Button> artButt = new Vector<Button>();
        artButt.add((Button)findViewById(R.id.button1));
        artButt.add((Button)findViewById(R.id.button2));
        artButt.add((Button)findViewById(R.id.button3));
        artButt.add((Button)findViewById(R.id.button4));
        artButt.add((Button)findViewById(R.id.button5));
        artButt.add((Button)findViewById(R.id.button6));
        artButt.add((Button)findViewById(R.id.button7));
        artButt.add((Button)findViewById(R.id.button8));
        artButt.add((Button)findViewById(R.id.button9));

        for ( int i = 0; i < MainActivity.e.artists_v.size(); ++i )
        {
            artButt.get(i).setText(MainActivity.e.artists_v.get(i).name + "\nEarned: " + Integer.toString(MainActivity.e.artists_v.get(i).earned ));
        }
        for ( int i = MainActivity.e.artists_v.size(); i < 9; ++i )
        {
            artButt.get(i).setVisibility(View.GONE);
        }
    }
    public void openSingle()
    {
        Intent intent = new Intent(this, SingleArtist.class);
        startActivity(intent);
    }
}

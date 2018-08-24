package com.example.prest.musictycoon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ArtistsMenu extends AppCompatActivity {
    private long lastClick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_menu);

        updateText();
        Button button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 400)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                signArtist();
            }
        });
        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 400)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                manageArtists();
            }
        });
        button1.setText( "Sign Artists");
        button2.setText( "Manage Artists");

    }
    public void updateText() {
        TextView tv1 =  findViewById(R.id.tv1);
        TextView tv2 =  findViewById(R.id.tv2);
        TextView tv3 =  findViewById(R.id.tv3);
        tv1.setText("Weeks: " + MainActivity.e.weeks);
        tv2.setText("Balance:\n" + MainActivity.e.myBal);
        tv3.setText(MainActivity.e.gen_button());
    }
    public void signArtist()
    {
        Intent intent = new Intent(this, SignArtists.class);
        startActivity(intent);
    }
    public void manageArtists()
    {
        Intent intent = new Intent(this, ManageArtists.class);
        startActivity(intent);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        updateText();
    }



}

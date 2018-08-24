package com.example.prest.musictycoon;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import java.lang.*;
import java.util.*;


public class MainMenu extends AppCompatActivity {
    private long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        updateText();
        Button button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTime();
            }
        });
        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 500)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openArtists();
            }
        });
        Button button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 500)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openBillboard();
            }
        });
        Button button4 =  findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 500)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openStudioUpgrade();
            }
        });
        Button button5 =  findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 350)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openGoals();
            }
        });
    }
    @Override
    public void onResume()
    {
        super.onResume();
        updateText();
    }


    public void updateText() {
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        tv1.setText("Weeks: " + MainActivity.e.weeks);
        tv2.setText("Balance:\n" + MainActivity.e.myBal);
    }
    public void passTime(){
        if ( MainActivity.e.weeks < 250 )
        {
            MainActivity.e.pass_time();
        }
        updateText();
    }
    public void openArtists()
    {
        Intent intent = new Intent(this, ArtistsMenu.class);
        startActivity(intent);
    }
    public void openBillboard(){
        Intent intent = new Intent(this, Billboards.class);
        startActivity(intent);
    }
    public void openStudioUpgrade(){
        Intent intent = new Intent(this, StudioUpgrade.class );
        startActivity (intent);
    }
    public void openGoals() {
        Intent intent = new Intent(this, Goals.class);
        startActivity(intent);
    }

}




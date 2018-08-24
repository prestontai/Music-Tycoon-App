package com.example.prest.musictycoon;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

public class SignArtists extends AppCompatActivity {
    Vector <String> s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_artists);

        updateText();
        Button button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose(1);
            }
        });
        Button button2 =  findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose(2);
            }
        });
        Button button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose(3);
            }
        });
        Button button4 =  findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose(4);
            }
        });

    }
    public void updateText() {
        s = MainActivity.e.ret_choices();
        TextView tv1 =  findViewById(R.id.tv1);
        TextView tv2 =  findViewById(R.id.tv2);
        TextView tv3 =  findViewById(R.id.tv3);
        tv1.setText("Weeks: " + MainActivity.e.weeks);
        tv2.setText("Balance:\n" + MainActivity.e.myBal);
        String signAmount = MainActivity.e.artists_v.size() < 9 ? "Cost to sign: " + Integer.toString((int)(MainActivity.e.reset_int() * 2.5)) :
            "Full Capacity";
        tv3.setText( signAmount  + "  \n Artists Signed: " + MainActivity.e.art_capacity());

        Button button1 =  findViewById(R.id.button1);
        Button button2 =  findViewById(R.id.button2);
        Button button3 =  findViewById(R.id.button3);
        Button button4 =  findViewById(R.id.button4);
        button1.setText(s.get(0));
        button2.setText(s.get(1));
        button3.setText(s.get(2));
        button4.setText("Reset choices\n" + Integer.toString((MainActivity.e.reset_int())));
    }
    public void choose( int num )
    {
        MainActivity.e.c_choice(num);
        updateText();
    }


}

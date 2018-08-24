package com.example.prest.musictycoon;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.io.*;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    static Context assm;
    static Engine e;
    private long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AsyncTask thread = new startEngine().execute(0);

        assm = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGame = (Button) findViewById(R.id.startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemClock.elapsedRealtime() - lastClick < 500)
                {
                    return;
                }
                lastClick = SystemClock.elapsedRealtime();
                openMainMenu();
            }
        });

    }
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }



    static Vector<String> init_vector(String filename) {
        Vector <String> sv = new Vector<String>();

        try{
            BufferedReader readFile = new BufferedReader(
                    new InputStreamReader(MainActivity.assm.getAssets().open(filename)));

            String line;

            while ((line = readFile.readLine()) != null) {
                sv.add(line);
            }
            readFile.close();
        }
        catch( IOException e)
        {
            System.out.println(e);
        }
        return sv;
    }

    private class startEngine extends AsyncTask <Integer, Integer, Integer>
    {
        protected Integer doInBackground( Integer ... params) {
            if (!isCancelled())
            {
                Genres.genre_v = MainActivity.init_vector("genres.txt");
                MainActivity.e = new Engine(0);
            }
            return 0;
        }

    }
}


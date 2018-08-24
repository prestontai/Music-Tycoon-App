package com.example.prest.musictycoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;


public class StudioUpgrade extends AppCompatActivity {
    Vector <Button> studButtons = new Vector<Button>();
    private long lastClick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_upgrade);

        studButtons.add( (Button) findViewById(R.id.button1) );
        studButtons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studUpgrade(1);
            }
        });
        studButtons.add( (Button) findViewById(R.id.button2) );
        studButtons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studUpgrade(2);
            }
        });
        studButtons.add( (Button) findViewById(R.id.button3) );
        studButtons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studUpgrade(3);
            }
        });
        studButtons.add( (Button) findViewById(R.id.button4) );
        studButtons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studUpgrade(4);
            }
        });

        updateText();
        Vector <String> prices = MainActivity.e.get_up_price();
        for ( int i = 0; i < MainActivity.e.get_up_price().size(); ++i )
        {
            studButtons.get(i).setText("Upgrade" + prices.get(i));
        }
    }
    public void studUpgrade ( int choice ){
        MainActivity.e.up_choice(choice);
        studButtons.get(choice-1).setText("Upgrade" + MainActivity.e.get_up_price().get(choice - 1));
        updateText();
    }
    public void updateText() {
        TextView tv1 =  findViewById(R.id.tv1);
        TextView tv2 =  findViewById(R.id.tv2);
        tv1.setText("Weeks: " + MainActivity.e.weeks);
        tv2.setText("Balance:\n" + MainActivity.e.myBal);
    }


}

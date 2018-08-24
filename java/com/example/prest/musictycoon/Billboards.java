package com.example.prest.musictycoon;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Vector;

public class Billboards extends AppCompatActivity {

    Vector<String>bbDom;
    Vector<String>bbV;
    Vector<String>mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new startBoards().execute(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboards);

        updateText();
    }
    public void updateText()
    {
        updateDom();
        updateBill();
        updateMine();
    }
    public void updateDom()
    {
//        bbDom = MainActivity.e.bb_dom_v();
        TableLayout table = (TableLayout)findViewById(R.id.table3);
        TableRow rows = new TableRow(this);
        TextView t3 = new TextView(this);
        t3.setText("Billboard Distribution");
        t3.setTextColor(Color.rgb(100, 50, 150));
        rows.addView(t3);
        table.addView(rows);
        for( int i = 0; i < bbDom.size(); ++i )
        {
            TableRow row = new TableRow(this);
            TextView t4 = new TextView(this);
            TextView t5 = new TextView(this);
            t4.setText(bbDom.get(i++));
            t5.setText(bbDom.get(i));
            t4.setTextColor(Color.rgb(100, 50, 150));
            t5.setTextColor(Color.rgb(100, 50, 150));
            row.addView(t4);
            row.addView(t5);
            table.addView(row);
        }
    }
    public void updateBill()
    {
//        bbV = MainActivity.e.billboards_v();
        TextView tvBill = (TextView) findViewById(R.id.tvbillboard);
        tvBill.setText("Billboards                                            Pop    Weeks");
        tvBill.setTextColor(Color.rgb(100, 150, 250));

        TableLayout table = (TableLayout)findViewById(R.id.table1);
        for( int i = 0; i < bbV.size(); ++i )
        {
            TableRow row = new TableRow(this);
            TextView t4 = new TextView(this);
            TextView t5 = new TextView(this);
            TextView t6 = new TextView(this);
            TextView t7 = new TextView(this);
            TextView t8 = new TextView(this);
            t4.setText(bbV.get(i++));
            t5.setText(bbV.get(i++));
            t6.setText(bbV.get(i++));
            t7.setText(bbV.get(i++));
            t8.setText(bbV.get(i));
            t4.setTextColor(Color.rgb(150, 50, 250));
            t5.setTextColor(Color.rgb(150, 50, 250));
            t6.setTextColor(Color.rgb(150, 50, 250));
            t7.setTextColor(Color.rgb(150, 50, 250));
            t8.setTextColor(Color.rgb(150, 50, 250));
            row.addView(t4);
            row.addView(t5);
            row.addView(t6);
            row.addView(t7);
            row.addView(t8);
            table.addView(row);
        }
    }
    public void updateMine()
    {
//        mine = MainActivity.e.smart_board_v();
        TextView tvMine = (TextView) findViewById(R.id.tvmine);
        tvMine.setText("My Songs");
        TextView t10 = (TextView) findViewById(R.id.tvtitle2);
        t10.setText("Streams    Name                               Pop   Weeks");
        t10.setTextColor(Color.rgb(200, 50, 50));
        TableLayout table = (TableLayout)findViewById(R.id.table2);
        for( int i = 0; i < mine.size(); ++i )
        {
            TableRow row = new TableRow(this);
            TextView t4 = new TextView(this);
            TextView t5 = new TextView(this);
            TextView t6 = new TextView(this);
            TextView t7 = new TextView(this);
            t4.setText(mine.get(i++));
            t5.setText(mine.get(i++));
            t6.setText(mine.get(i++));
            t7.setText(mine.get(i));
            t4.setTextColor(Color.rgb(150, 150, 50));
            t5.setTextColor(Color.rgb(150, 150, 50));
            t6.setTextColor(Color.rgb(150, 150, 50));
            t7.setTextColor(Color.rgb(150, 150, 50));
            row.addView(t4);
            row.addView(t5);
            row.addView(t6);
            row.addView(t7);
            table.addView(row);
        }

    }
    private class startBoards extends AsyncTask <Integer, Integer, Integer>
    {
        protected Integer doInBackground( Integer ... params) {
            bbDom = MainActivity.e.bb_dom_v();
            mine = MainActivity.e.smart_board_v();
            bbV = MainActivity.e.billboards_v();
            return 0;
        }

    }

}



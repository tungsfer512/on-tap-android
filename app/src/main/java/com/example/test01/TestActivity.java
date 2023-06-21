package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        TableLayout tableLayout = findViewById(R.id.table);
        TableRow tableRow = new TableRow(tableLayout.getContext());
        TextView view = new TextView(tableRow.getContext());
        TextView view2 = new TextView(tableRow.getContext());

        view.setText("Text 1");
        view2.setText("Text 2");
        view2.setGravity(Gravity.RIGHT);

        tableRow.addView(view);
        tableRow.addView(view2);

        tableLayout.addView(tableRow);


    }
}
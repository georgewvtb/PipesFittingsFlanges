package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class teesDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tees_display);

        Intent intent = getIntent();
        String[] values = (String[])intent.getExtras().get("teesValues");

        TextView teesNominalSize = findViewById(R.id.teesNominalSize);
        teesNominalSize.setText(String.valueOf(values[0]));

        TextView teesCenterToEndA = findViewById(R.id.teesCenterToEndA);
        teesCenterToEndA.setText(String.valueOf(values[1]));

        TextView teesCenterToEndB = findViewById(R.id.teesCenterToEndB);
        teesCenterToEndB.setText(String.valueOf(values[2]));
    }
}
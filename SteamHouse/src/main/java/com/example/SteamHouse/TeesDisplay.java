package com.example.SteamHouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class TeesDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tees_display);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Gets tees input activity's intent
        Intent intent = getIntent();
        // Gets tees input activity's array
        String[] values = (String[])intent.getExtras().get("teesValues");

        // Displays data from array
        TextView teesNominalSize = findViewById(R.id.teesNominalSize);
        teesNominalSize.setText(String.valueOf(values[0]));

        TextView teesCenterToEndA = findViewById(R.id.teesCenterToEndA);
        teesCenterToEndA.setText(String.valueOf(values[1]));

        TextView teesCenterToEndB = findViewById(R.id.teesCenterToEndB);
        teesCenterToEndB.setText(String.valueOf(values[2]));
    }
}
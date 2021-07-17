package com.example.SteamHouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class ElbowsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elbows_display);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Gets elbows input activity's intent
        Intent intent = getIntent();
        // Gets elbows input activity's array
        String[] values = (String[])intent.getExtras().get("values");

        // Displays data from array
        TextView elbowsNominalSize = findViewById(R.id.elbowsNominalSize);
        elbowsNominalSize.setText(String.valueOf(values[0]));

        TextView schedule = findViewById(R.id.schedule);
        schedule.setText(String.valueOf(values[1]));

        TextView elbowsOutsideDiameter = findViewById(R.id.elbowsOutsideDiameter);
        elbowsOutsideDiameter.setText(String.valueOf(values[2]));

        TextView elbowsCenterToEnd = findViewById(R.id.elbowsCenterToEnd);
        elbowsCenterToEnd.setText(String.valueOf(values[3]));

        TextView elbowsInternalDiameter = findViewById(R.id.elbowsInternalDiameter);
        elbowsInternalDiameter.setText(String.valueOf(values[4]));

        TextView elbowsWallThickness = findViewById(R.id.elbowsWallThickness);
        elbowsWallThickness.setText(String.valueOf(values[5]));
    }
}
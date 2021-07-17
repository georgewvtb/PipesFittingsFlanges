package com.example.SteamHouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class PipesDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipes_display);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Gets pipes input activity's intent
        Intent intent = getIntent();
        // Gets pipes input activity's array
        String[] values = (String[])intent.getExtras().get("pipesValues");

        // Displays data from array
        TextView nominalBore = findViewById(R.id.pipesNominalBore);
        nominalBore.setText(String.valueOf(values[0]));

        TextView schedule = findViewById(R.id.schedule);
        schedule.setText(String.valueOf(values[1]));

        TextView outsideDiameter = findViewById(R.id.pipesOutsideDiameter);
        outsideDiameter.setText(String.valueOf(values[2]));

        TextView wallThickness = findViewById(R.id.pipesWallThickness);
        wallThickness.setText(String.valueOf(values[3]));

        TextView internalDiameter = findViewById(R.id.pipesInternalDiameter);
        internalDiameter.setText(String.valueOf(values[4]));
    }
}
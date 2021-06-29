package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class pipesDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipes_display);

        Intent intent = getIntent();
        String[] values = (String[])intent.getExtras().get("pipesValues");

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
package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class elbowsDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elbows_display);

        Intent intent = getIntent();
        String[] values = (String[])intent.getExtras().get("values");

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
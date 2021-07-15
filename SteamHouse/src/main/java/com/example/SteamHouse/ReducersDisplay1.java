package com.example.SteamHouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReducersDisplay1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reducers_display1);

        // Get reducers input activity's intent
        Intent intent = getIntent();
        // Get reducers input activity's array
        String[] values = (String[])intent.getExtras().get("reducersValues");

        // Displays data from array

        TextView reducersNominalSize = findViewById(R.id.reducersNominalSize);
        reducersNominalSize.setText(String.valueOf(values[0]));

        TextView reducersOutsideDiameter = findViewById(R.id.reducersOutsideDiameter);
        reducersOutsideDiameter.setText(String.valueOf(values[1]));

        TextView reducersLength = findViewById(R.id.reducersLength);
        reducersLength.setText(String.valueOf(values[2]));
    }
}
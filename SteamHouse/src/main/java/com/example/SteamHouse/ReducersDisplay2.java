package com.example.SteamHouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReducersDisplay2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reducers_display2);

        // Get reducers input activity's intent
        Intent intent = getIntent();
        // Get reducers input activity's array
        String[] values2 = (String[])intent.getExtras().get("reducersValues2");

        // Displays data from array

        TextView reducersNominalSize = findViewById(R.id.reducersNominalSize);
        reducersNominalSize.setText(String.valueOf(values2[1]));

        TextView reducersOutsideDiameter = findViewById(R.id.reducersOutsideDiameter);
        reducersOutsideDiameter.setText(String.valueOf(values2[2]));

        TextView reducersLength = findViewById(R.id.reducersLength);
        reducersLength.setText(String.valueOf(values2[3]));
    }
}
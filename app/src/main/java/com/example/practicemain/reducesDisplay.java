package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class reducesDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduces_display);

        Intent intent = getIntent();
        String[] values = (String[])intent.getExtras().get("reducersValues");

        TextView reducersNominalSize = findViewById(R.id.reducersNominalSize);
        reducersNominalSize.setText(String.valueOf(values[0]));

        TextView reducersOutsideDiameter = findViewById(R.id.reducersOutsideDiameter);
        reducersOutsideDiameter.setText(String.valueOf(values[1]));

        TextView reducersLength = findViewById(R.id.reducersLength);
        reducersLength.setText(String.valueOf(values[2]));
    }
}
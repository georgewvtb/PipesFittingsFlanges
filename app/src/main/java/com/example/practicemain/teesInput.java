package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class teesInput extends AppCompatActivity {

    InputStream inputStream;

    Map<String, String[]> teesPairValues = new HashMap<>();
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tees_input);

        inputStream = getResources().openRawResource(R.raw.tees);

        BufferedReader teesReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = teesReader.readLine()) != null) {
                data = csvLine.split(";");
                try {
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2]);

                    teesPairValues.put(
                            data[0],
                            new String[]{
                                    String.valueOf(data[0]),
                                    String.valueOf(data[1]),
                                    String.valueOf(data[2]),
                            }
                    );
                } catch (Exception e) {
                    Log.e("Problem", e.toString());
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        }
    }

    public void submitTees(View v) {
        EditText teesNominalSize = findViewById(R.id.nominalSizeTees);

        String teesKey = teesNominalSize.getText().toString();

        String[] teesValues = teesPairValues.get( teesKey );

        if (teesValues != null) {
            Intent teesDisplay = new Intent(this, teesDisplay.class);
            teesDisplay.putExtra("teesValues", teesValues);
            startActivity(teesDisplay);
        } else {

        }
    }
}

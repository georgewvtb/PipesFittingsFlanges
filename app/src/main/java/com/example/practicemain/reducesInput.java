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

public class reducesInput extends AppCompatActivity {

    InputStream inputStream;

    Map<String, String[]> reducersPairValues = new HashMap<>();
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduces_input);

        inputStream = getResources().openRawResource(R.raw.reduces);

        BufferedReader reducersReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reducersReader.readLine()) != null) {
                data = csvLine.split(";");
                try {
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2]);

                    reducersPairValues.put(
                            data[0],
                            new String[]{
                                    String.valueOf(data[0]),
                                    String.valueOf(data[1]),//.replaceAll(",", ".")),
                                    String.valueOf(data[2]),//.replaceAll(",", ".")),
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

    public void submitReducers(View v) {
        EditText reducersNominalSize = findViewById(R.id.nominalSizeReducers);

        String reducersKey = reducersNominalSize.getText().toString();

        String[] reducersValues = reducersPairValues.get( reducersKey );

        if (reducersValues != null) {
            Intent reducersDisplay = new Intent(this, reducesDisplay.class);
            reducersDisplay.putExtra("reducersValues", reducersValues);
            startActivity(reducersDisplay);
        } else {

        }
    }
}

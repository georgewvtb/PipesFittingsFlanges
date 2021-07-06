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
    InputStream inputStream2;

    Map<String, String[]> reducersPairValues = new HashMap<>();
    String[] data;

    Map<String, String[]> reducersPairValues2 = new HashMap<>();
    String[] data2;

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

        inputStream2 = getResources().openRawResource(R.raw.reducers_02);
        BufferedReader reducersReader2 = new BufferedReader(new InputStreamReader(inputStream2));
        try {
            String csvLine2;
            while ((csvLine2 = reducersReader2.readLine()) != null) {
                data2 = csvLine2.split(";");
                try {
                    Log.e("Data2 ", "" + data2[0] + " " + data2[1] + " "
                            + data2[2] + " " + data2[3]);

                    reducersPairValues2.put(
                            data2[0],
                            new String[]{
                                    String.valueOf(data2[0]),
                                    String.valueOf(data2[1]),
                                    String.valueOf(data2[2]),//.replaceAll(",", ".")),
                                    String.valueOf(data2[3]),//.replaceAll(",", ".")),
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
    public void submitReducers (View v){
        try {
            EditText reducers = findViewById(R.id.nominalSizeReducers);
            String reducersKey = reducers.getText().toString();

            String[] reducersValues = reducersPairValues.get(reducersKey);
            String[] reducersValues2 = reducersPairValues2.get(reducersKey);

            if (reducers.length() == 0) {
                reducers.setError("Enter Nominal Size");
            }
            else if (reducersValues == null && reducersValues2 == null) {
                    reducers.setError("Invalid Nominal Size");
            } else {
                if (reducersValues != null) {
                    Intent reducersDisplay = new Intent(this, reducesDisplay.class);
                    reducersDisplay.putExtra("reducersValues", reducersValues);
                    startActivity(reducersDisplay);
                }
                else if (reducersValues2 != null) {
                    Intent reducersDisplay2 = new Intent(this, reducersDisplay2.class);
                    reducersDisplay2.putExtra("reducersValues2", reducersValues2);
                    startActivity(reducersDisplay2);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: " + e);
        }
    }
}


package com.example.SteamHouse;

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

public class TeesInput extends AppCompatActivity {

    // Initializes input stream variable
    InputStream inputStream;

    // Initializes tees hashmap
    Map<String, String[]> teesPairValues = new HashMap<>();

    // Initializes data array
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tees_input);

        // Gets tees csv file
        inputStream = getResources().openRawResource(R.raw.tees);

        // Reads tees csv file
        BufferedReader teesReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // Initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = teesReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2]);

                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    teesPairValues.put(
                            // Key name. Used to retrieve data (values)
                            data[0],
                            // Values. Placed in an array
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
        try {
            // Edit text to enter tees nominal size
            EditText et_teesNominalSize = findViewById(R.id.nominalSizeTees);

            // Converts edit text value to a string
            String teesKey = et_teesNominalSize.getText().toString();

            // Adds selected nominal size and respective data to an array
            String[] teesValues = teesPairValues.get(teesKey);

            // Displays custom error message if nominal size edit text is empty
            if (et_teesNominalSize.length() == 0) {
                et_teesNominalSize.setError("Enter Nominal Size");
                et_teesNominalSize.requestFocus();
            }
            // Displays custom error message if nominal size value is invalid
            else if (teesValues == null) {
                et_teesNominalSize.setError("Invalid Nominal Size");
                et_teesNominalSize.requestFocus();
            }
            // Sends the array to the tees display activity
            else if (teesValues != null) {
                    Intent teesDisplay = new Intent(this, TeesDisplay.class);
                    teesDisplay.putExtra("teesValues", teesValues);
                    startActivity(teesDisplay);
                }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: "+ e);
        }
    }
}

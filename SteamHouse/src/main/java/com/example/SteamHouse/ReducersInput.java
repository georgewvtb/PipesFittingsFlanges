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

public class ReducersInput extends AppCompatActivity {

    // Initializes first input stream variable
    InputStream inputStream;
    // Initializes second input stream variable
    InputStream inputStream2;

    // Two hashmaps were created so that when entering nominal size in the edit text,
    // both (A x B) or (AxB) can be entered

    // Initializes first reducers hashmap
    Map<String, String[]> reducersPairValues = new HashMap<>();
    // Initializes second reducers hashmap
    Map<String, String[]> reducersPairValues2 = new HashMap<>();

    // Initializes first data array
    String[] data;
    // Initializes second data array
    String[] data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reducers_input);

        // Gets reducers csv file
        inputStream = getResources().openRawResource(R.raw.reducers1);

        // Reads reducers csv file
        BufferedReader reducersReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // Initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = reducersReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2]);

                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    reducersPairValues.put(
                            // Key name. Used to retrieve data (values)
                            data[0],
                            // Values. Placed in an array
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

        // Gets second reducers csv file
        inputStream2 = getResources().openRawResource(R.raw.reducers2);

        // Reads second reducers csv file
        BufferedReader reducersReader2 = new BufferedReader(new InputStreamReader(inputStream2));
        try {
            // Initializes second csvLine variable
            String csvLine2;
            // Loops through second csv file. Loop ends when data ends
            while ((csvLine2 = reducersReader2.readLine()) != null) {
                // Splits second csv data in each row
                data2 = csvLine2.split(";");
                try {
                    Log.e("Data2 ", "" + data2[0] + " " + data2[1] + " "
                            + data2[2] + " " + data2[3]);

                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    reducersPairValues2.put(
                            // Key name. Used to retrieve data (values)
                            data2[0],
                            // Values. Placed in an array
                            new String[]{
                                    String.valueOf(data2[0]),
                                    String.valueOf(data2[1]),
                                    String.valueOf(data2[2]),
                                    String.valueOf(data2[3]),
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
            // Edit text to enter reducers nominal size
            EditText et_reducersNominalSize = findViewById(R.id.nominalSizeReducers);

            // Converts edit text value to a string
            String reducersKey = et_reducersNominalSize.getText().toString();

            // Adds selected nominal size and respective data from first hashmap to an array
            String[] reducersValues = reducersPairValues.get(reducersKey);
            // Adds selected nominal size and respective data from second hashmap to a second array
            String[] reducersValues2 = reducersPairValues2.get(reducersKey);

            // Displays custom error message if nominal size edit text is empty
            if (et_reducersNominalSize.length() == 0) {
                et_reducersNominalSize.setError("Enter Nominal Size");
                et_reducersNominalSize.requestFocus();
            }
            // Displays custom error message if nominal size from both arrays are invalid
            else if (reducersValues == null && reducersValues2 == null) {
                    et_reducersNominalSize.setError("Invalid Nominal Size");
                    et_reducersNominalSize.requestFocus();
            } else {
                // Sends first array to the first reducers display activity
                if (reducersValues != null) {
                    Intent reducersDisplay = new Intent(this, ReducersDisplay1.class);
                    reducersDisplay.putExtra("reducersValues", reducersValues);
                    startActivity(reducersDisplay);
                }
                // Sends second array to the second reducers display activity
                else if (reducersValues2 != null) {
                    Intent reducersDisplay2 = new Intent(this, ReducersDisplay2.class);
                    reducersDisplay2.putExtra("reducersValues2", reducersValues2);
                    startActivity(reducersDisplay2);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: " + e);
        }
    }
}


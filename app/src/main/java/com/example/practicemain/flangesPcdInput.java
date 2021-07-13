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

public class flangesPcdInput extends AppCompatActivity {

    // Initializes input stream varaible
    InputStream inputStream;

    // Initializes flanges - pcd hashmap
    Map<String, String[]> flangesPcdPairValues = new HashMap<>();

    // Initializes data array
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_pcd_input2);

        // Gets flanges - pcd csv file
        inputStream = getResources().openRawResource(R.raw.flanges_pcd);

        // Reads flanges - pcd csv file
        BufferedReader flangesPcdReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // Initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = flangesPcdReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {
                    Log.e("Data ",  " " + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4] + " "
                            + data[5] + " " + data[6] + " " + data[7] + " "
                            + data[8] + " " + data[9] + " " + data[10] + " "
                            + data[11] + " " + data[12] + " " + data[13] + " "
                            + data[14] + " " + data[15] + " " + data[16] + " "
                            + data[17] + " " + data[18] + " " + data[19] + " "
                            + data[20] + " " + data[21]);

                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    flangesPcdPairValues.put(
                            // Key name. Used to retrieve data (values)
                            data[0],
                            // Values. Placed in an array
                            new String[]{
                                    String.valueOf(data[0]),
                                    String.valueOf(data[1]),
                                    String.valueOf(data[2]),
                                    String.valueOf(data[3]),
                                    String.valueOf(data[4]),
                                    String.valueOf(data[5]),
                                    String.valueOf(data[6]),
                                    String.valueOf(data[7]),
                                    String.valueOf(data[8]),
                                    String.valueOf(data[9]),
                                    String.valueOf(data[10]),
                                    String.valueOf(data[11]),
                                    String.valueOf(data[12]),
                                    String.valueOf(data[13]),
                                    String.valueOf(data[14]),
                                    String.valueOf(data[15]),
                                    String.valueOf(data[16]),
                                    String.valueOf(data[17]),
                                    String.valueOf(data[18]),
                                    String.valueOf(data[19]),
                                    String.valueOf(data[20]),
                                    String.valueOf(data[21]),
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

    public void submitFlangesPcd(View v) {
        try {
            // Edit text to enter flanges - pcd PCD
            EditText et_flangesPCD = findViewById(R.id.etFlangesPcd);

            // Converts edit text value to a string
            String flangesPcdKey = et_flangesPCD.getText().toString();

            // Adds selected PCD and respective data to an array
            String[] flangesPcdValues = flangesPcdPairValues.get(flangesPcdKey);

            // Displays custom error message if PCD edit text is empty
            if (et_flangesPCD.length() == 0) {
                et_flangesPCD.setError("Enter PCD");
                et_flangesPCD.requestFocus();
            }
            // Displays custom error message if PCD value is invalid
            else if (flangesPcdValues == null) {
                et_flangesPCD.setError("Invalid PCD");
                et_flangesPCD.requestFocus();
            }
            // Sends the array to the flanges - pcd display activity
            else if (flangesPcdValues != null) {
                    Intent flangesPcdDisplay = new Intent(this, flangesPcdDisplay.class);
                    flangesPcdDisplay.putExtra("flangesPcdValues", flangesPcdValues);
                    startActivity(flangesPcdDisplay);
                }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: "+ e);
        }
    }
}

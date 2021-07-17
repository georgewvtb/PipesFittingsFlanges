package com.example.SteamHouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class FlangesPcdInput extends AppCompatActivity {

    // Initializes input stream variable
    InputStream inputStream;

    // Initializes flanges - pcd hashmap
    Map<String, String[]> flangesPcdPairValues = new HashMap<>();

    // Initializes data array
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_pcd_input);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

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

                    // Log.e displays data in Logcat for programmer use
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
                                    //Float.parseFloat(data[0].replace("-", " ")),
                                    String.valueOf(data[0]).replaceAll("-", ""),
                                    String.valueOf(data[1]).replaceAll("-", ""),
                                    String.valueOf(data[2]).replaceAll("-", ""),
                                    String.valueOf(data[3]).replaceAll("-", ""),
                                    String.valueOf(data[4]).replaceAll("-", ""),
                                    String.valueOf(data[5]).replaceAll("-", ""),
                                    String.valueOf(data[6]).replaceAll("-", ""),
                                    String.valueOf(data[7]).replaceAll("-", ""),
                                    String.valueOf(data[8]).replaceAll("-", ""),
                                    String.valueOf(data[9]).replaceAll("-", ""),
                                    String.valueOf(data[10]).replaceAll("-", ""),
                                    String.valueOf(data[11]).replaceAll("-", ""),
                                    String.valueOf(data[12]).replaceAll("-", ""),
                                    String.valueOf(data[13]).replaceAll("-", ""),
                                    String.valueOf(data[14]).replaceAll("-", ""),
                                    String.valueOf(data[15]).replaceAll("-", ""),
                                    String.valueOf(data[16]).replaceAll("-", ""),
                                    String.valueOf(data[17]).replaceAll("-", ""),
                                    String.valueOf(data[18]).replaceAll("-", ""),
                                    String.valueOf(data[19]).replaceAll("-", ""),
                                    String.valueOf(data[20]).replaceAll("-", ""),
                                    String.valueOf(data[21]).replaceAll("-", ""),
                            }
                    );
                    // Exception allows app to still run
                } catch (Exception e) {
                    Log.e("Problem", e.toString());
                    //throw new RuntimeException("Error in appending data to hashmap: "+ e);
                }
            }
            // IOException stops app from running
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
                    Intent flangesPcdDisplay = new Intent(this, FlangesPcdDisplay.class);
                    flangesPcdDisplay.putExtra("flangesPcdValues", flangesPcdValues);
                    startActivity(flangesPcdDisplay);
                }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: "+ e);
        }
    }
}

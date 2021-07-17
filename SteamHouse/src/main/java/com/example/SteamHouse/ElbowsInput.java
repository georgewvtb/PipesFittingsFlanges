package com.example.SteamHouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ElbowsInput extends AppCompatActivity {

    // Initializes input stream variable
    InputStream inputStream;

    // Initializes elbows hashmap
    Map<String, String[]> elbowsPairValues = new HashMap<>();

    // Initializes data array
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elbows_input);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Gets elbows csv file
        inputStream = getResources().openRawResource(R.raw.elbows);

        // Reads elbows csv file
        BufferedReader elbowsReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // Initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = elbowsReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {

                    // Log.e displays data in Logcat for programmer use
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4] + " " + data[5]);


                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    elbowsPairValues.put(
                            // Key name. Used to retrieve data (values)
                            data[0] + ";" + data[1],
                            // Values. Placed in an array
                            new String[] {
                                    String.valueOf(data[0]),
                                    String.valueOf(data[1]),
                                    String.valueOf(data[2]),
                                    String.valueOf(data[3]),
                                    String.valueOf(data[4]),
                                    String.valueOf(data[5]),
                            }
                    );
                    // Exception allows app to still run
                } catch (Exception e) {
                    Log.e("Problem", e.toString());
                    //throw new RuntimeException("Error in appending data to hashmap: "+ e);
                }
            }
        }
        // IOException stops app from running
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ ex);
        }

        Spinner schedule = findViewById(R.id.elbowsTextView);
        // Get the array from the resources (string.xml)
        String[] arr = getResources().getStringArray(R.array.schedule);
        // Creates a new adapter
        ArrayAdapter<String> scheduleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr) {
            // Method used by the spinner to know the state of each element in the dropdown menu
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) return false;
                else return true;
            }

            // Method used by the spinner to get the view for the selected item view
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == 0) ((TextView) view).setTextColor(Color.GRAY);
                else ((TextView) view).setTextColor(Color.BLACK);
                return view;
            }

            // Method used by the spinner to get the item view for the dropdown menu
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position == 0) ((TextView) view).setTextColor(Color.GRAY);
                else ((TextView) view).setTextColor(Color.BLACK);
                return view;
            }
        };
        // Sets the adapter for the spinner
        schedule.setAdapter(scheduleAdapter);
    }

    public void submitElbows(View v){
        try {
            // Edit text to enter elbows nominal bore
            EditText et_elbowsNominalSize = findViewById(R.id.nominalSizeElbows);
            // Spinner to select a schedule in dropdown list
            Spinner elbowsSchedule = findViewById(R.id.elbowsTextView);

            // Converts edit text value to a string
            String elbowsKey = et_elbowsNominalSize.getText().toString() + ";";

            // View schedule spinner custom error
            TextView elbowsViewSpinnerError = (TextView) elbowsSchedule.getSelectedView();

            // Displays custom error message if nominal size edit text is empty
            if (et_elbowsNominalSize.length() == 0) {
                et_elbowsNominalSize.setError("Enter Nominal Size");
                et_elbowsNominalSize.requestFocus();
            }
            // Sets custom spinner error message if a schedule is not selected
            else if (elbowsSchedule.getSelectedItemPosition() == 0) {
                elbowsViewSpinnerError.setError("Icon"); // Displays Icon "!" error in spinner
                elbowsViewSpinnerError.setTextColor(Color.RED);
                elbowsViewSpinnerError.setText("Select a Schedule");
                elbowsViewSpinnerError.setTextSize(20);
                elbowsSchedule.requestFocus();
            }
            else {
                // Runs if the nominal size and schedule are entered
                if (elbowsSchedule.getSelectedItemPosition() != 0) {
                    elbowsKey += elbowsSchedule.getSelectedItem().toString();

                    // Adds selected nominal size, schedule, and respective data to an array
                    String[] elbowsValues = elbowsPairValues.get(elbowsKey);

                    // Displays custom error message if nominal size value is invalid
                    if (elbowsValues == null) {
                        et_elbowsNominalSize.setError("Invalid Nominal Size");
                    }
                    // Sends the array to the elbows display activity
                    else if (elbowsValues != null) {
                        Intent elbowsDisplay = new Intent(this, ElbowsDisplay.class);
                        elbowsDisplay.putExtra("values", elbowsValues);
                        startActivity(elbowsDisplay);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: "+ e);
        }
    }
}
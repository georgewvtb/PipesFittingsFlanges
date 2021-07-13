package com.example.practicemain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

public class elbowsInput extends AppCompatActivity {

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

        // Gets elbows csv file
        inputStream = getResources().openRawResource(R.raw.elbows);

        // Reads elbows csv file
        BufferedReader elbowsReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = elbowsReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {
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
                } catch (Exception e) {
                    Log.e("Problem", e.toString());
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
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
            View elbowsViewSpinnerError = elbowsSchedule.getSelectedView();
            // Text view to display "!" icon to spinner
            TextView elbowsSpinnerErrorIcon = (TextView)elbowsViewSpinnerError;
            // Text view for spinner custom error message
            TextView elbowsInvisibleSpinnerError = (TextView)findViewById(R.id.elbowsInvisibleSpinnerError);

            // Displays custom error message if nominal size edit text is empty
            if (et_elbowsNominalSize.length() == 0) {
                et_elbowsNominalSize.setError("Enter Nominal Size");
                et_elbowsNominalSize.requestFocus();
            }
            // Sets custom spinner error message if a schedule is not selected
            else if (elbowsSchedule.getSelectedItemPosition() == 0) {
                elbowsSpinnerErrorIcon.setError("Icon"); // Displays Icon error in spinner
                elbowsInvisibleSpinnerError.setBackgroundColor(Color.BLACK);
                elbowsInvisibleSpinnerError.setTextColor(Color.WHITE);
                elbowsInvisibleSpinnerError.setText("Select a Schedule");
                elbowsSchedule.requestFocus();
            } else {
                // Clears spinner custom error message if a schedule is selected
                elbowsInvisibleSpinnerError.setText(null);
                elbowsInvisibleSpinnerError.setBackgroundColor(Color.WHITE);
            }

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
                    Intent elbowsDisplay = new Intent(this, elbowsDisplay.class);
                    elbowsDisplay.putExtra("values", elbowsValues);
                    startActivity(elbowsDisplay);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: "+ e);
        }
    }
}
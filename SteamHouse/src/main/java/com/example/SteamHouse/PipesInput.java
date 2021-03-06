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

public class PipesInput extends AppCompatActivity {

    // Initializes input stream variable
    InputStream inputStream;

    // Initializes pipes hashmap
    Map<String, String[]> pipesPairValues = new HashMap<>();

    // Initializes data array
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipes_input);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Gets pipes csv file
        inputStream = getResources().openRawResource(R.raw.pipes);

        // Reads pipes csv file
        BufferedReader pipesReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // Initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = pipesReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {
                    // Log.e displays data in Logcat for programmer use
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4]);

                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    pipesPairValues.put(
                            // Key name. Used to retrieve data (values)
                            data[0] + ";" + data[1],
                            // Values. Placed in an array
                            new String[]{
                                    String.valueOf(data[0]),
                                    String.valueOf(data[1]),
                                    String.valueOf(data[2]),
                                    String.valueOf(data[3]),
                                    String.valueOf(data[4]),
                                    //Float.parseFloat(data[4].replaceAll(",", ".")),
                            }
                    );
                    // Exception allows app to still run
                } catch (Exception e) {
                     Log.e("Problem", e.toString());
                //    throw new RuntimeException("Error in appending data to hashmap: " + e);
                 //   e.printStackTrace();
                }
            }
        }
        // IOException stops app from running
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }

        Spinner schedule = findViewById(R.id.pipesTextView);
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

    public void submitPipes(View v) {
        try {
            // Edit text to enter pipes nominal bore
            EditText et_pipesNominalBore = findViewById(R.id.nominalBorePipes);
            // Spinner to select pipes schedule in dropdown list
            Spinner pipesSchedule = findViewById(R.id.pipesTextView);

            // Converts edit text value to a string
            String pipesKey = et_pipesNominalBore.getText().toString() + ";";

            // Pipe schedule spinner custom error
            TextView pipesViewSpinnerError = (TextView) pipesSchedule.getSelectedView();

            // Displays custom error message if nominal bore edit text is empty
            if (et_pipesNominalBore.length() == 0) {
                et_pipesNominalBore.setError("Enter Nominal Bore");
                et_pipesNominalBore.requestFocus();
            }
            // Sets custom spinner error message if a schedule is not selected
            else if (pipesSchedule.getSelectedItemPosition() == 0) {
                pipesViewSpinnerError.setError("Icon");
                pipesViewSpinnerError.setTextColor(Color.RED);
                pipesViewSpinnerError.setText("Select a Schedule");
                pipesViewSpinnerError.setTextSize(20);
                pipesSchedule.requestFocus();
            } else {
                // Runs if the nominal bore and schedule are entered
                if (pipesSchedule.getSelectedItemPosition() != 0) {
                    pipesKey += pipesSchedule.getSelectedItem().toString();

                    // Adds selected nominal bore, schedule, and respective data to an array
                    String[] pipesValues = pipesPairValues.get(pipesKey);

                    // Displays custom error message if nominal bore value is invalid
                    if (pipesValues == null) {
                        et_pipesNominalBore.setError("Invalid Nominal Bore");
                        et_pipesNominalBore.requestFocus();
                    }
                    // Sends the array to the pipes display activity
                    else if (pipesValues != null) {
                        Intent pipesDisplay = new Intent(this, PipesDisplay.class);
                        pipesDisplay.putExtra("pipesValues", pipesValues);
                        startActivity(pipesDisplay);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: " + e);
        }
    }
}


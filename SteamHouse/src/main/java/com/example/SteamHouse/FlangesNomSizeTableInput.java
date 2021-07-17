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

public class FlangesNomSizeTableInput extends AppCompatActivity {

    // Initializes input stream variable
    InputStream inputStream;

    // Initializes flanges - nominal size & table hashmap
    Map<String, String[]> flangesNomSizeTablePairValues = new HashMap<>();

    // Initializes data array
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_nom_size_table_input);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Gets flanges - nominal size & table csv file
        inputStream = getResources().openRawResource(R.raw.flanges_nomsize_table);

        // Reads flanges - nominal size & table csv file
        BufferedReader flangesNomSizeTableReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // initializes csvLine variable
            String csvLine;
            // Loops through csv file. Loop ends when data ends
            while ((csvLine = flangesNomSizeTableReader.readLine()) != null) {
                // Splits csv data in each row
                data = csvLine.split(";");
                try {

                    // Log.e displays data in Logcat for programmer use
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4] + " "
                            + data[5] + data[6] + " " + data[7]);


                    // Adds each row of data to hashmap (dictionary)
                    // Key value pair
                    flangesNomSizeTablePairValues.put(
                            // Key name. Used to retrieve data (values)
                            data[0] + ";" + data[1],
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
                            }
                    );
                    // Exception allows app to still run
                } catch (Exception e) {
                    Log.e("Problem", e.toString());
                   // throw new RuntimeException("Error in appending data to hashmap: "+ e);
                }
            }
            // IOException stops app from running
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        }

        Spinner flangesTable = findViewById(R.id.flangesNomSizeTableTextView);
        // Get the array from the resources (string.xml)
        String[] arr = getResources().getStringArray(R.array.flangesTable);
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
        flangesTable.setAdapter(scheduleAdapter);
    }

    public void submitFlangesNomSizeTable(View v) {
        try {
            // Edit text to enter flanges nominal size
            EditText et_flangesNomSizeTableNominalSize = findViewById(R.id.nominalSizeFlangesNomSizeTable);
            // Spinner to select a table in dropdown list
            Spinner flangesTable = findViewById(R.id.flangesNomSizeTableTextView);

            // Converts edit text value to a string
            String flangesNomSizeTableNominalSizeKey = et_flangesNomSizeTableNominalSize.getText().toString() + ";";

            // View schedule spinner custom error
            TextView flangesViewSpinnerError = (TextView) flangesTable.getSelectedView();

            // Displays custom error message if nominal size edit text is empty
            if (et_flangesNomSizeTableNominalSize.length() == 0) {
                et_flangesNomSizeTableNominalSize.setError("Enter Nominal Size");
                et_flangesNomSizeTableNominalSize.requestFocus();
            }
            // Sets custom spinner error message if a table is not selected
            else if (flangesTable.getSelectedItemPosition() == 0) {
                flangesViewSpinnerError.setError("Icon"); // Displays Icon "!" error in spinner
                flangesViewSpinnerError.setTextColor(Color.RED);
                flangesViewSpinnerError.setText("Select a Table");
                flangesViewSpinnerError.setTextSize(20);
                flangesTable.requestFocus();
            }
            else {
                // Runs if the nominal size and schedule are entered
                if (flangesTable.getSelectedItemPosition() != 0) {
                    flangesNomSizeTableNominalSizeKey += flangesTable.getSelectedItem().toString();

                    // Adds selected nominal size, table, and respective data to an array
                    String[] flangesNomSizeTableValues = flangesNomSizeTablePairValues.get(flangesNomSizeTableNominalSizeKey);

                    // Displays custom error message if nominal size value is invalid
                    if (flangesNomSizeTableValues == null) {
                        et_flangesNomSizeTableNominalSize.setError("Invalid Nominal Size");
                        flangesTable.requestFocus();

                        // Sends the array to the flanges nominal size & table display activity
                    } else if (flangesNomSizeTableValues != null) {
                        Intent flangesNomSizeTableDisplay = new Intent(this, FlangesNomSizeTableDisplay.class);
                        flangesNomSizeTableDisplay.putExtra("flangesNomSizeTableValues", flangesNomSizeTableValues);
                        startActivity(flangesNomSizeTableDisplay);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in submitting: "+ e);
        }
    }
}


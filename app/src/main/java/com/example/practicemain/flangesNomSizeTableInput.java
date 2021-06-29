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

public class flangesNomSizeTableInput extends AppCompatActivity {

    InputStream inputStream;

    Map<String, String[]> flangesNomSizeTablePairValues = new HashMap<>();

    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_nom_size_table_input);

        inputStream = getResources().openRawResource(R.raw.flanges_nomsize_table);

        BufferedReader flangesNomSizeTableReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = flangesNomSizeTableReader.readLine()) != null) {
                data = csvLine.split(";");
                try {
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4] + " " + data[5]);

                    flangesNomSizeTablePairValues.put(
                            data[0] + ";" + data[1],
                            new String[]{
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
        EditText flangesNomSizeTableNominalSize = findViewById(R.id.nominalSizeFlangesNomSizeTable);
        Spinner flangesTable = findViewById(R.id.flangesNomSizeTableTextView);

        String flangesNomSizeTableNominalSizeKey = flangesNomSizeTableNominalSize.getText().toString() + ";";
        //String flangesNomSizeTableTableKey = flangesNomSizeTableTable.getText().toString();

        if (flangesTable.getSelectedItemPosition() != 0) {
            flangesNomSizeTableNominalSizeKey += flangesTable.getSelectedItem().toString();

            String[] flangesNomSizeTableValues = flangesNomSizeTablePairValues.get(flangesNomSizeTableNominalSizeKey);

            if (flangesNomSizeTableValues != null) {
                Intent flangesNomSizeTableDisplay = new Intent(this, flangesNomSizeTableDisplay.class);
                flangesNomSizeTableDisplay.putExtra("flangesNomSizeTableValues", flangesNomSizeTableValues);
                startActivity(flangesNomSizeTableDisplay);
            } else {
            }
        } else {
        }
    }
}


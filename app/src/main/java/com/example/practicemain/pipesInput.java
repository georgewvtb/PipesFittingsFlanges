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

public class pipesInput extends AppCompatActivity {

    InputStream inputStream;

    Map<String, String[]> pipesPairValues = new HashMap<>();

    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipes_input);

        inputStream = getResources().openRawResource(R.raw.pipes);

        BufferedReader pipesReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = pipesReader.readLine()) != null) {
                data = csvLine.split(";");
                try {
                    Log.e("Data ", "" + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4]);

                    pipesPairValues.put(
                            data[0] + ";" + data[1],
                            new String[] {
                                    String.valueOf(data[0]),
                                    String.valueOf(data[1]),
                                    String.valueOf(data[2]),
                                    String.valueOf(data[3]),
                                    String.valueOf(data[4]),
                                    //Float.parseFloat(data[4].replaceAll(",", ".")),
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

    public void submitPipes(View v){
        EditText pipesNominalBore = findViewById(R.id.nominalBorePipes);
        Spinner pipesSchedule = findViewById(R.id.pipesTextView);

        String pipesKey = pipesNominalBore.getText().toString() + ";";
        if (pipesSchedule.getSelectedItemPosition() != 0) {
            pipesKey += pipesSchedule.getSelectedItem().toString();

            String[] pipesValues = pipesPairValues.get( pipesKey );

            if (pipesValues != null) {
                Intent pipesDisplay = new Intent(this, pipesDisplay.class);
                pipesDisplay.putExtra("pipesValues", pipesValues);
                startActivity(pipesDisplay);
            }
            else {

            }
        }
        else {

        }
    }
}

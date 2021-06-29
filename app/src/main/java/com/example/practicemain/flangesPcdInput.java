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

    InputStream inputStream;

    Map<String, String[]> flangesPcdPairValues = new HashMap<>();

    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_pcd_input2);

        inputStream = getResources().openRawResource(R.raw.flanges_pcd);

        BufferedReader flangesPcdReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = flangesPcdReader.readLine()) != null) {
                data = csvLine.split(";");
                try {
                    Log.e("Data ",  " " + data[0] + " " + data[1] + " "
                            + data[2] + " " + data[3] + " " + data[4] + " "
                            + data[5] + " " + data[6] + " " + data[7] + " "
                            + data[8] + " " + data[9] + " " + data[10] + " " + data[11] + " "
                            + data[12] + " " + data[13] + " " + data[14] + " "
                            + data[15] + " " + data[16] + " " + data[17] + " "
                            + data[18] + " " + data[19] + " " + data[20]);

                    flangesPcdPairValues.put(
                            data[0],
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
        EditText flangesPCD = findViewById(R.id.etFlangesPcd);

        String flangesPcdKey = flangesPCD.getText().toString();

        String[] flangesPcdValues = flangesPcdPairValues.get( flangesPcdKey );

        if (flangesPcdValues != null) {
            Intent flangesPcdDisplay = new Intent(this, flangesPcdDisplay.class);
            flangesPcdDisplay.putExtra("flangesPcdValues", flangesPcdValues);
            startActivity(flangesPcdDisplay);
        } else {

        }
    }
}

package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class flangesPcdDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_pcd_display);

        // Get flanges - pcd input activity's intent
        Intent intent = getIntent();
        // Get flanges - pcd input activity's array
        String[] values = (String[])intent.getExtras().get("flangesPcdValues");

        // Display data from array

        TextView flangesPcdPcd = findViewById(R.id.flangesPcdPcd);
        flangesPcdPcd.setText(String.valueOf(values[1]));

        TextView flangesPcdNominalSize1 = findViewById(R.id.flangesPcdNominalSize1);
        flangesPcdNominalSize1.setText(String.valueOf(values[2]));

        TextView flangesPcdTable1 = findViewById(R.id.flangesPcdTable1);
        flangesPcdTable1.setText(String.valueOf(values[3]));

        TextView flangesPcdNominalSize2 = findViewById(R.id.flangesPcdNominalSize2);
        flangesPcdNominalSize2.setText(String.valueOf(values[4]));

        TextView flangesPcdTable2 = findViewById(R.id.flangesPcdTable2);
        flangesPcdTable2.setText(String.valueOf(values[5]));

        TextView flangesPcdNominalSize3 = findViewById(R.id.flangesPcdNominalSize3);
        flangesPcdNominalSize3.setText(String.valueOf(values[6]));

        TextView flangesPcdTable3 = findViewById(R.id.flangesPcdTable3);
        flangesPcdTable3.setText(String.valueOf(values[7]));

        TextView flangesPcdNominalSize4 = findViewById(R.id.flangesPcdNominalSize4);
        flangesPcdNominalSize4.setText(String.valueOf(values[8]));

        TextView flangesPcdTable4 = findViewById(R.id.flangesPcdTable4);
        flangesPcdTable4.setText(String.valueOf(values[9]));

        TextView flangesPcdNominalSize5 = findViewById(R.id.flangesPcdNominalSize5);
        flangesPcdNominalSize5.setText(String.valueOf(values[10]));

        TextView flangesPcdTable5 = findViewById(R.id.flangesPcdTable5);
        flangesPcdTable5.setText(String.valueOf(values[11]));

        TextView flangesPcdNominalSize6 = findViewById(R.id.flangesPcdNominalSize6);
        flangesPcdNominalSize6.setText(String.valueOf(values[12]));

        TextView flangesPcdTable6 = findViewById(R.id.flangesPcdTable6);
        flangesPcdTable6.setText(String.valueOf(values[13]));

        TextView flangesPcdNominalSize7 = findViewById(R.id.flangesPcdNominalSize7);
        flangesPcdNominalSize7.setText(String.valueOf(values[14]));

        TextView flangesPcdTable7 = findViewById(R.id.flangesPcdTable7);
        flangesPcdTable7.setText(String.valueOf(values[15]));

        TextView flangesPcdNominalSize8 = findViewById(R.id.flangesPcdNominalSize8);
        flangesPcdNominalSize8.setText(String.valueOf(values[16]));

        TextView flangesPcdTable8 = findViewById(R.id.flangesPcdTable8);
        flangesPcdTable8.setText(String.valueOf(values[17]));

        TextView flangesPcdNominalSize9 = findViewById(R.id.flangesPcdNominalSize9);
        flangesPcdNominalSize9.setText(String.valueOf(values[18]));

        TextView flangesPcdTable9 = findViewById(R.id.flangesPcdTable9);
        flangesPcdTable9.setText(String.valueOf(values[19]));

        TextView flangesPcdNominalSize10 = findViewById(R.id.flangesPcdNominalSize10);
        flangesPcdNominalSize10.setText(String.valueOf(values[20]));

        TextView flangesPcdTable10 = findViewById(R.id.flangesPcdTable10);
        flangesPcdTable10.setText(String.valueOf(values[21]));

    }
}
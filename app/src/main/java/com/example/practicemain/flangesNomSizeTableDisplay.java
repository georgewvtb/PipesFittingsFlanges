package com.example.practicemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class flangesNomSizeTableDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flanges_nom_size_table_display);

        // Get flanges - nominal size & table input activity's intent
        Intent intent = getIntent();
        // Get flanges - nominal size & table input activity's array
        String[] values = (String[])intent.getExtras().get("flangesNomSizeTableValues");

        // Displays data from array

        TextView flangesNomSizeTableNominalSize = findViewById(R.id.flangesNomSizeTableNominalSize);
        flangesNomSizeTableNominalSize.setText(String.valueOf(values[0]));

        TextView flangesNomSizeTableTable = findViewById(R.id.flangesNomSizeTableTable);
        flangesNomSizeTableTable.setText(String.valueOf(values[1]));

        TextView flangesNomSizeTableThickness = findViewById(R.id.flangesNomSizeTableThickness);
        flangesNomSizeTableThickness.setText(String.valueOf(values[2]));

        TextView flangesNomSizeTableHolesNumber = findViewById(R.id.flangesNomSizeTableHolesNumber);
        flangesNomSizeTableHolesNumber.setText(String.valueOf(values[3]));

        TextView flangesNomSizeTableHolesDiameter = findViewById(R.id.flangesNomSizeTableHolesDiameter);
        flangesNomSizeTableHolesDiameter.setText(String.valueOf(values[4]));

        TextView flangesNomSizeTablePCD = findViewById(R.id.flangesNomSizeTablePCD);
        flangesNomSizeTablePCD.setText(String.valueOf(values[5]));

        TextView flangesNomSizeTableOutsideDiameter = findViewById(R.id.flangesNomSizeTableOutsideDiameter);
        flangesNomSizeTableOutsideDiameter.setText(String.valueOf(values[6]));

        TextView flangesNomSizeTableInternalDiameter = findViewById(R.id.flangesNomSizeTableInternalDiameter);
        flangesNomSizeTableInternalDiameter.setText(String.valueOf(values[7]));
    }
}
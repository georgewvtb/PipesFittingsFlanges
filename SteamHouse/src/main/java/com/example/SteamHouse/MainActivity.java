package com.example.SteamHouse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets action bar to a logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.steamhouse_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        // Changes action bar color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("WHITE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //Create the drop-down menu
        Spinner materials = findViewById(R.id.materials);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> materialsAdapter = ArrayAdapter.createFromResource(this,
                R.array.materials, android.R.layout.simple_spinner_item);
        materialsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        materials.setAdapter(materialsAdapter);
    }

    //Method which handles the button click event (here is the intent)
    public void btnClick( View v ) {
        Spinner materials = findViewById(R.id.materials);
        String selectedItem = materials.getSelectedItem().toString().toUpperCase();

        switch (selectedItem)
        {
            case "PIPES":
                Intent pipesIntent = new Intent(this, PipesInput.class);
                startActivity( pipesIntent );
                break;

            case "ELBOWS - LONG RADIUS":
                Intent elbowsIntent = new Intent(this, ElbowsInput.class);
                startActivity( elbowsIntent );
                break;

            case "TEES - EQUAL":
                Intent teesIntent = new Intent(this, TeesInput.class);
                startActivity( teesIntent );
                break;

            case "REDUCERS - CONCENTRIC & ECCENTRIC":
                Intent reducesIntent = new Intent(this, ReducersInput.class);
                startActivity( reducesIntent );
                break;

            case "FLANGES - NOMINAL SIZE & TABLE":
                Intent flangesNomSizeTableIntent = new Intent(this, FlangesNomSizeTableInput.class);
                startActivity( flangesNomSizeTableIntent );
                break;

            case "FLANGES - PCD":
                Intent flangesPcdIntent = new Intent(this, FlangesPcdInput.class);
                startActivity( flangesPcdIntent );
                break;
        }
    }

}
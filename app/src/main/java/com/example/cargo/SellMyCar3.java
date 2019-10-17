package com.example.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SellMyCar3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_my_car3);


        final EditText engine = findViewById(R.id.et_engine);
        final EditText information = findViewById(R.id.et_extrainfomation);


        String[] options1 = { "Sedan", "Coupe" , "Hatchback", "Van", "Truck" , "Station Wagon" , "Convertible" , "SUV" , "Small SUV" };

        final Spinner CarTypeSpinner =(Spinner)
                findViewById(R.id.spinner_type);
        ArrayAdapter<String> objGenderArr1 = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, options1);
        CarTypeSpinner.setAdapter(objGenderArr1);



        TextView text = findViewById(R.id.next1);


        final Bundle extras = getIntent().getExtras();


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , SellMyCar4.class);
                intent.putExtra("Brand" , extras.getString("Brand"));
                intent.putExtra("Model" , extras.getString("Model"));
                intent.putExtra("Year" , extras.getString("Year"));
                intent.putExtra("Color" , extras.getString("Color"));
                intent.putExtra("Kilometers" , extras.getString("Kilometers") );
                intent.putExtra("Fuel" , extras.getString("Fuel") );
                intent.putExtra("Transmission" , extras.getString("Transmission") );
                intent.putExtra("engine" , engine.getText().toString());
                intent.putExtra("Type" , CarTypeSpinner.getSelectedItem().toString());
                intent.putExtra("ExtraInformation" , information.getText().toString() );







                startActivity(intent);

            }
        });

    }
}

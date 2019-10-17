package com.example.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SellMyCar extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_my_car);

        final EditText Model = findViewById(R.id.et_model);
        final EditText year = findViewById(R.id.et_year);


        String[] options = { "Acura", "Alfa Romeo" , "Aston Martin", "Audi" ,
                "Bently", "BMW" , "Cadillac", "Chevrolet" ,
                "Chrysler", "Citroen" , "Cupra" , "Dodge", "Ferrari" ,
                "Fiat", "Ford", "Genesis" , "GMC", "Hyundai" ,
                "Infiniti", "Jaguar" , "Jeep", "Kia" ,
                "Lamborghini", "Land Rover" , "Lexus", "Maserati" ,
                "Mazda", "Maybach" , "McLaren" , "Mercedes-Benz", "Mini" ,
                "Mitsubishi", "Peugeot" , "Porsche", "Nissan" ,"Opel",
                "Ram", "Renault" , "Rolls Royce", "Seat" , "Skoda" , "Smart" , "Subaru" ,
                "Suzuki", "Tesla" , "Toyota", "Volkswagen" ,
                "Volvo"};


        final Spinner brandSpinner =(Spinner)
                findViewById(R.id.spinner_brand);
        ArrayAdapter<String> objGenderArr = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, options);
        brandSpinner.setAdapter(objGenderArr);



        String[] options1 = { "Black", "Green" , "Blue", "Red" };

        final Spinner colorspinner =(Spinner)
                findViewById(R.id.spinner_color);
        ArrayAdapter<String> objGenderArr1 = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, options1);
        colorspinner.setAdapter(objGenderArr1);


        TextView text = findViewById(R.id.next1);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , SellMyCar2.class);
                intent.putExtra("Brand" , brandSpinner.getSelectedItem().toString());
                intent.putExtra("Model" , Model.getText().toString());
                intent.putExtra("Year" , year.getText().toString());
                intent.putExtra("Color" , colorspinner.getSelectedItem().toString());
                startActivity(intent);

            }
        });



    }
}

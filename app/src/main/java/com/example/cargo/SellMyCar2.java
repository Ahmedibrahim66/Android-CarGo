package com.example.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SellMyCar2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_my_car2);

        final EditText kilometers = findViewById(R.id.et_kilometers);
        final RadioGroup fuel = findViewById(R.id.rg_fueltype);
        final RadioGroup trasmission = findViewById(R.id.rg_trasmission);

        final RadioGroup new_used = findViewById(R.id.rg_new_used);



        final Bundle extras = getIntent().getExtras();


        final TextView text5 = findViewById(R.id.textviewkm);


        final RadioButton bt = findViewById(R.id.rb_used);

        bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(bt.isChecked()){

                    kilometers.setVisibility(View.VISIBLE);
                    text5.setVisibility(View.VISIBLE);



                }else {

                    kilometers.setVisibility(View.INVISIBLE);
                    text5.setVisibility(View.INVISIBLE);

                }

            }
        });





        TextView text = findViewById(R.id.next1);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , SellMyCar3.class);

                intent.putExtra("Brand" , extras.getString("Brand"));
                intent.putExtra("Model" , extras.getString("Model"));
                intent.putExtra("Year" , extras.getString("Year"));
                intent.putExtra("Color" , extras.getString("Color"));



                int selectedId = fuel.getCheckedRadioButtonId();


                RadioButton radioButton =  findViewById(selectedId);
                intent.putExtra("Fuel" , radioButton.getText().toString());


                int selectedId1 = trasmission.getCheckedRadioButtonId();
                RadioButton radioButton1 =  findViewById(selectedId1);
                intent.putExtra("Transmission" , radioButton1.getText().toString());


                int selectedId2 = new_used.getCheckedRadioButtonId();
                RadioButton radioButton2 =  findViewById(selectedId1);

                TextView text = findViewById(R.id.textviewkm);


                RadioButton bt = findViewById(R.id.rb_used);

                if(bt.isChecked()){

                    intent.putExtra("Kilometers" , kilometers.getText().toString());


                }else {

                    intent.putExtra("Kilometers" , "New");

                }

                intent.putExtra("Transmission" , radioButton1.getText().toString());



                startActivity(intent);

            }
        });

    }
}

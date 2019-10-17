package com.example.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SellMyCar4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_my_car4);


        final Bundle extras = getIntent().getExtras();


        final EditText price = findViewById(R.id.et_price);
        final CheckBox listedone = findViewById(R.id.cb_sale);
        final CheckBox listedone1 = findViewById(R.id.cb_badal);


        final CheckBox paymentone = findViewById(R.id.cb_cash);
        final CheckBox paymentone1 = findViewById(R.id.cb_takseet);







        TextView text = findViewById(R.id.next1);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String one = "" ;
                String two = "";
                String three = "";
                String four = "";


                if(listedone.isChecked()){
                    one = listedone.getText().toString();
                }
                if(listedone1.isChecked()){
                    two = listedone.getText().toString();
                }
                if(paymentone.isChecked()){
                    three = paymentone.getText().toString();
                }
                if(paymentone1.isChecked()){
                    four = paymentone1.getText().toString();
                }

                Intent intent = new Intent(getApplicationContext() , SellMyCar5.class);

                intent.putExtra("Brand" , extras.getString("Brand"));
                intent.putExtra("Model" , extras.getString("Model"));
                intent.putExtra("Year" , extras.getString("Year"));
                intent.putExtra("Color" , extras.getString("Color"));
                intent.putExtra("Kilometers" , extras.getString("Kilometers") );
                intent.putExtra("Fuel" , extras.getString("Fuel") );
                intent.putExtra("Transmission" , extras.getString("Transmission") );
                intent.putExtra("engine" , extras.getString("engine"));
                intent.putExtra("Type" , extras.getString("Type"));
                intent.putExtra("ExtraInformation" , extras.getString("ExtraInformation") );
                intent.putExtra("Price" , price.getText().toString() );
                intent.putExtra("ListedForSale" , one );
                intent.putExtra("ListedForBadl" , two);
                intent.putExtra("PaymentCash" , three);
                intent.putExtra("PaymentTakset" , four );







                startActivity(intent);

            }
        });

    }
}

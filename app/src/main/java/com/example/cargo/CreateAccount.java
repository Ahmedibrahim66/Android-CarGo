package com.example.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final EditText fullname = findViewById(R.id.FullNameText);
        final EditText city = findViewById(R.id.CityText);
        Button createaccount = findViewById(R.id.createaccount);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Map<String, Object> user = new HashMap<>();





        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String id = firebaseAuth.getUid();



        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.put("Name", fullname.getText().toString());
                user.put("City", city.getText().toString());

                db.collection("Users").document(id)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext() , "Account Created" , Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });



            }
        });


    }
}

package com.example.cargo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddDealer extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;

    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Phone = new ArrayList<>();
    private ArrayList<String> ID = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dealer);


        recyclerView = findViewById(R.id.rv_adddealer);
        editText = findViewById(R.id.editText);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();








        db.collection("Users")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Name.add(document.getString("Name"));
                        ID.add(document.getId());
                        Phone.add(document.getString("Phone"));
                    }

                    recycleviewcreate();

                    }
            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                db.collection("Users").whereGreaterThanOrEqualTo("Name" , editText.getText().toString()).whereLessThanOrEqualTo("Name",editText.getText().toString()+"z")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            Name.clear();
                            ID.clear();
                            Phone.clear();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Name.add(document.getString("Name"));
                                ID.add(document.getId());
                                Phone.add(document.getString("Phone"));
                            }

                            recycleviewcreate();

                        }
                    }
                });


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }



    private void recycleviewcreate(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_adddealer);
        AddDealerRV adapter = new AddDealerRV( Name,Phone,ID,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

}

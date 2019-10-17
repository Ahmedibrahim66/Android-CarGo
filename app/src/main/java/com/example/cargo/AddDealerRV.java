package com.example.cargo;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddDealerRV extends RecyclerView.Adapter<AddDealerRV.ViewHolder>{




    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Phone = new ArrayList<>();
    private ArrayList<String> ID = new ArrayList<>();

    private Context mContext;


    private static final String TAG = "MainActivity";

    public AddDealerRV(ArrayList<String> Name ,ArrayList<String> Phone, ArrayList<String> ID,Context mContext) {

        this.Name = Name;
        this.ID = ID;
        this.Phone = Phone;
        this.mContext = mContext;


    }

    @Override
    public AddDealerRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adddealerrv, viewGroup, false);
        AddDealerRV.ViewHolder holder = new AddDealerRV.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull AddDealerRV.ViewHolder holder, final int position) {


        holder.name.setText(Name.get(position));
        holder.phone.setText(Phone.get(position));

        final FirebaseFirestore db = FirebaseFirestore.getInstance();


            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setCancelable(true);
                    builder.setTitle("Confirm Dealer");
                    builder.setMessage("Are You Sure You want to make " + Name.get(position) +
                            " with Phone Number " + Phone.get(position) + " a Dealer ?");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    DocumentReference doc = db.collection("Users").document(ID.get(position));
                                    doc.update("isDealer", "1")
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "DocumentSnapshot successfully updated!");

                                                    Toast.makeText(mContext, "Dealer Added", Toast.LENGTH_SHORT).show();

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error updating document", e);
                                                }
                                            });

                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            Toast.makeText(mContext, "Canceled", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();



                }
            });

    }


    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView phone;
        Button button;
        LinearLayout olayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_adddealer_name);
            phone = itemView.findViewById(R.id.tv_adddealer_PhoneNumber);
            button = itemView.findViewById(R.id.bt_adddealer_button);
            olayout = itemView.findViewById(R.id.Olayout);
        }
    }
}


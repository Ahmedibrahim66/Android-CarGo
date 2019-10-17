package com.example.cargo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class latestofferbuycardRecycleViewAdapter  extends RecyclerView.Adapter<latestofferbuycardRecycleViewAdapter.ViewHolder>{




    private ArrayList<String> ID = new ArrayList<>();
    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();
    private ArrayList<String> cargoprice = new ArrayList<>();
    private ArrayList<String> mileage = new ArrayList<>();
    private ArrayList<String> model = new ArrayList<>();
    private ArrayList<String> city = new ArrayList<>();
    private ArrayList<String> dealer = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Context mContext;


    private static final String TAG = "MainActivity";

    public latestofferbuycardRecycleViewAdapter(ArrayList<String> ID, ArrayList<String> brand, ArrayList<String> type,
                                                  ArrayList<String> cargoprice,
                                                 ArrayList<String> mileage, ArrayList<String> model, ArrayList<String> city, ArrayList<String> dealer,
                                                 ArrayList<String> images , Context mContext) {

        this.ID = ID;
        this.Brand = brand;
        this.Type = type;
        this.cargoprice = cargoprice;
        this.model = model;
        this.city = city;
        this.dealer = dealer;
        this.mileage = mileage;
        this.images = images;
        this.mContext = mContext;


    }

    @Override
    public latestofferbuycardRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.latestoffercardbuyfull, viewGroup, false);
        latestofferbuycardRecycleViewAdapter.ViewHolder holder = new latestofferbuycardRecycleViewAdapter.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull latestofferbuycardRecycleViewAdapter.ViewHolder holder, final int position) {



        holder.brand.setText(Brand.get(position) + " " + Type.get(position));
        holder.cargoprice.setText(cargoprice.get(position) + "₪");
        holder.mileage.setText(mileage.get(position) + "Km");
        holder.model.setText(model.get(position));
        holder.city.setText(city.get(position));
        holder.dealer.setText(dealer.get(position));


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext , SpecialOfferBuyFullCard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Id" , ID.get(position));
                intent.putExtra("Brand" , Brand.get(position));
                intent.putExtra("Price" , cargoprice.get(position));
                intent.putExtra("Kilometer" , mileage.get(position));
                intent.putExtra("Model" , Type.get(position));
                intent.putExtra("City" , city.get(position));
                intent.putExtra("Dealer" , dealer.get(position));
                intent.putExtra("MainImage" , images.get(position));

                mContext.startActivity(intent);

            }
        });
/*
        holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
*/


        // put the image //
        Picasso.get()
                .load(images.get(position))
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return Brand.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView brand;
        TextView cargoprice;
        TextView city;
        TextView mileage;
        TextView model;
        TextView dealer;
        TextView button;



        LinearLayout olayout;
        CardView cd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_carimage);
            brand = itemView.findViewById(R.id.tv_carname);
            mileage = itemView.findViewById(R.id.tv_carmileage);
            model = itemView.findViewById(R.id.tv_carmodel);
            city = itemView.findViewById(R.id.tv_carlocation);
            dealer = itemView.findViewById(R.id.tv_cardealer);
            cargoprice = itemView.findViewById(R.id.tv_cargoprice);
            olayout = itemView.findViewById(R.id.R1layout);
            cd = itemView.findViewById(R.id.cardView3);
            button = itemView.findViewById(R.id.bt_txt_viewoffer);

        }
    }

}

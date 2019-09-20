package com.example.cargo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpecialOffersRecycleViewAdaperBuy extends RecyclerView.Adapter<SpecialOffersRecycleViewAdaperBuy.ViewHolder>{





    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();
    private ArrayList<String> Price = new ArrayList<>();
    private ArrayList<String> Discount = new ArrayList<>();
    private ArrayList<String> Specs = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Context mContext;


    private static final String TAG = "MainActivity";

    public SpecialOffersRecycleViewAdaperBuy(ArrayList<String> brand, ArrayList<String> type,
                                        ArrayList<String> price, ArrayList<String> discount,
                                        ArrayList<String> specs,
                                        ArrayList<String> images ,Context mContext) {
        this.Brand = brand;
        this.Type = type;
        this.Price = price;
        this.Discount = discount;
        this.Specs = specs;
        this.images = images;
        this.mContext = mContext;


    }

    @Override
    public SpecialOffersRecycleViewAdaperBuy.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topdealslistsell, viewGroup, false);
        SpecialOffersRecycleViewAdaperBuy.ViewHolder holder = new SpecialOffersRecycleViewAdaperBuy.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull SpecialOffersRecycleViewAdaperBuy.ViewHolder holder, int position) {



        holder.brand.setText(Brand.get(position));
        holder.price.setText(Price.get(position) + "₪");
        holder.discount.setText("Save " + Discount.get(position) + "₪");
        holder.type.setText(Type.get(position));
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
        TextView type;
        TextView price;
        TextView discount;

        ConstraintLayout olayout;
        CardView cd;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_carImage);
            brand = itemView.findViewById(R.id.tv_carBrand);
            type = itemView.findViewById(R.id.tv_carType);
            price = itemView.findViewById(R.id.tv_carPrice);
            discount = itemView.findViewById(R.id.tv_carDiscount);
            olayout = itemView.findViewById(R.id.R1layout);
            cd = itemView.findViewById(R.id.cardView2);
        }
    }

}

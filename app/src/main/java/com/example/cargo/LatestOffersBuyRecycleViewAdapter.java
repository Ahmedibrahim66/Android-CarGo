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

public class LatestOffersBuyRecycleViewAdapter extends RecyclerView.Adapter<LatestOffersBuyRecycleViewAdapter.ViewHolder>{


    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Price = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> Dealer = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();



    private Context mContext;


    private static final String TAG = "MainActivity";

    public LatestOffersBuyRecycleViewAdapter(ArrayList<String> brand, ArrayList<String> locations,
                                             ArrayList<String> price, ArrayList<String> dealer ,
                                             ArrayList<String> images ,ArrayList<String> Type , Context mContext) {
        this.Brand = brand;
        this.locations = locations;
        this.Price = price;
        this.Dealer = dealer;
        this.images = images;
        this.Type = Type;
        this.mContext = mContext;

    }

    @Override
    public LatestOffersBuyRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.latest_offer_buy_card, viewGroup, false);
        LatestOffersBuyRecycleViewAdapter.ViewHolder holder = new LatestOffersBuyRecycleViewAdapter.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull LatestOffersBuyRecycleViewAdapter.ViewHolder holder, int position) {


        holder.name.setText(Brand.get(position));
        holder.price.setText(Price.get(position) + "₪");
        holder.location.setText(locations.get(position));
        holder.dealer.setText(Dealer.get(position));
        holder.type.setText(Type.get(position));

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
        TextView name;
        TextView price;
        TextView location;
        TextView dealer;
        TextView type;

        ConstraintLayout olayout;
        CardView cd;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_carImage);
            name = itemView.findViewById(R.id.tv_carBrand);
            olayout = itemView.findViewById(R.id.R1layout);
            cd = itemView.findViewById(R.id.cardView2);
            price = itemView.findViewById(R.id.tv_carPrice);
            type = itemView.findViewById(R.id.tv_carType);
            dealer = itemView.findViewById(R.id.tv_delear);
            location = itemView.findViewById(R.id.tv_new_used);


        }
    }


}

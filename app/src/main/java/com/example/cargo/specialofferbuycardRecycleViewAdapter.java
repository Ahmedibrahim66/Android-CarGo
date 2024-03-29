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

public class specialofferbuycardRecycleViewAdapter extends RecyclerView.Adapter<specialofferbuycardRecycleViewAdapter.ViewHolder>{





    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();
    private ArrayList<String> Price = new ArrayList<>();
    private ArrayList<String> cargoprice = new ArrayList<>();
    private ArrayList<String> Discount = new ArrayList<>();
    private ArrayList<String> mileage = new ArrayList<>();
    private ArrayList<String> model = new ArrayList<>();
    private ArrayList<String> city = new ArrayList<>();
    private ArrayList<String> dealer = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Context mContext;


    private static final String TAG = "MainActivity";

    public specialofferbuycardRecycleViewAdapter(ArrayList<String> brand, ArrayList<String> type,
                                             ArrayList<String> price, ArrayList<String> cargoprice, ArrayList<String> discount,
                                             ArrayList<String> mileage,ArrayList<String> model,ArrayList<String> city,ArrayList<String> dealer,
                                             ArrayList<String> images ,Context mContext) {
        this.Brand = brand;
        this.Type = type;
        this.Price = price;
        this.cargoprice = cargoprice;
        this.model = model;
        this.city = city;
        this.dealer = dealer;
        this.Discount = discount;
        this.mileage = mileage;
        this.images = images;
        this.mContext = mContext;


    }

    @Override
    public specialofferbuycardRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.specialofferbuycardfull, viewGroup, false);
        specialofferbuycardRecycleViewAdapter.ViewHolder holder = new specialofferbuycardRecycleViewAdapter.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull specialofferbuycardRecycleViewAdapter.ViewHolder holder, int position) {



        holder.brand.setText(Brand.get(position) + " " + Type.get(position));
        holder.price.setText(Price.get(position) + "₪");
        holder.cargoprice.setText(cargoprice.get(position) + "₪");
        holder.discount.setText(Discount.get(position) + "₪");
        holder.mileage.setText(mileage.get(position));
        holder.model.setText(model.get(position));
        holder.city.setText(city.get(position));
        holder.dealer.setText(dealer.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext , SpecialOfferBuyFullCard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

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
        TextView type;
        TextView price;
        TextView discount;
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
            price = itemView.findViewById(R.id.tv_carprice);
            discount = itemView.findViewById(R.id.tv_carDiscount);
            mileage = itemView.findViewById(R.id.tv_carmileage);
            model = itemView.findViewById(R.id.tv_carmodel);
            city = itemView.findViewById(R.id.tv_carlocation);
            dealer = itemView.findViewById(R.id.tv_cardealer);
            cargoprice = itemView.findViewById(R.id.tv_cargoprice);
            olayout = itemView.findViewById(R.id.R1layout);
            cd = itemView.findViewById(R.id.cardView3);
            button = itemView.findViewById(R.id.viewofferbuyspeical);
        }
    }

}
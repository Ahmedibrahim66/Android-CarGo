package com.example.cargo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class rvadapterimagesviewbuy extends RecyclerView.Adapter<rvadapterimagesviewbuy.ViewHolder>{





    private ArrayList<String> images = new ArrayList<>();
    private Context mContext;


    private static final String TAG = "MainActivity";

    public rvadapterimagesviewbuy(ArrayList<String> images ,Context mContext) {

        this.images = images;
        this.mContext = mContext;


    }

    @Override
    public rvadapterimagesviewbuy.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ooo, viewGroup, false);
        rvadapterimagesviewbuy.ViewHolder holder = new rvadapterimagesviewbuy.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull rvadapterimagesviewbuy.ViewHolder holder, int position) {





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
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;




        LinearLayout olayout;
        CardView cd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageee);

            olayout = itemView.findViewById(R.id.Olayout);
            cd = itemView.findViewById(R.id.cd);
        }
    }
}

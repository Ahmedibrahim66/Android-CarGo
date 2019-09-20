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

public class TppDealersRecycleViewAdpater extends RecyclerView.Adapter<TppDealersRecycleViewAdpater.ViewHolder>{



    private ArrayList<String> Names = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Context mContext;


    private static final String TAG = "MainActivity";

    public TppDealersRecycleViewAdpater(ArrayList<String> names, ArrayList<String> images ,Context mContext) {
        this.Names = names;
        this.images = images;
        this.mContext = mContext;

    }

    @Override
    public TppDealersRecycleViewAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topdealerslistsbuy, viewGroup, false);
        TppDealersRecycleViewAdpater.ViewHolder holder = new TppDealersRecycleViewAdpater.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull TppDealersRecycleViewAdpater.ViewHolder holder, int position) {

        // put the image //
        holder.name.setText(Names.get(position));


        Picasso.get()
                .load(images.get(position))
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return Names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        ConstraintLayout olayout;
        CardView cd;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_dealerImage);
            name = itemView.findViewById(R.id.tf_dealerName);
            olayout = itemView.findViewById(R.id.R1layout);
            cd = itemView.findViewById(R.id.cardView);
        }
    }

}

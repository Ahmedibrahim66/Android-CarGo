package com.example.cargo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class sellimageRVadapter extends RecyclerView.Adapter<sellimageRVadapter.ViewHolder>{





    private ArrayList<Uri> images = new ArrayList<>();
    private Context mContext;


    private static final String TAG = "MainActivity";

    public sellimageRVadapter(ArrayList<Uri> images ,Context mContext) {

        this.images = images;
        this.mContext = mContext;


    }

    @Override
    public sellimageRVadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ooo, viewGroup, false);
        sellimageRVadapter.ViewHolder holder = new sellimageRVadapter.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull sellimageRVadapter.ViewHolder holder, int position) {


        holder.image.setImageURI(images.get(position));


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

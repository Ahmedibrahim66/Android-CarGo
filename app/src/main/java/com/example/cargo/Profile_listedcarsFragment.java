package com.example.cargo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Profile_listedcarsFragment extends Fragment {

    RecyclerView recyclerView;



    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Price = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> Dealer = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();
    private ArrayList<String> images11 = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile_listedcars, container, false);


        Brand.add("Audi");
        Type.add("Q8");
        Price.add("670,000");
        locations.add("Ramallah");
        Dealer.add("Audi Palestine");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69697540_2257322764365552_229159938583166976_o.jpg?_nc_cat=106&_nc_oc=AQlh22anZ4z2X3us1ivzqHc-P7KfXC5Lt_ADFLKZkD4pyECG3o95zLF0fLCGuQdTsGQ&_nc_ht=scontent.fjrs1-1.fna&oh=ba96289c7f5151884a6c5e45dda5575f&oe=5E3B6A94");


        Brand.add("Mercedes ");
        Type.add("GLC250d");
        Price.add("230,000");
        locations.add("Ramallah");
        Dealer.add("Friends Motors");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70744560_2475662346025471_4206787657453273088_n.jpg?_nc_cat=108&_nc_oc=AQmnUrx37n0ICEtTvYZp7JtPpY5OmJ1joj6PaPEamCU10Y8rYSIPfrrwbG8keGIAE60&_nc_ht=scontent.fjrs1-1.fna&oh=fa5ae1fdec8656a258cda5c698f3b4ab&oe=5E0E7D13");


        Brand.add("BMW ");
        Type.add("X3");
        Price.add("259,000");
        locations.add("Ramallah");
        Dealer.add("Faqih Auto");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70622789_2479795902278782_2912376834760900608_o.jpg?_nc_cat=105&_nc_oc=AQmRqCrKAM-iGmrv3yRp5IEarqqEivmKnJt9g5fCVdMzNpt3f-B5BbAOCpW3CgIzr5I&_nc_ht=scontent.fjrs1-1.fna&oh=428a208cc0d584ef95549d6f67296e95&oe=5E3DAA4E");


        Brand.add("Range Rover");
        Type.add("Evoqe");
        Price.add("189,000");
        locations.add("Ramallah");
        Dealer.add("Faqih Auto");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70278337_2478917052366667_4463612743110361088_o.jpg?_nc_cat=105&_nc_oc=AQnyAkxWwUaQq4ZMp-FCq8JlI1RkgX7P2O6ic61Mqc3OzE-7v3kd1yMa_QThD6QqDLI&_nc_ht=scontent.fjrs1-1.fna&oh=0d1ab95272f69738044a863f093123f1&oe=5E3B9A7F");


        Brand.add("VW ");
        Type.add("Touareg ");
        Price.add("240,000");
        locations.add("Hebron");
        Dealer.add("Frankfort Motors");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69739744_2380857165503983_7389019198682300416_o.jpg?_nc_cat=103&_nc_oc=AQlYMppdx5IgiQdrjXXzLSf6CMernw6SqcNA27VvXtXTd7G_B4Sa87Lk93zVOutgmS8&_nc_ht=scontent.fjrs1-1.fna&oh=edea99be086a57368af530812ae66fb1&oe=5E3D1BE5");



        Brand.add("Mazda");
        Type.add("6");
        Price.add("185,000");
        locations.add("Ramallah");
        Dealer.add("Al Rami Motors");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/68558062_2982391615109802_4793510826088595456_n.jpg?_nc_cat=108&_nc_oc=AQn9KfSjM0hzMv56nw9wO3BgX9toTtt7Tr2_6R5TzU7c89WBhpy2Bd17c8KGfVz1FoE&_nc_ht=scontent.fjrs1-1.fna&oh=fc42f1e309d484845f4a9d3d818dca9e&oe=5DFB4AAE");


        Brand.add("Range Rover");
        Type.add("Sport ");
        Price.add("450,000");
        locations.add("Hebron");
        Dealer.add("Frankfort Motors");
        images11.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69582549_2378821572374209_1853148834830483456_o.jpg?_nc_cat=108&_nc_oc=AQlPAuku845ZWtRjPRzQhYcNasaz7_bsJ3h4xrAlB3CdKIbCZ5ExT5PZS3aORvZ5PZo&_nc_ht=scontent.fjrs1-1.fna&oh=8e1d8611a47390c77adf350b1ef55171&oe=5DF40A18");


        recycleviewcreate3(v);

        return v;

    }



    private void recycleviewcreate3(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.profile_recycleview);
        LatestOffersBuyRecycleViewAdapter adapter = new LatestOffersBuyRecycleViewAdapter( Brand ,locations,Price,Dealer,images11 ,Type, getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(),  LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


}

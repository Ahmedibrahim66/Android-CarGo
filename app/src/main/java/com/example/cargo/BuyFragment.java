package com.example.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BuyFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private ArrayList<String> mNames = new ArrayList<>();;
    private ArrayList<String> mImageUrls = new ArrayList<>();;




    private ArrayList<String> mBrand = new ArrayList<>();
    private ArrayList<String> mType = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    private ArrayList<String> mDiscount = new ArrayList<>();
    private ArrayList<String> mSpecs = new ArrayList<>();
    private ArrayList<String> mimages1 = new ArrayList<>();




    private ArrayList<String> Brand = new ArrayList<>();
    private ArrayList<String> Price = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> Dealer = new ArrayList<>();
    private ArrayList<String> Type = new ArrayList<>();
    private ArrayList<String> images11 = new ArrayList<>();



    ViewFlipper v_flipper;

    TextView tv;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_buy, container, false);


        tv = v.findViewById(R.id.tv_seeallspecialbuy);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), SpecialOffersBuy.class));


            }
        });
        //image flipper
        int images [] = {R.drawable.specialoffer , R.drawable.specialoffer2 , R.drawable.mercesdescomm , R.drawable.audiq8comm};
        v_flipper = v.findViewById(R.id.v_flipper);
        for (int image: images){

            flipperImages(image);

        }
        //image flipper



        mNames.add("Faqih Auto");
        mImageUrls.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/1897779_1401921440066239_214155154_n.jpg?_nc_cat=106&_nc_oc=AQnzfytu4S6XFH0CdMfIcXx_2oBQtXWk5pWGozkcfzlzKACZJEbRgvgC_nzftQ6Gwis&_nc_ht=scontent.fjrs1-1.fna&oh=95b4e8b684e2f4f657bce955b3d4d9a9&oe=5DFFE62F");

        mNames.add("Friend Motor");
        mImageUrls.add("https://i.ibb.co/QJzPZCx/fm.png");

        mNames.add("Mercedes-Benz Palestine");
        mImageUrls.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/16406531_582009631998310_150124446962603364_n.jpg?_nc_cat=100&_nc_oc=AQmrSLvjKdglmwxti_7KdDt9cssaKG_4-1o1jmA1BYM4HCHs6JFtoBbErwuGHnCqwCY&_nc_ht=scontent.fjrs1-1.fna&oh=9eafde0782eabfc9aa6e6e2fdef718cb&oe=5DF84B5F");

        mNames.add("Audi Palestine");
        mImageUrls.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/13100892_930674593697049_2582118210292990690_n.png?_nc_cat=108&_nc_oc=AQkeyoAO73CgbgO8h5-nZdQP48u933N04-wf9oH2JZi1l-lMaMTu-xQ9sCVXmHU4wbg&_nc_ht=scontent.fjrs1-1.fna&oh=d7335b3edc3bf2c2c97e5e28c9158e23&oe=5DF06CC2");

        mNames.add("Al-Rami Motors");
        mImageUrls.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/46503228_2497621400253495_4725362451153944576_n.jpg?_nc_cat=109&_nc_oc=AQl4XKsAiw8FWJo1lloQdGx0AiqcF4C66nYYU4WpkiEMoM1PqChlpcmN6i2YyQUY6MM&_nc_ht=scontent.fjrs1-1.fna&oh=285f686962b667e4a29637eea92d0ac8&oe=5DF8AEE3");

        mNames.add("BMW Palestine - Abu Khader Automotive");
        mImageUrls.add("https://s3.amazonaws.com/static.revolutionparts.com/assets/images/bmw.png");

        recycleviewcreate(v);


        mBrand.add("BMW");
        mType.add("5-series");
        mPrice.add("285,000");
        mDiscount.add("5000");
        mSpecs.add("Ramallah - BMW 530i Full Options at Friends Motors");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69179554_2691288744249166_9139203214862712832_n.jpg?_nc_cat=102&_nc_oc=AQlS0rgC3_tdUhnObrkUzpFNRj-6Uhc3VQNGhVvyPMGDVACdZOz-4UZ6KOjZ4nc4V5U&_nc_ht=scontent.fjrs1-1.fna&oh=a8c8760bc8a9d4935ccb7b4fac9cfb76&oe=5E006D3B");


        mBrand.add("Jaguar");
        mType.add("F-pace");
        mPrice.add("250,000");
        mDiscount.add("7000");
        mSpecs.add("Ramallah - Jaguar F-pace Full Options at Friends Motors");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69891669_2699245560120151_1881406648736546816_n.jpg?_nc_cat=100&_nc_oc=AQnzO34KJOY9safJLX9RmpkW7UzqG6YMrI3yUdhNW2UO1nFo4hs0qk12m9HIX2npGI0&_nc_ht=scontent.fjrs1-1.fna&oh=ffe41503614c033af8f0fe009ffb4b19&oe=5DEEF32C");

        mBrand.add("Kia");
        mType.add("Sorento");
        mPrice.add("180,000");
        mDiscount.add("10000");
        mSpecs.add("Ramallah - Kia Sorento Full Options at Friends Motors");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/68915723_2686106861434021_2720587853296828416_n.jpg?_nc_cat=104&_nc_oc=AQlSXpIMltMOFXFaw8tLmQNt3SIRe2lbnJC_NrRXgFXrx_p-ZoKjjdkiBO3q_5n87Iw&_nc_ht=scontent.fjrs1-1.fna&oh=c757d5719673216361912f9970578e08&oe=5DF7F8AD");


        mBrand.add("Mercedes");
        mType.add("GLC");
        mPrice.add("360,000");
        mDiscount.add("20000");
        mSpecs.add("Mercedes Benz GLC 250 2020 at Mercedes Benz Palestine");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70592418_1124805387718729_1057330753961984000_o.jpg?_nc_cat=110&_nc_oc=AQl1NeKDezXl4fS1hl6yl7706S2MglD9SZRILI5EHFXpsc71NHvE7ztM3QTTnajd4i8&_nc_ht=scontent.fjrs1-1.fna&oh=664aa6ccbc49e069bc4d9177b6cbcf6e&oe=5DF94B90");


        mBrand.add("Mercedes");
        mType.add("Maybach");
        mPrice.add("2,700,000");
        mDiscount.add("100000");
        mSpecs.add("Mercedes Benz Maybach 2020 at Mercedes Benz Palestine");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/69771026_1110327049166563_1226101652772093952_o.jpg?_nc_cat=102&_nc_oc=AQlJsSGBDciPp_E2NVOLzaqqWcf0Cr97QFbjDSmEg7ULDN2OM8xCZPVricuB_jkIk00&_nc_ht=scontent.fjrs1-1.fna&oh=2a64a9d6b454bec91a74f96b31f4a8fa&oe=5DF77493");


        mBrand.add("Mercedes");
        mType.add("GLC");
        mPrice.add("200,000");
        mDiscount.add("5000");
        mSpecs.add("Mercedes Benz GLC 250 2017 at Faqih Auto");
        mimages1.add("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70744560_2475662346025471_4206787657453273088_n.jpg?_nc_cat=108&_nc_oc=AQmnUrx37n0ICEtTvYZp7JtPpY5OmJ1joj6PaPEamCU10Y8rYSIPfrrwbG8keGIAE60&_nc_ht=scontent.fjrs1-1.fna&oh=fa5ae1fdec8656a258cda5c698f3b4ab&oe=5E0E7D13");





        recycleviewcreate2(v);


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


        Brand.add("Range Rover ");
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


    private void recycleviewcreate(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        TppDealersRecycleViewAdpater adapter = new TppDealersRecycleViewAdpater( mNames , mImageUrls , getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


    private void recycleviewcreate2(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view2);
        SpecialOffersRecycleViewAdaperBuy adapter = new SpecialOffersRecycleViewAdaperBuy( mBrand ,mType,mPrice,mDiscount,mSpecs, mimages1 , getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(),  LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


    private void recycleviewcreate3(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view3);
        LatestOffersBuyRecycleViewAdapter adapter = new LatestOffersBuyRecycleViewAdapter( Brand ,locations,Price,Dealer,images11 ,Type, getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(),  LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }



    public void flipperImages(int image){

        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        // animation
        v_flipper.setInAnimation(getContext() , android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext() , android.R.anim.slide_out_right);

    }


}

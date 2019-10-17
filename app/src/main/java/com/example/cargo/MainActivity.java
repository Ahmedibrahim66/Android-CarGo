package com.example.cargo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    SpaceNavigationView spaceNavigationView;
    BoomMenuButton bmb;


    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* //Bottom navigation bar
        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListner);
        //Bottom navigation bar
*/






        //navigation drwaer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(FirebaseAuth.getInstance().getCurrentUser() != null )
        {
            // user is logged in
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_menu);
        }else {
            // no user logged in
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_menu_no_user);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BuyFragment()).commit();



        }





        //navigation drwaer


        //Boom menu button
        bmb = (BoomMenuButton) findViewById(R.id.bmb);

        //First builder
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_001_brand_new_car_with_dollar_price_tag)
                .normalText("Buy A Car")
                .subNormalText("Search For a Car To Buy")
                .unableColorRes(R.color.colorPrimaryDark).normalColorRes(R.color.colorAccent)

                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();

                    }
                });
        bmb.addBuilder(builder);
        //First builder

        //second builder
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_002_rent_a_car_sign)
                .normalText("Rent a Car")
                .subNormalText("Search for a Rental Car")
                .unableColorRes(R.color.colorPrimaryDark).normalColorRes(R.color.colorAccent)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();

                    }
                });
        bmb.addBuilder(builder);
        //second builder

        //third builder
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_001_brand_new_car_with_dollar_price_tag)
                .normalText("Sell My Car ")
                .subNormalText("List My Car in CarGo").normalColorRes(R.color.colorAccent)
                .unableColorRes(R.color.colorPrimaryDark)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();

                    }
                });
        bmb.addBuilder(builder);
        //third builder

        //forth builder
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_cancel)
                .normalText("Cancel")
                .subNormalText("")
                .unableColorRes(R.color.colorPrimaryDark).normalColorRes(R.color.RED)

                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        // When the boom-button corresponding this builder is clicked.
                        Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();

                    }
                });
        bmb.addBuilder(builder);
        //forth builder

        bmb.setVisibility(View.INVISIBLE);


        //bottom space nav bar
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Buy a Car", R.drawable.ic_001_brand_new_car_with_dollar_price_tag));
        spaceNavigationView.addSpaceItem(new SpaceItem("Rent a Car", R.drawable.ic_002_rent_a_car_sign));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                bmb.boom();
                Toast.makeText(MainActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemIndex == 0)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                            new BuyFragment()).commit();
                    navigationView.setCheckedItem(R.id.menu_buyacar);

                }

                if(itemIndex == 1)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                            new RentFragment()).commit();
                    navigationView.setCheckedItem(R.id.menu_rentacar);

                }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                //Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
        //bottom space nav bar



        View headerView = navigationView.getHeaderView(0);
        final TextView navUsername = (TextView) headerView.findViewById(R.id.tv_usernamename);


        //work for log in database

        if(FirebaseAuth.getInstance().getCurrentUser() != null ) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            final String id = firebaseAuth.getUid();
            final int[] flag = {0};
            final int[] flag1 = {0};



            //craete account for users
            db.collection("Users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    if(id.equals(document.getId())){
                                        flag[0] =1;
                                        // if user in firestore data base then change flag
                                        db.collection("Users").document(document.getId()).get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                        navUsername.setText(documentSnapshot.getString("Name"));

                                                    }
                                                });


                                        if(document.getString("isDealer").equals("1")){
                                            //user is dealer check if he have account if not create account

                                            ///create account for dealers
                                            db.collection("Dealers")
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                            if (task.isSuccessful()) {
                                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                                    if(id.equals(document.getId())){
                                                                        flag1[0] =1;
                                                                        // if user in firestore data base then change flag
                                                                        db.collection("Dealers").document(document.getId()).get()
                                                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                                    @Override
                                                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                                                        navUsername.setText(documentSnapshot.getString("Name"));

                                                                                    }
                                                                                });



                                                                    }

                                                                }

                                                                if(flag1[0] == 0 ) {
                                                                    //if user not in firebase data base then create account
                                                                    Intent intent = new Intent(getApplicationContext(), CreateDealer.class);
                                                                    startActivity(intent);
                                                                }

                                                            } else {
                                                                Log.w(TAG, "Error getting documents.", task.getException());
                                                            }
                                                        }
                                                    });



                                        }



                                    }

                                }

                                if(flag[0] == 0 ) {
                                    //if user not in firebase data base then create account
                                    Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                                    startActivity(intent);
                                }

                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });






        }

        //create account for Dealers




        //work for log in database



    }
        //navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_buyacar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BuyFragment()).commit();

                spaceNavigationView.setVisibility(View.VISIBLE);

                break;

            case R.id.menu_rentacar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RentFragment()).commit();
                spaceNavigationView.setVisibility(View.VISIBLE);

                break;

            case R.id.SellmyCar:
                Intent intent = new Intent(this , SellMyCar.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);


                break;

            case R.id.menu_favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoriteList()).commit();
                spaceNavigationView.setVisibility(View.GONE);

                break;

            case R.id.menu_listedcars:

                Intent intenttest = new Intent(this,AddDealer.class);
                startActivity(intenttest);
                break;


            case R.id.editaccount:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DealerProfile()).commit();

                spaceNavigationView.setVisibility(View.GONE);


                break;



            case R.id.LogOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                break;

            case R.id.aboutUs:
                Toast.makeText(this, "AboutUs", Toast.LENGTH_SHORT).show();
                break;

            case R.id.SellmyCarNotLogged:
                Toast.makeText(this, "ContactUs", Toast.LENGTH_SHORT).show();
                break;



            case R.id.logIn:
                Intent intent1 = new Intent(this , SignInAuthentication.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent1);
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //navigation drwaer


    //navigation drwaer
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
          drawer.closeDrawer(GravityCompat.START);

        }else {
        super.onBackPressed();
        }
    }
    //navigation drwaer




    //Bottom navigation bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_buy:
                            selectedFragment= new BuyFragment();
                            break;


                        case R.id.nav_rent:
                            selectedFragment= new RentFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                            selectedFragment).commit();

                    return true;
                }
            };
    //Bottom navigation bar
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

}



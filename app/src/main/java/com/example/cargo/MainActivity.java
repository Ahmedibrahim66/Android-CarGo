package com.example.cargo;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    SpaceNavigationView spaceNavigationView;




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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BuyFragment()).commit();
            navigationView.setCheckedItem(R.id.account);
        }
        //navigation drwaer


        //Boom menu button
        final BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);

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
                            new BuyFragment()).commit();}

                if(itemIndex == 1)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                            new RentFragment()).commit();}

                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
        //bottom space nav bar




        // recycle view



        //recycle view


    }
        //navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BuyFragment()).commit();
                break;

            case R.id.aboutUs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BuyFragment()).commit();
                break;
            case R.id.contactus:
                Toast.makeText(this, "contactUs", Toast.LENGTH_SHORT).show();
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



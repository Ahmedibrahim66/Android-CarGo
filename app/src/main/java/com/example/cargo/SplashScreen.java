package com.example.cargo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {


    ImageView im_cAnimation;
    Animation jump,up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        im_cAnimation = findViewById(R.id.cAnimationPicture);
        jump = AnimationUtils.loadAnimation(this,R.anim.jump);
        up = AnimationUtils.loadAnimation(this,R.anim.up);

        im_cAnimation.setAnimation(jump);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                im_cAnimation.setAnimation(up);
            }
        }, 2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 4000);





    }
}

package com.example.matrusnehhealth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView imgLogo;
    TextView txtSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgLogo = findViewById(R.id.imgLogo);
        txtSplash = findViewById(R.id.txtSplash);

        // Fade Animation
        Animation animation =
                new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(2000);

        imgLogo.startAnimation(animation);
        txtSplash.startAnimation(animation);

        // Move to MainActivity
        new Handler().postDelayed(() -> {

            Intent intent =
                    new Intent(
                            SplashActivity.this,
                            MainActivity.class
                    );

            startActivity(intent);

            finish();

        }, 2500);
    }
}
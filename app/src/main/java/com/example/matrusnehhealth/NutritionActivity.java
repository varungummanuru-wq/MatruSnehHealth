package com.example.matrusnehhealth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionActivity extends AppCompatActivity {

    Button btnWater;
    int waterCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        btnWater = findViewById(R.id.btnWater);

        btnWater.setOnClickListener(v -> {
            waterCount++;

            Toast.makeText(
                    this,
                    "Water Glass Count: " + waterCount,
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}
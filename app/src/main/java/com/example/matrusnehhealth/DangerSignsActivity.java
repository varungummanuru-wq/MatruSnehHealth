package com.example.matrusnehhealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DangerSignsActivity extends AppCompatActivity {

    Button btnEmergencyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_signs);

        btnEmergencyCall = findViewById(R.id.btnEmergencyCall);

        btnEmergencyCall.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:108"));
            startActivity(intent);

        });
    }
}
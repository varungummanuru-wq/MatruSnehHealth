package com.example.matrusnehhealth;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class EmergencyActivity extends AppCompatActivity {

    EditText edtEmergencyNumber;
    Button btnSaveEmergency, btnCallEmergency;
    TextView txtEmergency;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        edtEmergencyNumber = findViewById(R.id.edtEmergencyNumber);
        btnSaveEmergency = findViewById(R.id.btnSaveEmergency);
        btnCallEmergency = findViewById(R.id.btnCallEmergency);
        txtEmergency = findViewById(R.id.txtEmergency);

        sharedPreferences = getSharedPreferences("EmergencyData", MODE_PRIVATE);

        // Load saved number
        String savedNumber = sharedPreferences.getString("number", "");

        if (!savedNumber.isEmpty()) {
            txtEmergency.setText("Saved Number: " + savedNumber);
        }

        // Save Button
        btnSaveEmergency.setOnClickListener(view -> {

            String number = edtEmergencyNumber.getText().toString();

            // Validation
            if (number.isEmpty()) {
                edtEmergencyNumber.setError("Enter Emergency Number");
                edtEmergencyNumber.requestFocus();
                return;
            }

            // Save permanently
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("number", number);
            editor.apply();

            Toast.makeText(
                    this,
                    "Emergency Number Saved",
                    Toast.LENGTH_SHORT
            ).show();

            txtEmergency.setText(
                    "Saved Number: " + number
            );
        });

        // Call Button
        btnCallEmergency.setOnClickListener(view -> {

            String number = sharedPreferences.getString("number", "");

            if (number.isEmpty()) {
                Toast.makeText(
                        this,
                        "No Emergency Number Saved",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1
                );

                return;
            }

            startActivity(intent);
        });
    }
}
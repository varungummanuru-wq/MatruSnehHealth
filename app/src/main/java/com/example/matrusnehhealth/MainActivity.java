package com.example.matrusnehhealth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    MaterialCardView cardKick,
            cardHistory,
            cardNutrition,
            cardAppointment,
            cardBMI,
            cardEmergency,
            cardMedicine,
            cardGrowth,
            cardDangerSigns,
            cardStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Cards

        cardKick = findViewById(R.id.cardKick);

        cardHistory = findViewById(R.id.cardHistory);

        cardNutrition = findViewById(R.id.cardNutrition);

        cardAppointment = findViewById(R.id.cardAppointment);

        cardBMI = findViewById(R.id.cardBMI);

        cardEmergency = findViewById(R.id.cardEmergency);

        cardMedicine = findViewById(R.id.cardMedicine);

        cardGrowth = findViewById(R.id.cardGrowth);

        cardDangerSigns = findViewById(R.id.cardDangerSigns);

        cardStatistics = findViewById(R.id.cardStatistics);


        // Kick Counter
        cardKick.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                KickCounterActivity.class
                        )
                )
        );

        // History
        cardHistory.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                HistoryActivity.class
                        )
                )
        );

        // Nutrition
        cardNutrition.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                NutritionActivity.class
                        )
                )
        );

        // Appointment
        cardAppointment.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                AppointmentActivity.class
                        )
                )
        );

        // BMI
        cardBMI.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                BMIActivity.class
                        )
                )
        );

        // Emergency
        cardEmergency.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                EmergencyActivity.class
                        )
                )
        );

        // Medicine
        cardMedicine.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                MedicineActivity.class
                        )
                )
        );

        // Growth
        cardGrowth.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                GrowthActivity.class
                        )
                )
        );

        // Danger Signs
        cardDangerSigns.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                DangerSignsActivity.class
                        )
                )
        );

        // Statistics
        cardStatistics.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                MainActivity.this,
                                StatisticsActivity.class
                        )
                )
        );
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Do you want to exit?")
                .setPositiveButton(
                        "Yes",
                        (dialog, which) -> finish()
                )
                .setNegativeButton(
                        "No",
                        null
                )
                .show();
    }
}
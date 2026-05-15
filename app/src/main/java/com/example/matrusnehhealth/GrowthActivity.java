package com.example.matrusnehhealth;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class GrowthActivity extends AppCompatActivity {

    Spinner spinnerWeeks;
    TextView txtGrowthInfo;

    String[] weeks = {
            "Week 1",
            "Week 5",
            "Week 10",
            "Week 15",
            "Week 20",
            "Week 25",
            "Week 30",
            "Week 35",
            "Week 40"
    };

    String[] growthInfo = {
            "Pregnancy begins. Body starts preparing for baby.",
            "Baby is the size of a sesame seed. Heart starts beating.",
            "Baby is the size of a strawberry. Tiny arms and legs develop.",
            "Baby can move arms and legs. Bones are developing.",
            "Baby is the size of a banana. Mother may feel movements.",
            "Baby responds to sounds. Weight increases steadily.",
            "Baby’s brain develops rapidly. Eyes can open.",
            "Baby gains body fat and prepares for birth.",
            "Baby is fully developed and ready for delivery."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth);

        spinnerWeeks = findViewById(R.id.spinnerWeeks);
        txtGrowthInfo = findViewById(R.id.txtGrowthInfo);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                weeks
        );

        spinnerWeeks.setAdapter(adapter);

        spinnerWeeks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       android.view.View view,
                                       int position,
                                       long id) {

                txtGrowthInfo.setText(growthInfo[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
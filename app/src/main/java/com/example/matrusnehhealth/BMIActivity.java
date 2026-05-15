package com.example.matrusnehhealth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMIActivity extends AppCompatActivity {

    EditText edtWeight, edtHeight;
    Button btnCalculate;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        edtWeight = findViewById(R.id.edtWeight);
        edtHeight = findViewById(R.id.edtHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        btnCalculate.setOnClickListener(v -> {

            double weight = Double.parseDouble(
                    edtWeight.getText().toString());

            double height = Double.parseDouble(
                    edtHeight.getText().toString());

            double bmi = weight / (height * height);

            txtResult.setText("BMI = " + bmi);
        });
    }
}
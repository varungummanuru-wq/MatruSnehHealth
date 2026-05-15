package com.example.matrusnehhealth;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    EditText edtDoctor, edtDate;
    Button btnSaveAppointment;
    TextView txtAppointment, txtCountdown;

    SharedPreferences sharedPreferences;

    int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        edtDoctor = findViewById(R.id.edtDoctor);
        edtDate = findViewById(R.id.edtDate);
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment);
        txtAppointment = findViewById(R.id.txtAppointment);
        txtCountdown = findViewById(R.id.txtCountdown);

        // SharedPreferences
        sharedPreferences = getSharedPreferences(
                "AppointmentData",
                MODE_PRIVATE
        );

        // Load saved data
        String savedDoctor =
                sharedPreferences.getString("doctor", "");

        String savedDate =
                sharedPreferences.getString("date", "");

        if (!savedDoctor.isEmpty()
                && !savedDate.isEmpty()) {

            txtAppointment.setText(
                    "Appointment Saved\n\nDoctor: "
                            + savedDoctor
                            + "\nDate: "
                            + savedDate
            );
        }

        // Load saved countdown
        int savedYear =
                sharedPreferences.getInt("year", -1);

        int savedMonth =
                sharedPreferences.getInt("month", -1);

        int savedDay =
                sharedPreferences.getInt("day", -1);

        if (savedYear != -1) {

            calculateCountdown(
                    savedYear,
                    savedMonth,
                    savedDay
            );
        }

        // Date Picker
        edtDate.setOnClickListener(v -> {

            Calendar calendar =
                    Calendar.getInstance();

            int year =
                    calendar.get(Calendar.YEAR);

            int month =
                    calendar.get(Calendar.MONTH);

            int day =
                    calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            AppointmentActivity.this,

                            (view,
                             year1,
                             month1,
                             dayOfMonth) -> {

                                selectedYear = year1;
                                selectedMonth = month1;
                                selectedDay = dayOfMonth;

                                String date =
                                        dayOfMonth + "/"
                                                + (month1 + 1)
                                                + "/"
                                                + year1;

                                edtDate.setText(date);

                            },

                            year,
                            month,
                            day
                    );

            datePickerDialog.show();
        });

        // Save Button
        btnSaveAppointment.setOnClickListener(view -> {

            String doctor =
                    edtDoctor.getText()
                            .toString()
                            .trim();

            String date =
                    edtDate.getText()
                            .toString()
                            .trim();

            // Validation
            if (doctor.isEmpty()) {

                edtDoctor.setError(
                        "Enter Doctor Name"
                );

                edtDoctor.requestFocus();
                return;
            }

            if (date.isEmpty()) {

                edtDate.setError(
                        "Enter Appointment Date"
                );

                edtDate.requestFocus();
                return;
            }

            // Save permanently
            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.putString("doctor", doctor);
            editor.putString("date", date);

            editor.putInt("year", selectedYear);
            editor.putInt("month", selectedMonth);
            editor.putInt("day", selectedDay);

            editor.apply();

            Toast.makeText(
                    this,
                    "Appointment Saved Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            txtAppointment.setText(
                    "Appointment Saved\n\nDoctor: "
                            + doctor
                            + "\nDate: "
                            + date
            );

            calculateCountdown(
                    selectedYear,
                    selectedMonth,
                    selectedDay
            );
        });
    }

    // Countdown Method
    private void calculateCountdown(
            int year,
            int month,
            int day) {

        Calendar selectedDate =
                Calendar.getInstance();

        selectedDate.set(year, month, day);

        Calendar currentDate =
                Calendar.getInstance();

        long difference =
                selectedDate.getTimeInMillis()
                        - currentDate.getTimeInMillis();

        long days =
                difference / (1000 * 60 * 60 * 24);

        if (days >= 0) {

            txtCountdown.setText(
                    "Next appointment in "
                            + days
                            + " days"
            );

        } else {

            txtCountdown.setText(
                    "Appointment date passed"
            );
        }
    }
}
package com.example.matrusnehhealth;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class MedicineActivity extends AppCompatActivity {

    EditText edtMedicine, edtTime;
    Button btnSaveMedicine;
    TextView txtMedicineReminder;

    SharedPreferences sharedPreferences;

    int selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        // Notification Permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.POST_NOTIFICATIONS
                    },
                    101
            );
        }

        edtMedicine = findViewById(R.id.edtMedicine);
        edtTime = findViewById(R.id.edtTime);
        btnSaveMedicine = findViewById(R.id.btnSaveMedicine);

        txtMedicineReminder =
                findViewById(R.id.txtMedicineReminder);

        // SharedPreferences
        sharedPreferences =
                getSharedPreferences(
                        "MedicineData",
                        MODE_PRIVATE
                );

        // Load saved data
        String savedMedicine =
                sharedPreferences.getString(
                        "medicine",
                        ""
                );

        String savedTime =
                sharedPreferences.getString(
                        "time",
                        ""
                );

        if (!savedMedicine.isEmpty()
                && !savedTime.isEmpty()) {

            txtMedicineReminder.setText(
                    "Saved Reminder\n\nMedicine: "
                            + savedMedicine
                            + "\nTime: "
                            + savedTime
            );
        }

        // Time Picker
        edtTime.setOnClickListener(v -> {

            Calendar calendar =
                    Calendar.getInstance();

            int hour =
                    calendar.get(Calendar.HOUR_OF_DAY);

            int minute =
                    calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(
                            MedicineActivity.this,

                            (view,
                             hourOfDay,
                             minute1) -> {

                                selectedHour = hourOfDay;
                                selectedMinute = minute1;

                                String time =
                                        hourOfDay
                                                + ":"
                                                + minute1;

                                edtTime.setText(time);
                            },

                            hour,
                            minute,
                            false
                    );

            timePickerDialog.show();
        });

        // Save Button
        btnSaveMedicine.setOnClickListener(view -> {

            String medicine =
                    edtMedicine.getText()
                            .toString()
                            .trim();

            String time =
                    edtTime.getText()
                            .toString()
                            .trim();

            // Validation
            if (medicine.isEmpty()) {

                edtMedicine.setError(
                        "Enter Medicine Name"
                );

                edtMedicine.requestFocus();
                return;
            }

            if (time.isEmpty()) {

                edtTime.setError(
                        "Enter Reminder Time"
                );

                edtTime.requestFocus();
                return;
            }

            // Save permanently
            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.putString("medicine", medicine);
            editor.putString("time", time);

            editor.apply();

            // Schedule notification
            scheduleMedicineReminder();

            Toast.makeText(
                    this,
                    "Medicine Reminder Saved",
                    Toast.LENGTH_SHORT
            ).show();

            txtMedicineReminder.setText(
                    "Saved Reminder\n\nMedicine: "
                            + medicine
                            + "\nTime: "
                            + time
            );
        });
    }

    // AlarmManager Logic
    private void scheduleMedicineReminder() {

        Calendar calendar =
                Calendar.getInstance();

        calendar.set(
                Calendar.HOUR_OF_DAY,
                selectedHour
        );

        calendar.set(
                Calendar.MINUTE,
                selectedMinute
        );

        calendar.set(
                Calendar.SECOND,
                0
        );

        // If selected time already passed,
        // schedule for next day
        if (calendar.before(Calendar.getInstance())) {

            calendar.add(Calendar.DATE, 1);
        }

        Intent intent =
                new Intent(
                        this,
                        MedicineReceiver.class
                );

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                                | PendingIntent.FLAG_IMMUTABLE
                );

        AlarmManager alarmManager =
                (AlarmManager)
                        getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {

            alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    pendingIntent
            );
        }

        // Instant test notification
        NotificationHelper.showNotification(
                MedicineActivity.this,
                "Medicine Reminder",
                "Time to take medicine"
        );
    }
}
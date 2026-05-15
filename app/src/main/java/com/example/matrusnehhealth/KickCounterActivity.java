package com.example.matrusnehhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.matrusnehhealth.database.AppDatabase;
import com.example.matrusnehhealth.database.KickData;

public class KickCounterActivity extends AppCompatActivity {

    TextView txtCount, txtStatus;

    Button btnKickCount, btnReset, btnHistory;

    ProgressBar progressKicks;

    int count = 0;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kick_counter);

        txtCount = findViewById(R.id.txtCount);
        txtStatus = findViewById(R.id.txtStatus);

        btnKickCount = findViewById(R.id.btnKickCount);
        btnReset = findViewById(R.id.btnReset);
        btnHistory = findViewById(R.id.btnHistory);

        progressKicks = findViewById(R.id.progressKicks);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "kick_database"
        ).allowMainThreadQueries().build();

        btnKickCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                txtCount.setText(String.valueOf(count));

                progressKicks.setProgress(count);

                // Status Logic
                if (count < 5) {

                    txtStatus.setText(
                            "Baby movement is low"
                    );

                } else if (count < 15) {

                    txtStatus.setText(
                            "Baby movement is normal"
                    );

                } else {

                    txtStatus.setText(
                            "Baby is very active today"
                    );
                }

                KickData data = new KickData();

                data.count = count;

                data.time = java.text.DateFormat
                        .getDateTimeInstance()
                        .format(new java.util.Date());

                db.kickDao().insert(data);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = 0;

                txtCount.setText(String.valueOf(count));

                progressKicks.setProgress(0);

                txtStatus.setText(
                        "Start counting baby movements"
                );
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        KickCounterActivity.this,
                        HistoryActivity.class
                );

                startActivity(intent);
            }
        });
    }
}
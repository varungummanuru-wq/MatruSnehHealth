package com.example.matrusnehhealth;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.matrusnehhealth.database.AppDatabase;
import com.example.matrusnehhealth.database.KickData;

import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    TextView txtTotalKicks,
            txtLastKick,
            txtKickStatus;

    ProgressBar progressKicks;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        txtTotalKicks =
                findViewById(R.id.txtTotalKicks);

        txtLastKick =
                findViewById(R.id.txtLastKick);

        txtKickStatus =
                findViewById(R.id.txtKickStatus);

        progressKicks =
                findViewById(R.id.progressKicks);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "kick_database"
        ).allowMainThreadQueries().build();

        List<KickData> kickList =
                db.kickDao().getAllKicks();

        int totalKicks = kickList.size();

        txtTotalKicks.setText(
                "Total Kicks Recorded: "
                        + totalKicks
        );

        progressKicks.setProgress(
                Math.min(totalKicks, 20)
        );

        if (totalKicks > 0) {

            KickData lastKick =
                    kickList.get(totalKicks - 1);

            txtLastKick.setText(
                    "Last Kick:\n"
                            + lastKick.time
            );

        } else {

            txtLastKick.setText(
                    "No kick data available"
            );
        }

        // Status Logic
        if (totalKicks < 5) {

            txtKickStatus.setText(
                    "Status: Baby movement is low"
            );

        } else if (totalKicks < 15) {

            txtKickStatus.setText(
                    "Status: Baby movement is normal"
            );

        } else {

            txtKickStatus.setText(
                    "Status: Baby is very active"
            );
        }
    }
}
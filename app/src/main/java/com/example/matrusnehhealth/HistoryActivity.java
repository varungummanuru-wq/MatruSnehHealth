package com.example.matrusnehhealth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.matrusnehhealth.database.AppDatabase;
import com.example.matrusnehhealth.database.KickData;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    TextView txtHistory;

    Button btnDelete;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        txtHistory = findViewById(R.id.txtHistory);

        btnDelete = findViewById(R.id.btnDelete);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "kick_database"
        ).allowMainThreadQueries().build();

        List<KickData> dataList = db.kickDao().getAllData();

        StringBuilder builder = new StringBuilder();

        for (KickData data : dataList) {

            builder.append("Kick Count: ")
                    .append(data.count)
                    .append("\n");

            builder.append("Time: ")
                    .append(data.time)
                    .append("\n\n");
        }

        txtHistory.setText(builder.toString());

        btnDelete.setOnClickListener(v -> {

            db.kickDao().deleteAll();

            txtHistory.setText("History Cleared");

        });
    }
}
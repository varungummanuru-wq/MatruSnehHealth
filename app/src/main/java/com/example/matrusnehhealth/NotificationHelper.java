package com.example.matrusnehhealth;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    public static void showNotification(
            Context context,
            String title,
            String message) {

        String channelId = "medicine_channel";

        // Create channel for Android 8+
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            channelId,
                            "Medicine Reminder",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            NotificationManager manager =
                    context.getSystemService(
                            NotificationManager.class
                    );

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        context,
                        channelId
                )

                        .setSmallIcon(
                                android.R.drawable.ic_dialog_info
                        )

                        .setContentTitle(title)

                        .setContentText(message)

                        .setPriority(
                                NotificationCompat.PRIORITY_HIGH
                        )

                        .setAutoCancel(true);

        NotificationManagerCompat managerCompat =
                NotificationManagerCompat.from(context);

        try {

            managerCompat.notify(
                    1,
                    builder.build()
            );

        } catch (SecurityException e) {

            e.printStackTrace();
        }
    }
}
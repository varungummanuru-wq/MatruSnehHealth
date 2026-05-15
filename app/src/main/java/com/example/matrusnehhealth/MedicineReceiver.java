package com.example.matrusnehhealth;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class MedicineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context,
                          Intent intent) {

        String channelId = "medicine_channel";

        NotificationManager manager =
                (NotificationManager)
                        context.getSystemService(
                                Context.NOTIFICATION_SERVICE);

        // Notification channel
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            channelId,
                            "Medicine Reminder",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            manager.createNotificationChannel(channel);
        }

        Intent openIntent =
                new Intent(context,
                        MainActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        openIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                                | PendingIntent.FLAG_IMMUTABLE
                );

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        context,
                        channelId
                )

                        .setSmallIcon(
                                android.R.drawable.ic_dialog_info
                        )

                        .setContentTitle(
                                "Medicine Reminder"
                        )

                        .setContentText(
                                "Time to take your medicine"
                        )

                        .setPriority(
                                NotificationCompat.PRIORITY_HIGH
                        )

                        .setContentIntent(
                                pendingIntent
                        )

                        .setAutoCancel(true);

        manager.notify(1, builder.build());
    }
}
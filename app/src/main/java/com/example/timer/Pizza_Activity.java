package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Pizza_Activity extends AppCompatActivity {
    public TextView time;
    public ProgressBar progress;

    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Pizza channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        time = findViewById(R.id.timer_text);
        progress = findViewById(R.id.pizza_progress);

    }

    public void start_pizza(View view) {
        new CountDownTimer(75000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText(DateUtils.formatElapsedTime(l / 1000));
                progress.setProgress((int) (l/1000));
            }

            @Override
            public void onFinish() {
                time.setText("-");
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(Pizza_Activity.this, CHANNEL_ID)
                                .setContentTitle("Напоминание")
                                .setContentText("Пицца готова")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH);
                }
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(Pizza_Activity.this);
                notificationManager.notify(NOTIFY_ID, builder.build());
            }
        }.start();
    }
}
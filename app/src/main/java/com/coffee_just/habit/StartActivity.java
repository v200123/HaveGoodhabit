package com.coffee_just.habit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.coffee_just.habit.Model.categoryLab;
import com.google.android.material.navigation.NavigationView;

/*用于显示APP启动页面的*/
public class StartActivity extends AppCompatActivity {
    private boolean lag = true;

    private Button skipBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_picture);

        skipBtn = findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener((view)->{
            finish();
            Intent intent = new Intent(StartActivity.this , MainActivity.class);
            startActivity(intent);
            lag=false;
        });

        Toast.makeText(StartActivity.this , "wait 5s!" , Toast.LENGTH_LONG).show();
        new Handler().postDelayed(()->{
            if(lag){
                finish();
                Intent intent = new Intent(StartActivity.this , MainActivity.class);
                startActivity(intent);
        }}
        ,5000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Time";
            String channelName = "通知信息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

        }

}
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}

package com.coffee_just.habit;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coffee_just.habit.Model.Category;
import com.coffee_just.habit.Model.TimeLog;
import com.coffee_just.habit.Model.categoryLab;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private categoryLab mCategoryLab = categoryLab.getCategoryLab();
    private ArrayList<Category> mCategories = mCategoryLab.getCategories();
    private TimeLog log;
    private Spinner mSpinner;
    private NavigationView mNavigationView;
    private EditText inputTime, inputTime2;
    private TextView showTime, TipText;
    private Button BtnEnsure;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.myNav);
        mSpinner = findViewById(R.id.spinner);
        inputTime = findViewById(R.id.InPutTime1);
        TipText = findViewById(R.id.textView);
        inputTime2 = findViewById(R.id.InPutTime2);
        showTime = findViewById(R.id.showTime);
        BtnEnsure = findViewById(R.id.EnsureTime);
        BtnEnsure.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> list = new ArrayList<>();
        for (Category category : mCategories) {
            list.add(category.getCategoryTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    //    @SuppressLint("HandlerLeak")
    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.arg1;
//                    String information = (String) msg.obj;
                if (msg.what == 1) {
                    inputTime.setText(i / 60 + "");
                    inputTime2.setText(i % 60 + "");
                }
                if (msg.arg1 == 0) {
                    showTime.setText("完成");
                    Toast.makeText(getApplicationContext(), "认真时间已经完成啦,请休息一会，然后再来吧", Toast.LENGTH_SHORT).show();
                    TipText.setText("输入时间：");
                    mSpinner.setVisibility(View.VISIBLE);
                    log.setSuccess(true);
                    log.setCategory((String) mSpinner.getSelectedItem());
                    log.inputLog(log);
                    inputTime.setText("00");
                    inputTime2.setText("00");
                    BtnEnsure.setVisibility(View.VISIBLE);
                }
            }
        };
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.category:
                    Intent i = new Intent(this, CategoryListActivity.class);
                    startActivity(i);
                    return true;
                case R.id.logTime:
                    Intent i1 = new Intent(this,TimeLogActivity.class);
                    startActivity(i1);
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onClick(View v) {
        int hours, minus;
        String hour, minu;
        switch (v.getId()) {
            case R.id.EnsureTime:
//                inputTime.setVisibility(View.INVISIBLE);
//                showTime.setVisibility(View.VISIBLE);
                mSpinner.setVisibility(View.INVISIBLE);
                TipText.setText("剩余时间为：");
                hour = inputTime.getText().toString();
                hours = Integer.parseInt(hour);
                minu = inputTime2.getText().toString();
                minus = Integer.parseInt(minu);
               log = new TimeLog(new Date());
                sendTimeLog(hour+"时 "+minu+"分");
                BtnEnsure.setVisibility(View.INVISIBLE);
//                showTime.setText("倒计时剩余时间为："+num);
                new Thread(() -> {
                    for (int i = hours * 60 + minus; i >= 0; i--) {
                        try {
                            Thread.sleep(1000);//实现读分
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message message = mHandler.obtainMessage();
                        message.what = 1;
                        message.arg1 = i;
                        mHandler.sendMessage(message);//发送信息
                    }
                }).start();
                inputTime.clearFocus();
                inputTime2.clearFocus();

                break;
        }
    }

    private void sendTimeLog(String Msg)
    {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this,"Time")
                .setSmallIcon(R.drawable.icon1)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon1))
                .setContentText("计时的时长为"+Msg)
                .setContentTitle("计时开始了，请认真工作哟")
                .setAutoCancel(true)
                .build();
        manager.notify(1,notification);
    }
}

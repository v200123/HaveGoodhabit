package com.coffee_just.habit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
/*用于显示APP启动页面的*/
public class StartActivity extends AppCompatActivity {
    private boolean lag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_picture);
        Toast.makeText(StartActivity.this , "wait 5s!" , Toast.LENGTH_LONG).show();
        new Handler().postDelayed(()->{
            if(lag){
                finish();
                Intent intent = new Intent(StartActivity.this , MainApp.class);
                startActivity(intent);
        }}
        ,5000);
    }
}

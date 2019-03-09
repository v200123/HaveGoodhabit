package com.coffee_just.habit;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffee_just.habit.Model.TimeLog;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TimeLogActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_time);
        RecyclerView recyclerView = findViewById(R.id.Log_Time_RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TimeAdapter adapter = new TimeAdapter();
        recyclerView.setAdapter(adapter);
    }
}





       class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_time_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TimeLog log = TimeLog.getArrayList().get(position);
            holder.showLog.setText("完成时间为："+new SimpleDateFormat("yyyy-MM-DD  HH-mm-ss").format(log.getDate())+"\n分类为："+log.getCategory() );
            if(log.getSuccess())
            {
                holder.isSuccessedColor.setBackgroundColor(Color.rgb(144,238,144));
            }
            else {
                holder.isSuccessedColor.setBackgroundColor(Color.rgb(255,48 ,48));
            }
            //todo  这里处理组件的各种属性设置   完成
    }

    @Override
    public int getItemCount() {
        return TimeLog.getArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView isSuccessedColor;
        private TextView showLog;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isSuccessedColor = itemView.findViewById(R.id.isSuccessedImg);
            showLog = itemView.findViewById(R.id.showLog);

        }
    }
}

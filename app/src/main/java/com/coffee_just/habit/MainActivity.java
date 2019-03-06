package com.coffee_just.habit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.coffee_just.habit.Model.Category;
import com.coffee_just.habit.Model.categoryLab;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private categoryLab mCategoryLab = categoryLab.getCategoryLab();
    private ArrayList<Category> mCategories= mCategoryLab.getCategories();
    private Spinner mSpinner;
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.myNav);
        mSpinner = findViewById(R.id.spinner);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String>  list = new ArrayList<>();
        for (Category category : mCategories){
            list.add(category.getCategoryTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId())
            {
                case R.id.category:
                    Intent i = new Intent(this,CategoryListActivity.class);
                    startActivity(i);
                    return true;
            }
            return false;
        });
    }
}

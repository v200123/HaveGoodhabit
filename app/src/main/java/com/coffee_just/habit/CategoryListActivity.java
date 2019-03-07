package com.coffee_just.habit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.coffee_just.habit.Model.Category;
import com.coffee_just.habit.Model.categoryLab;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryListActivity extends AppCompatActivity {
    ArrayList<Category> mCategories = categoryLab.getCategoryLab().getCategories();
    //    private ArrayAdapter<Category> mAdapter;
    private ListView mListView;
    private Button mButton;
    private SearchView mSearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_category_list);
        mListView = findViewById(R.id.category_list);
        categoryAdapter mAdapter = new categoryAdapter(this);
        mListView.setAdapter(mAdapter);
        mSearchView = findViewById(R.id.findCategory);
        mButton = findViewById(R.id.addCategory);
        mButton.setVisibility(View.INVISIBLE);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for (Category category : mCategories) {
                    if (!category.getCategoryTitle().contains(newText)) {
                        mButton.setVisibility(View.VISIBLE);
                        Category category1 = new Category(newText);
                        mButton.setOnClickListener(view -> {
                            categoryLab.getCategoryLab().addCategory(category1);
//                               Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                            mAdapter.notifyDataSetChanged();
                            mButton.setVisibility(View.INVISIBLE);
                            mSearchView.setQuery("",false);
                        });

                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    class categoryAdapter extends BaseAdapter {
        private ViewHolder mViewHolder;
        private LayoutInflater mInflater;

        categoryAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mCategories.size();
        }

        @Override
        public Object getItem(int position) {
            return mCategories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.habit_category_item, null);
                mViewHolder.categoryList = convertView.findViewById(R.id.addedCategory);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            mViewHolder.categoryList.setText(mCategories.get(position).getCategoryTitle());
            return convertView;
        }

    }

    static class ViewHolder {
        private TextView categoryList;


    }

}


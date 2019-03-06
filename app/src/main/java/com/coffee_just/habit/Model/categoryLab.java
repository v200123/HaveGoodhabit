package com.coffee_just.habit.Model;

import java.util.ArrayList;

public class categoryLab {
    private static categoryLab sCategoryLab;
    private ArrayList<Category> mCategories ;

    private categoryLab() {
        mCategories = new ArrayList<>();
        Category category = new Category("学习");
        mCategories.add(category);
        mCategories.add(new Category("生活"));
    }

    public static categoryLab getCategoryLab() {
       if (sCategoryLab==null)
       {
           sCategoryLab = new categoryLab();
       }
           return sCategoryLab;

    }

    public ArrayList<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(ArrayList<Category> categories) {
        mCategories = categories;
    }

public void addCategory(Category category)
{
    mCategories.add(category);
 }}

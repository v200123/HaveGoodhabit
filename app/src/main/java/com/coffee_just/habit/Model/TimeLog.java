package com.coffee_just.habit.Model;

import java.util.ArrayList;
import java.util.Date;

public class TimeLog {
    public static ArrayList<TimeLog> getArrayList() {
        return mArrayList;
    }

    private static ArrayList mArrayList = new ArrayList();
    private Date mDate;
    private Boolean Success;
    private String category;

    public TimeLog(Date date) {
        mDate = date;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public Date getDate() {
        return mDate;
    }

    public void inputLog(TimeLog timeLog)
    {
        mArrayList.add(timeLog);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

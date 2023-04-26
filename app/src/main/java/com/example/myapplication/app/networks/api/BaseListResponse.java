package com.example.myapplication.app.networks.api;

import java.util.ArrayList;

public class BaseListResponse<T> {
    public int statusCode;
    public ArrayList<T> data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public boolean hasData() {
        return data != null && data.size() > 0;
    }
}
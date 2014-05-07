package com.example.volleyapp.app;

import java.util.ArrayList;

public class DataStore {
    private ArrayList<String> mUrls;

    public DataStore() {
        mUrls = new ArrayList<>();
    }

    public void addUrl(String url) {
        mUrls.add(url);
    }

    public String get(int position) {
        return mUrls.get(position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataStore: [");

        for(String url : mUrls) {
            sb.append("\n\t").append(url);
        }
        sb.append("\n]");

        return sb.toString();
    }
}
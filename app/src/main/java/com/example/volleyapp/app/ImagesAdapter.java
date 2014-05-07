package com.example.volleyapp.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
* Created by chris on 07/05/2014.
*/
class ImagesAdapter extends BaseAdapter {
    private List<String> mUrls;
    private final Context mContext;

    public ImagesAdapter(Context context, List<String> urls) {
        super();

        mContext = context;
        mUrls = (urls == null ? new ArrayList<String>() : urls);
    }

    public void changeUrls(List<String> urls) {
        mUrls = urls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return mUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        if(holder == null) {
            holder = new ViewHolder();
            holder.text1 = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(holder);
        }

        holder.text1.setText(getItem(position).toString());

        return view;
    }

    private class ViewHolder {
        TextView text1;
    }
}

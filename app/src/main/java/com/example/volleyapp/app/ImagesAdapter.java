package com.example.volleyapp.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.lang.ref.WeakReference;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        if(holder == null) {
            holder = new ViewHolder();
            holder.image1 = (ImageView) view.findViewById(R.id.image1);
            view.setTag(holder);
        }

        final WeakReference<ViewHolder> holderWeakReference = new WeakReference<>(holder);
        String url = (String) getItem(position);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                ViewHolder weakHolder = holderWeakReference.get();

                if(weakHolder != null) {
                    weakHolder.image1.setImageBitmap(bitmap);
                }
            }
        }, 0, 0, null, null);

        VolleyApplication.getInstance().getRequestQueue().add(imageRequest);
        holder.image1.setImageResource(R.drawable.ic_launcher);

        return view;
    }

    private class ViewHolder {
        ImageView image1;
    }
}

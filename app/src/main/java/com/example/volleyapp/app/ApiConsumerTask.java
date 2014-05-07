package com.example.volleyapp.app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiConsumerTask extends AsyncTask<JSONObject, Integer, Boolean> {
    private final static String TAG = ApiConsumerTask.class.getSimpleName();
    private DataStore mDataStore;
    private ApiConsumerListener mListener;

    public ApiConsumerTask(DataStore dataStore, ApiConsumerListener listener) {
        mDataStore = dataStore;
        mListener = listener;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        mListener.onApiConsumerFinished(success);
    }

    @Override
    protected Boolean doInBackground(JSONObject... params) {
        JSONObject jsonRoot = params[0];

        try {
            // Check the JSON contains an `images` key
            if(!jsonRoot.has("images")) {
                return false;
            }

            JSONArray jsonImages = jsonRoot.getJSONArray("images");

            for(int i = 0; i < jsonImages.length(); i++) {
                String url = jsonImages.getString(i);
                mDataStore.addUrl(url);
            }

            return true;
        }
        catch(JSONException e) {
            Log.w(TAG, e);
            return false;
        }
    }

    public interface ApiConsumerListener {
        void onApiConsumerFinished(boolean success);
    }
}

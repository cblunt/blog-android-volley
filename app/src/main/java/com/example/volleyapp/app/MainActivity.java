package com.example.volleyapp.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends ActionBarActivity implements ApiConsumerTask.ApiConsumerListener {

    private ImagesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new ImagesAdapter(this, null);

        mTextView = (TextView) findViewById(R.id.text1);

        ListView listView = (ListView) findViewById(R.id.list1);
        listView.setAdapter(mAdapter);

        // Create a new JsonObjectRequest.
        JsonObjectRequest request = new JsonObjectRequest("http://cblunt.github.io/blog-android-volley/response.json", null,
                                                          new Response.Listener<JSONObject>() {
                                                              @Override
                                                              public void onResponse(JSONObject response) {
                                                                  new ApiConsumerTask(MainActivity.this).execute(response);
                                                              }
                                                          },
                                                          new Response.ErrorListener() {
                                                              @Override
                                                              public void onErrorResponse(VolleyError error) {
                                                                  mTextView.setText(error.toString());
                                                              }
                                                          }
        );

        // With the request created, simply add it to our Application's RequestQueue
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    private TextView mTextView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onApiConsumerFinished(boolean success, List<String> urls) {
        mAdapter.changeUrls(urls);
    }

}

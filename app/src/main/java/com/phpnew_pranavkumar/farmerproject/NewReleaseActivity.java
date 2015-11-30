package com.phpnew_pranavkumar.farmerproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.phpnew_pranavkumar.farmerproject.adapter.NewReleaseAdapter;
import com.phpnew_pranavkumar.farmerproject.bean.MovieData;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kehooo on 11/28/2015.
 */
public class NewReleaseActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    NewReleaseAdapter mAdapter;
    JSONObject jsonobject;
    JSONArray jsonarray;
    String json;

    private ArrayList<MovieData> feedMovieList = new ArrayList<MovieData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrealese);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarrls);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_home);
        ab.setTitle("New Release");
        ab.setDisplayHomeAsUpEnabled(true);

        new DownloadJSON().execute();

        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);


        // StaggeredGridLayoutManager mLayoutManager1 = new StaggeredGridLayoutManager(2,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private class DownloadJSON extends AsyncTask<String, String, String> {


        @Override
        public String doInBackground(String... params) {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/newmoviejson")
                    .build();
            Call call=okHttpClient.newCall(request);

            try {

                Response response=call.execute();

                if(response.isSuccessful())
                {
                    Log.v("okHTTP", response.body().toString());
                }


                json = response.body().string();


                JSONObject reader = new JSONObject(json);
                jsonarray = reader.getJSONArray("movies");

                for (int i = 0; i < jsonarray.length(); i++) {

                    jsonobject = jsonarray.getJSONObject(i);

                    MovieData item = new MovieData();


                    item.setMoviename(jsonobject.optString("moviename"));
                    item.setMoviethumbnail(jsonobject.optString("thumbnail"));
                    item.setMovieurl(jsonobject.optString("url"));

                    //Log.v("okHTTP",jsonobject.optString("moviename"));
                    feedMovieList.add(item);

                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            catch (Exception io)
            {
                io.printStackTrace();
            }

            //Log.v("okHTTP",json);
            return json;
        }

        @Override
        protected void onPostExecute(String args)
        {

            mAdapter = new NewReleaseAdapter(getApplicationContext(), feedMovieList);

            mRecyclerView.setAdapter(mAdapter);

        }
    }
}

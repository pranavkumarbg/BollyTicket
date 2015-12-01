package com.phpnew_pranavkumar.farmerproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.phpnew_pranavkumar.farmerproject.adapter.NewReleaseAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.PopularAdapter;
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
public class PopularActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    PopularAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarpp);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Popular");
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = this.getIntent();
        ArrayList<MovieData> feedMovieList =  i.getParcelableArrayListExtra("cars");

        mRecyclerView = (RecyclerView)findViewById(R.id.listpp);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PopularAdapter(getApplicationContext(), feedMovieList);

        mRecyclerView.setAdapter(mAdapter);
    }



}

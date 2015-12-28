/*
 * Copyright (C) 2015 Pranavkumar Gorawar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phpnew_pranavkumar.farmerproject;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.phpnew_pranavkumar.farmerproject.adapter.NewReleaseAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.PopularAdapter;
import com.phpnew_pranavkumar.farmerproject.bean.MovieData;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.startapp.android.publish.StartAppAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeluguActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private StartAppAd startAppAd = new StartAppAd(this);
    Bundle appData;

    PopularAdapter mAdapter;
    ArrayList<MovieData> feedMovieList=new ArrayList<MovieData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telugu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartelg);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Telugu Movies");
        ab.setDisplayHomeAsUpEnabled(true);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        Intent i = this.getIntent();
        feedMovieList =  i.getParcelableArrayListExtra("cars");

        appData = new Bundle();
        appData.putParcelableArrayList("cars", feedMovieList);

        mRecyclerView = (RecyclerView)findViewById(R.id.listtelg);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PopularAdapter(getApplicationContext(), feedMovieList);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setAppSearchData(appData);
        searchView.setSubmitButtonEnabled(true);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {

            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    PopularAdapter.OnItemClickListener onItemClickListener=new PopularAdapter.OnItemClickListener()
    {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);


            String url=feedMovieList.get(position).movieurl;
            String image=feedMovieList.get(position).moviethumbnail;
            //Toast.makeText(getActivity(),url,Toast.LENGTH_LONG).show();
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage",image);
            startActivity(transitionIntent);

            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };

}

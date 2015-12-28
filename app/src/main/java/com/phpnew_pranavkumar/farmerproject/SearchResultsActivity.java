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
import android.widget.Toast;

import com.phpnew_pranavkumar.farmerproject.adapter.NewReleaseAdapter;
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

public class SearchResultsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private StartAppAd startAppAd = new StartAppAd(this);
    NewReleaseAdapter mAdapter;
    private ArrayList<MovieData> feedMovieList;
    private ArrayList<MovieData> feedMovieListnext = new ArrayList<MovieData>();
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        handleIntent(getIntent());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsrch);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_home);
        ab.setTitle("Search");
        ab.setDisplayHomeAsUpEnabled(true);

        // new DownloadJSON().execute();

        Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
        feedMovieList =  appData.getParcelableArrayList("cars");

        mRecyclerView = (RecyclerView)findViewById(R.id.listsrch);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        //gaggeredGridLayoutManager= new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        //Toast.makeText(this,"search activity",Toast.LENGTH_LONG).show();


        // StaggeredGridLayoutManager mLayoutManager1 = new StaggeredGridLayoutManager(2,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

       // mAdapter = new NewReleaseAdapter(getApplicationContext(), feedMovieList);

        //mRecyclerView.setAdapter(mAdapter);

        //mAdapter.setOnItemClickListener(onItemClickListener);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);

            feedMovieListnext.clear();
           // feedMovieList.clear();

            new DownloadJSON().execute();
           // Toast.makeText(this,query,Toast.LENGTH_LONG).show();


        }
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
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);


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

    private class DownloadJSON extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        public String doInBackground(String... params) {


            return null;
        }

        @Override
        protected void onPostExecute(String args) {


            for (int j = 0; j < feedMovieList.size(); j++) {
                MovieData sk = feedMovieList.get(j);
                String moviename = sk.moviename.toLowerCase();

               // String moviename =feedMovieList.get(j).moviename;

                //Toast.makeText(getApplicationContext(),moviename,Toast.LENGTH_LONG).show();
                int index1 = moviename.indexOf(query);
                // if(moviename.contains(query))
                if (index1 != -1 || moviename.contains(query))
                {

                    //Toast.makeText(getApplicationContext(),"found",Toast.LENGTH_SHORT).show();

//                    MovieData item1 = new MovieData();
//                    // ArrayList<MovieData> feedMovieListnew = new ArrayList<MovieData>();
//
//                    item1.setMoviename(feedMovieList.get(j).getMoviename());
//                    item1.setMoviethumbnail(feedMovieList.get(j).getMoviethumbnail());
//                    item1.setMovieurl(feedMovieList.get(j).getMovieurl());


                    feedMovieListnext.add(new MovieData(feedMovieList.get(j).moviethumbnail, feedMovieList.get(j).movieurl, feedMovieList.get(j).moviename));


                    // feedMovieListnext.add(item1);


                    mAdapter = new NewReleaseAdapter(getApplicationContext(), feedMovieListnext);

                    mRecyclerView.setAdapter(mAdapter);

                    if(feedMovieListnext.isEmpty())
                    {
                        Toast.makeText(getApplication(),"No Movie found",Toast.LENGTH_LONG).show();
                    }


                    mAdapter.setOnItemClickListener(onItemClickListener);


                }
            }
        }
    }



    NewReleaseAdapter.OnItemClickListener onItemClickListener=new NewReleaseAdapter.OnItemClickListener()
    {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);


            String url=feedMovieListnext.get(position).movieurl;
            String image=feedMovieListnext.get(position).moviethumbnail;
            //Toast.makeText(getActivity(),url,Toast.LENGTH_LONG).show();
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage",image);
            startActivity(transitionIntent);




        }
    };

}

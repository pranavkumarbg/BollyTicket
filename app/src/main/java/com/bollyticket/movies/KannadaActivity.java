/*
 * Copyright (C) 2016 Bolly Ticket
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
package com.bollyticket.movies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bollyticket.movies.adapter.NewKanAdapter;
import com.bollyticket.movies.bean.NewMovieData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.StartAppAd;

import java.util.ArrayList;

public class KannadaActivity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private StartAppAd startAppAd = new StartAppAd(this);
    NewKanAdapter mAdapter;
    ArrayList<NewMovieData> feedMovieList = new ArrayList<NewMovieData>();
    Bundle appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kannada);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkan);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Kannada Movies");
        ab.setDisplayHomeAsUpEnabled(true);


        mAdView = (AdView) findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequestinterstitial = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequestinterstitial);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


        Intent i = this.getIntent();
        feedMovieList = i.getParcelableArrayListExtra("cars");

        appData = new Bundle();
        appData.putParcelableArrayList("cars", feedMovieList);


        mRecyclerView = (RecyclerView) findViewById(R.id.listkan);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NewKanAdapter(getApplicationContext(), feedMovieList);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();

        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
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

        switch (id) {

            case android.R.id.home:
                startAppAd.onBackPressed();
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    NewKanAdapter.OnItemClickListener onItemClickListener = new NewKanAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivityNew.class);


            String url1 = feedMovieList.get(position).movieurl1;
            String url2 = feedMovieList.get(position).movieurl2;
            String image = feedMovieList.get(position).moviethumbnail;
            String name = feedMovieList.get(position).moviename;
            transitionIntent.putExtra("flagname", name);

            transitionIntent.putExtra("flagurl1", url1);
            transitionIntent.putExtra("flagurl2", url2);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };

    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
        }
    }
}

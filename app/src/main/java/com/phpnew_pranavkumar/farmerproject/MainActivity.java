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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.onesignal.OneSignal;
import com.phpnew_pranavkumar.farmerproject.adapter.MovieegtAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.MoviefivAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.MovieforAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.MoviesecAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.MoviesixAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.MoviesvnAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.MovietrdAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.NewRlsAdapter;
import com.phpnew_pranavkumar.farmerproject.bean.MovieData;
import com.phpnew_pranavkumar.farmerproject.bean.NewMovieData;
import com.phpnew_pranavkumar.farmerproject.fragment.HomeFragment;
import com.phpnew_pranavkumar.farmerproject.fragment.ProductDetailFragment;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Fragment contentFragment;
    HomeFragment homeFragment;

    Button b, bsec, btrd, bfor, bfiv, bsix, bsvn, begt, eng, hnd, kan, tam, tlgu, mal;
    RecyclerView mRecyclerView, mRecyclerViewsec, mRecyclerViewthr, mRecyclerViewfor, mRecyclerViewfv, mRecyclerViewsx, mRecyclerViewsvn, mRecyclerVieweght;
    RecyclerView.LayoutManager mLayoutManager, mLayoutManagersec, mLayoutManagerthr, mLayoutManagerfor, mLayoutManagerfv, mLayoutManagersx, mLayoutManagersvn, mLayoutManagereght;
    NewRlsAdapter mAdapter;
    MoviesecAdapter mAdaptersec;
    MovietrdAdapter mAdaptertrd;
    MovieforAdapter mAdapterfor;
    MoviefivAdapter moviefivAdapter;
    MoviesixAdapter moviesixAdapter;
    MoviesvnAdapter moviesvnAdapter;
    MovieegtAdapter movieegtAdapter;

    private ArrayList<MovieData> feedMovieList = new ArrayList<MovieData>();
    private ArrayList<MovieData> feedMovieListsec = new ArrayList<MovieData>();
    private ArrayList<MovieData> feedMovieListtrd = new ArrayList<MovieData>();
    private ArrayList<MovieData> feedMovieListfor = new ArrayList<MovieData>();
    private ArrayList<NewMovieData> feedMovieListfiv = new ArrayList<NewMovieData>();
    private ArrayList<MovieData> feedMovieListsix = new ArrayList<MovieData>();
    private ArrayList<MovieData> feedMovieListsvn = new ArrayList<MovieData>();
    private ArrayList<NewMovieData> feedMovieListegt = new ArrayList<NewMovieData>();

    private StartAppAd startAppAd = new StartAppAd(this);

    ProgressBar progressBar;
    ScrollView scrollView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "211895003", false);

        setContentView(R.layout.activity_main);


        OneSignal.enableNotificationsWhenActive(true);


        relativeLayout = (RelativeLayout) findViewById(R.id.mainlayout);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        b = (Button) findViewById(R.id.button);
        bsec = (Button) findViewById(R.id.buttonsec);
        btrd = (Button) findViewById(R.id.buttontrd);
        bfor = (Button) findViewById(R.id.buttonfor);
        bfiv = (Button) findViewById(R.id.buttonfv);
        bsix = (Button) findViewById(R.id.buttonsx);
        bsvn = (Button) findViewById(R.id.buttonsvn);
        begt = (Button) findViewById(R.id.buttonegt);


        eng = (Button) findViewById(R.id.Buttoneng);
        hnd = (Button) findViewById(R.id.Buttonhnd);
        kan = (Button) findViewById(R.id.Buttonkan);
        tam = (Button) findViewById(R.id.Buttontam);
        tlgu = (Button) findViewById(R.id.Buttontlgu);
        mal = (Button) findViewById(R.id.Buttonmal);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), NewReleaseActivity.class);
                i.putParcelableArrayListExtra("cars", feedMovieList);

                startActivity(i);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        bsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ipp = new Intent(getApplicationContext(), PopularActivity.class);
                ipp.putParcelableArrayListExtra("cars", feedMovieListsec);

                startActivity(ipp);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();

            }
        });

        btrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ie = new Intent(getApplicationContext(), EnglishActivity.class);
                ie.putParcelableArrayListExtra("cars", feedMovieListtrd);

                startActivity(ie);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        bfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ih = new Intent(getApplicationContext(), HindiActivity.class);
                ih.putParcelableArrayListExtra("cars", feedMovieListfor);

                startActivity(ih);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        bfiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ik = new Intent(getApplicationContext(), KannadaActivity.class);
                ik.putParcelableArrayListExtra("cars", feedMovieListfiv);

                startActivity(ik);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        bsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itlgu = new Intent(getApplicationContext(), TeluguActivity.class);
                itlgu.putParcelableArrayListExtra("cars", feedMovieListsix);

                startActivity(itlgu);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        bsvn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itml = new Intent(getApplicationContext(), TamilActivity.class);
                itml.putParcelableArrayListExtra("cars", feedMovieListsvn);

                startActivity(itml);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        begt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imal = new Intent(getApplicationContext(), MalayalamActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListegt);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });


        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent imal = new Intent(getApplicationContext(), EnglishActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListtrd);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        hnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imal = new Intent(getApplicationContext(), HindiActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListfor);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        kan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imal = new Intent(getApplicationContext(), KannadaActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListfiv);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        tam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imal = new Intent(getApplicationContext(), TamilActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListsvn);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        tlgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imal = new Intent(getApplicationContext(), TeluguActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListsix);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });

        mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imal = new Intent(getApplicationContext(), MalayalamActivity.class);
                imal.putParcelableArrayListExtra("cars", feedMovieListegt);

                startActivity(imal);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                startAppAd.showAd();
                startAppAd.loadAd();
            }
        });


        scrollView.setSmoothScrollingEnabled(true);

        DownloadJSON asynctask = new DownloadJSON();
        asynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerViewsec = (RecyclerView) findViewById(R.id.recyclerviewsec);
        mRecyclerViewsec.setHasFixedSize(true);

        mRecyclerViewthr = (RecyclerView) findViewById(R.id.recyclerviewtrd);
        mRecyclerViewfor = (RecyclerView) findViewById(R.id.recyclerviewfor);
        mRecyclerViewfv = (RecyclerView) findViewById(R.id.recyclerviewfv);
        mRecyclerViewsx = (RecyclerView) findViewById(R.id.recyclerviewsx);
        mRecyclerViewsvn = (RecyclerView) findViewById(R.id.recyclerviewsvn);
        mRecyclerVieweght = (RecyclerView) findViewById(R.id.recyclerviewegt);

        mRecyclerViewthr.setHasFixedSize(true);
        mRecyclerViewfor.setHasFixedSize(true);
        mRecyclerViewfv.setHasFixedSize(true);
        mRecyclerViewsx.setHasFixedSize(true);
        mRecyclerViewsvn.setHasFixedSize(true);
        mRecyclerVieweght.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagersec = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mLayoutManagerthr = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerfor = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerfv = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagersx = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagersvn = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagereght = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewsec.setLayoutManager(mLayoutManagersec);


        mRecyclerViewthr.setLayoutManager(mLayoutManagerthr);
        mRecyclerViewfor.setLayoutManager(mLayoutManagerfor);
        mRecyclerViewfv.setLayoutManager(mLayoutManagerfv);
        mRecyclerViewsx.setLayoutManager(mLayoutManagersx);
        mRecyclerViewsvn.setLayoutManager(mLayoutManagersvn);
        mRecyclerVieweght.setLayoutManager(mLayoutManagereght);

        mRecyclerView.smoothScrollToPosition(1000);
        mRecyclerViewsec.smoothScrollToPosition(1000);
        mRecyclerViewthr.smoothScrollToPosition(1000);
        mRecyclerViewfor.smoothScrollToPosition(1000);
        mRecyclerViewfv.smoothScrollToPosition(1000);
        mRecyclerViewsx.smoothScrollToPosition(1000);
        mRecyclerViewsvn.smoothScrollToPosition(1000);
        mRecyclerVieweght.smoothScrollToPosition(1000);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_home);
        //ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("content")) {
                String content = savedInstanceState.getString("content");
                if (content.equals(ProductDetailFragment.ARG_ITEM_ID)) {
                    if (fragmentManager
                            .findFragmentByTag(ProductDetailFragment.ARG_ITEM_ID) != null) {
                        contentFragment = fragmentManager
                                .findFragmentByTag(ProductDetailFragment.ARG_ITEM_ID);
                    }
                }
            }
            if (fragmentManager.findFragmentByTag(HomeFragment.ARG_ITEM_ID) != null) {
                homeFragment = (HomeFragment) fragmentManager
                        .findFragmentByTag(HomeFragment.ARG_ITEM_ID);
                contentFragment = homeFragment;
            }
        } else {
            homeFragment = new HomeFragment();
            switchContent(homeFragment, HomeFragment.ARG_ITEM_ID);
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
    protected void onSaveInstanceState(Bundle outState) {
        if (contentFragment instanceof HomeFragment) {
            outState.putString("content", HomeFragment.ARG_ITEM_ID);
        } else {
            outState.putString("content", ProductDetailFragment.ARG_ITEM_ID);
        }
        super.onSaveInstanceState(outState);
    }

    public void switchContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.popBackStackImmediate())
            ;

        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            transaction.replace(R.id.content_frame, fragment, tag);
            if (!(fragment instanceof HomeFragment)) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
            contentFragment = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Closing App")
                .setMessage("Are you sure you want to close this App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                })
                .setNegativeButton("No", null)
                .show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:

                Intent i = new Intent(this, AboutUs.class);
                startActivity(i);
                return true;

            case R.id.action_open:

                displayLicensesAlertDialog();

                return true;

            case R.id.action_privacy:

                displayLicensesAlertDialogPrivacy();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayLicensesAlertDialog() {
        WebView view = (WebView) LayoutInflater.from(this).inflate(R.layout.dailog_licenses, null);
        view.loadUrl("file:///android_asset/open_source_licenses.html");
        AlertDialog mAlertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle("")
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void displayLicensesAlertDialogPrivacy() {
        WebView view = (WebView) LayoutInflater.from(this).inflate(R.layout.dailog_licenses, null);
        view.loadUrl("file:///android_asset/privacy_licenses.html");
        AlertDialog mAlertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle("")
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private class DownloadJSON extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        public String doInBackground(String... params) {


            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/newrelease").build();
            Request requestsec = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/popularmoviejson").build();
            Request requesttrd = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/english").build();
            Request requestfor = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/hindi").build();
            Request requestfiv = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/kannadanew").build();
            Request requestsix = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/telugu").build();
            Request requestsvn = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/tamil").build();
            Request requestegt = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/malayalamnew").build();


            Call call = okHttpClient.newCall(request);
            Call callsec = okHttpClient.newCall(requestsec);
            Call calltrd = okHttpClient.newCall(requesttrd);
            Call callfor = okHttpClient.newCall(requestfor);
            Call callfiv = okHttpClient.newCall(requestfiv);
            Call callsix = okHttpClient.newCall(requestsix);
            Call callsvn = okHttpClient.newCall(requestsvn);
            Call callegt = okHttpClient.newCall(requestegt);


            try {

                Response response = call.execute();
                Response responsesec = callsec.execute();
                Response responsetrd = calltrd.execute();
                Response responsefor = callfor.execute();
                Response responsefiv = callfiv.execute();
                Response responsesix = callsix.execute();
                Response responsesvn = callsvn.execute();
                Response responseegt = callegt.execute();


                String json = response.body().string();
                String jsonsec = responsesec.body().string();
                String jsontrd = responsetrd.body().string();
                String jsonfor = responsefor.body().string();
                String jsonfiv = responsefiv.body().string();
                String jsonsix = responsesix.body().string();
                String jsonsvn = responsesvn.body().string();
                String jsonegt = responseegt.body().string();


                JSONObject reader = new JSONObject(json);
                JSONObject readersec = new JSONObject(jsonsec);
                JSONObject readertrd = new JSONObject(jsontrd);
                JSONObject readerfor = new JSONObject(jsonfor);
                JSONObject readerfiv = new JSONObject(jsonfiv);
                JSONObject readersix = new JSONObject(jsonsix);
                JSONObject readersvn = new JSONObject(jsonsvn);
                JSONObject readeregt = new JSONObject(jsonegt);


                JSONArray jsonarray = reader.getJSONArray("movies");
                JSONArray jsonarraysec = readersec.getJSONArray("movies");
                JSONArray jsonarraytrd = readertrd.getJSONArray("movies");
                JSONArray jsonarrayfor = readerfor.getJSONArray("movies");
                JSONArray jsonarrayfiv = readerfiv.getJSONArray("movies");
                JSONArray jsonarraysix = readersix.getJSONArray("movies");
                JSONArray jsonarraysvn = readersvn.getJSONArray("movies");
                JSONArray jsonarrayegt = readeregt.getJSONArray("movies");


                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);


                    feedMovieList.add(new MovieData(jsonobject.optString("thumbnail"), jsonobject.optString("url"), jsonobject.optString("moviename")));

                }

                for (int i = 0; i < jsonarraysec.length(); i++) {

                    JSONObject jsonobjectsec = jsonarraysec.getJSONObject(i);


                    feedMovieListsec.add(new MovieData(jsonobjectsec.optString("thumbnail"), jsonobjectsec.optString("url"), jsonobjectsec.optString("moviename")));

                }

                for (int i = 0; i < jsonarraytrd.length(); i++) {

                    JSONObject jsonobjecttrd = jsonarraytrd.getJSONObject(i);


                    feedMovieListtrd.add(new MovieData(jsonobjecttrd.optString("thumbnail"), jsonobjecttrd.optString("url"), jsonobjecttrd.optString("moviename")));

                }

                for (int i = 0; i < jsonarrayfor.length(); i++) {

                    JSONObject jsonobjectfor = jsonarrayfor.getJSONObject(i);


                    feedMovieListfor.add(new MovieData(jsonobjectfor.optString("thumbnail"), jsonobjectfor.optString("url"), jsonobjectfor.optString("moviename")));

                }

                for (int i = 0; i < jsonarrayfiv.length(); i++) {

                    JSONObject jsonobjectfiv = jsonarrayfiv.getJSONObject(i);


                    feedMovieListfiv.add(new NewMovieData(jsonobjectfiv.optString("thumbnail"), jsonobjectfiv.optString("url1"), jsonobjectfiv.optString("url2"), jsonobjectfiv.optString("moviename")));

                }

                for (int i = 0; i < jsonarraysix.length(); i++) {

                    JSONObject jsonobjectsix = jsonarraysix.getJSONObject(i);


                    feedMovieListsix.add(new MovieData(jsonobjectsix.optString("thumbnail"), jsonobjectsix.optString("url"), jsonobjectsix.optString("moviename")));

                }


                for (int i = 0; i < jsonarraysvn.length(); i++) {

                    JSONObject jsonobjectsvn = jsonarraysvn.getJSONObject(i);


                    feedMovieListsvn.add(new MovieData(jsonobjectsvn.optString("thumbnail"), jsonobjectsvn.optString("url"), jsonobjectsvn.optString("moviename")));

                }


                for (int i = 0; i < jsonarrayegt.length(); i++) {

                    JSONObject jsonobjectegt = jsonarrayegt.getJSONObject(i);


                    feedMovieListegt.add(new NewMovieData(jsonobjectegt.optString("thumbnail"), jsonobjectegt.optString("url1"), jsonobjectegt.optString("url2"), jsonobjectegt.optString("moviename")));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception io) {
                io.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String args) {


            mAdapter = new NewRlsAdapter(getApplicationContext(), feedMovieList);
            mAdaptersec = new MoviesecAdapter(getApplicationContext(), feedMovieListsec);
            mAdaptertrd = new MovietrdAdapter(getApplicationContext(), feedMovieListtrd);
            mAdapterfor = new MovieforAdapter(getApplicationContext(), feedMovieListfor);
            moviefivAdapter = new MoviefivAdapter(getApplicationContext(), feedMovieListfiv);
            moviesixAdapter = new MoviesixAdapter(getApplicationContext(), feedMovieListsix);
            moviesvnAdapter = new MoviesvnAdapter(getApplicationContext(), feedMovieListsvn);
            movieegtAdapter = new MovieegtAdapter(getApplicationContext(), feedMovieListegt);


            mRecyclerView.setAdapter(mAdapter);
            mRecyclerViewsec.setAdapter(mAdaptersec);

            mRecyclerViewthr.setAdapter(mAdaptertrd);
            mRecyclerViewfor.setAdapter(mAdapterfor);
            mRecyclerViewfv.setAdapter(moviefivAdapter);
            mRecyclerViewsx.setAdapter(moviesixAdapter);
            mRecyclerViewsvn.setAdapter(moviesvnAdapter);
            mRecyclerVieweght.setAdapter(movieegtAdapter);

            mAdapter.setOnItemClickListener(onItemClickListener);
            mAdaptersec.setOnItemClickListener(onItemClickListenersec);
            mAdaptertrd.setOnItemClickListener(onItemClickListenertrd);
            mAdapterfor.setOnItemClickListener(onItemClickListenerfor);

            moviefivAdapter.setOnItemClickListener(onItemClickListenerfiv);
            moviesixAdapter.setOnItemClickListener(onItemClickListenersix);
            moviesvnAdapter.setOnItemClickListener(onItemClickListenersvn);
            movieegtAdapter.setOnItemClickListener(onItemClickListeneregt);

            progressBar.setVisibility(View.GONE);


        }
    }


    NewRlsAdapter.OnItemClickListener onItemClickListener = new NewRlsAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);


            String url = feedMovieList.get(position).movieurl;
            String image = feedMovieList.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };

    MoviesecAdapter.OnItemClickListener onItemClickListenersec = new MoviesecAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);

            String url = feedMovieListsec.get(position).movieurl;
            String image = feedMovieListsec.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };

    MovietrdAdapter.OnItemClickListener onItemClickListenertrd = new MovietrdAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);

            String url = feedMovieListtrd.get(position).movieurl;
            String image = feedMovieListtrd.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };

    MovieforAdapter.OnItemClickListener onItemClickListenerfor = new MovieforAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);

            String url = feedMovieListfor.get(position).movieurl;
            String image = feedMovieListfor.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };


    MoviefivAdapter.OnItemClickListener onItemClickListenerfiv = new MoviefivAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivityNew.class);

            String url1 = feedMovieListfiv.get(position).movieurl1;
            String url2 = feedMovieListfiv.get(position).movieurl2;
            String image = feedMovieListfiv.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl1", url1);
            transitionIntent.putExtra("flagurl2", url2);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };


    MoviesixAdapter.OnItemClickListener onItemClickListenersix = new MoviesixAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);

            String url = feedMovieListsix.get(position).movieurl;
            String image = feedMovieListsix.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };


    MoviesvnAdapter.OnItemClickListener onItemClickListenersvn = new MoviesvnAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);

            String url = feedMovieListsvn.get(position).movieurl;
            String image = feedMovieListsvn.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };


    MovieegtAdapter.OnItemClickListener onItemClickListeneregt = new MovieegtAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivityNew.class);

            String url1 = feedMovieListegt.get(position).movieurl1;
            String url2 = feedMovieListegt.get(position).movieurl2;
            String image = feedMovieListegt.get(position).moviethumbnail;
            transitionIntent.putExtra("flagurl1", url1);
            transitionIntent.putExtra("flagurl2", url2);
            transitionIntent.putExtra("flagimage", image);
            startActivity(transitionIntent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


        }
    };
}
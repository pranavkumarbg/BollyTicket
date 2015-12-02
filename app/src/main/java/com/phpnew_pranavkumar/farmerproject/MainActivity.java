package com.phpnew_pranavkumar.farmerproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.phpnew_pranavkumar.farmerproject.adapter.MoviesecAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.NewReleaseAdapter;
import com.phpnew_pranavkumar.farmerproject.adapter.NewRlsAdapter;
import com.phpnew_pranavkumar.farmerproject.bean.MovieData;
import com.phpnew_pranavkumar.farmerproject.fragment.HomeFragment;
import com.phpnew_pranavkumar.farmerproject.fragment.ProductDetailFragment;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Fragment contentFragment;
    HomeFragment homeFragment;

    Button b,bsec;
    RecyclerView mRecyclerView,mRecyclerViewsec,mRecyclerViewthr,mRecyclerViewfor,mRecyclerViewfv,mRecyclerViewsx,mRecyclerViewsvn,mRecyclerVieweght;
    RecyclerView.LayoutManager mLayoutManager,mLayoutManagersec,mLayoutManagerthr,mLayoutManagerfor,mLayoutManagerfv,mLayoutManagersx,mLayoutManagersvn,mLayoutManagereght;
    NewRlsAdapter mAdapter;
    MoviesecAdapter mAdaptersec;

    private ArrayList<MovieData> feedMovieList = new ArrayList<MovieData>();
    private ArrayList<MovieData> feedMovieListsec = new ArrayList<MovieData>();
    ProgressBar progressBar;
    ScrollView scrollView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout=(RelativeLayout)findViewById(R.id.mainlayout);

        relativeLayout.getBackground().setAlpha(51);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        //progressBar.setVisibility(View.GONE);

        b=(Button)findViewById(R.id.button);
        bsec=(Button)findViewById(R.id.buttonsec);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), NewReleaseActivity.class);
                i.putParcelableArrayListExtra("cars", feedMovieList);

                startActivity(i);
            }
        });

        bsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ipp = new Intent(getApplicationContext(), PopularActivity.class);
                ipp.putParcelableArrayListExtra("cars", feedMovieListsec);

                startActivity(ipp);

            }
        });
        //new DownloadJSON().execute();

        scrollView.setSmoothScrollingEnabled(true);

        DownloadJSON asynctask=new DownloadJSON();
        asynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerViewsec = (RecyclerView)findViewById(R.id.recyclerviewsec);
        mRecyclerViewsec.setHasFixedSize(true);

        mRecyclerViewthr = (RecyclerView)findViewById(R.id.recyclerviewtrd);
        mRecyclerViewfor = (RecyclerView)findViewById(R.id.recyclerviewfor);
        mRecyclerViewfv = (RecyclerView)findViewById(R.id.recyclerviewfv);
        mRecyclerViewsx = (RecyclerView)findViewById(R.id.recyclerviewsx);
        mRecyclerViewsvn = (RecyclerView)findViewById(R.id.recyclerviewsvn);
        mRecyclerVieweght = (RecyclerView)findViewById(R.id.recyclerviewegt);

        mRecyclerViewthr.setHasFixedSize(true);
        mRecyclerViewfor.setHasFixedSize(true);
        mRecyclerViewfv.setHasFixedSize(true);
        mRecyclerViewsx.setHasFixedSize(true);
        mRecyclerViewsvn.setHasFixedSize(true);
        mRecyclerVieweght.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagersec = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);

        mLayoutManagerthr = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerfor = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerfv = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagersx = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagersvn = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagereght = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);




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
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_home);
        ab.setDisplayHomeAsUpEnabled(true);

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
            // Only ProductDetailFragment is added to the back stack.
            if (!(fragment instanceof HomeFragment)) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
            contentFragment = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else if (contentFragment instanceof HomeFragment
                || fm.getBackStackEntryCount() == 0) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                //Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();

//                Intent i=new Intent(this,ViewPagerActivity.class);
//                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
            Request request = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/newmoviejson").build();
            Request requestsec = new Request.Builder().url("http://moviejson-pranavkumar.rhcloud.com/popularmoviejson").build();

            Call call=okHttpClient.newCall(request);
            Call callsec=okHttpClient.newCall(requestsec);


            try {

                Response response=call.execute();
                Response responsesec=callsec.execute();

                String json = response.body().string();
                String jsonsec = responsesec.body().string();



                JSONObject reader = new JSONObject(json);
                JSONObject readersec = new JSONObject(jsonsec);

                JSONArray jsonarray = reader.getJSONArray("movies");
                JSONArray jsonarraysec = readersec.getJSONArray("movies");


                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);


                    feedMovieList.add(new MovieData(jsonobject.optString("thumbnail"),jsonobject.optString("url"),jsonobject.optString("moviename")));

                }

                for (int i = 0; i < jsonarraysec.length(); i++) {

                    JSONObject jsonobjectsec = jsonarraysec.getJSONObject(i);


                    feedMovieListsec.add(new MovieData(jsonobjectsec.optString("thumbnail"),jsonobjectsec.optString("url"),jsonobjectsec.optString("moviename")));

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
            return null;
        }



        @Override
        protected void onPostExecute(String args) {



            mAdapter = new NewRlsAdapter(getApplicationContext(), feedMovieList);
            mAdaptersec = new MoviesecAdapter(getApplicationContext(), feedMovieListsec);


            mRecyclerView.setAdapter(mAdapter);
            mRecyclerViewsec.setAdapter(mAdaptersec);

//            mRecyclerViewthr.setAdapter(mAdapter);
//            mRecyclerViewfor.setAdapter(mAdapter);
//            mRecyclerViewfv.setAdapter(mAdapter);
//            mRecyclerViewsx.setAdapter(mAdapter);
//            mRecyclerViewsvn.setAdapter(mAdapter);
//            mRecyclerVieweght.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(onItemClickListener);
            mAdaptersec.setOnItemClickListener(onItemClickListenersec);


            progressBar.setVisibility(View.GONE);

        }
    }


    NewRlsAdapter.OnItemClickListener onItemClickListener=new NewRlsAdapter.OnItemClickListener()
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



        }
    };

    MoviesecAdapter.OnItemClickListener onItemClickListenersec=new MoviesecAdapter.OnItemClickListener()
    {

        @Override
        public void onItemClick(View view, int position) {


            Intent transitionIntent = new Intent(getApplicationContext(), MovieFullActivity.class);


            String url=feedMovieListsec.get(position).movieurl;
            String image=feedMovieListsec.get(position).moviethumbnail;
            //Toast.makeText(getActivity(),url,Toast.LENGTH_LONG).show();
            transitionIntent.putExtra("flagurl", url);
            transitionIntent.putExtra("flagimage",image);
            startActivity(transitionIntent);



        }
    };

}
package com.phpnew_pranavkumar.farmerproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.phpnew_pranavkumar.farmerproject.adapter.MyCustomLayoutManager;
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
    ProductDetailFragment pdtDetailFragment;

    Button b;
    RecyclerView mRecyclerView,mRecyclerViewsec,mRecyclerViewthr,mRecyclerViewfor,mRecyclerViewfv,mRecyclerViewsx,mRecyclerViewsvn,mRecyclerVieweght;
    RecyclerView.LayoutManager mLayoutManager,mLayoutManagersec,mLayoutManagerthr,mLayoutManagerfor,mLayoutManagerfv,mLayoutManagersx,mLayoutManagersvn,mLayoutManagereght;
    NewRlsAdapter mAdapter;
    MyCustomLayoutManager myCustomLayoutManager;
    JSONObject jsonobject;
    JSONArray jsonarray;
    String json;
    private ArrayList<MovieData> feedMovieList = new ArrayList<MovieData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=(Button)findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplication(),NewReleaseActivity.class);
                startActivity(i);
            }
        });

        new DownloadJSON().execute();


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


       // myCustomLayoutManager = new MyCustomLayoutManager(getApplication());
        //mRecyclerView.setLayoutManager(myCustomLayoutManager);
        //mRecyclerView.smoothScrollToPosition(100);

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

                Intent i=new Intent(this,ViewPagerActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class DownloadJSON extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

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
        protected void onPostExecute(String args) {



            mAdapter = new NewRlsAdapter(getApplicationContext(), feedMovieList);

            mRecyclerView.setAdapter(mAdapter);
            mRecyclerViewsec.setAdapter(mAdapter);

            mRecyclerViewthr.setAdapter(mAdapter);
            mRecyclerViewfor.setAdapter(mAdapter);
            mRecyclerViewfv.setAdapter(mAdapter);
            mRecyclerViewsx.setAdapter(mAdapter);
            mRecyclerViewsvn.setAdapter(mAdapter);
            mRecyclerVieweght.setAdapter(mAdapter);

           // mAdapter.setOnItemClickListener(onItemClickListener);



        }
    }
}
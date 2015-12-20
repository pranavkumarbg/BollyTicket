package com.phpnew_pranavkumar.farmerproject;

/**
 * Created by kehooo on 12/4/2015.
 */
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
import android.view.View;

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
public class KannadaActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    PopularAdapter mAdapter;
    ArrayList<MovieData> feedMovieList=new ArrayList<MovieData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kannada);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkan);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Kannada Movies");
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = this.getIntent();
        feedMovieList =  i.getParcelableArrayListExtra("cars");

        mRecyclerView = (RecyclerView)findViewById(R.id.listkan);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PopularAdapter(getApplicationContext(), feedMovieList);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
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



        }
    };

}

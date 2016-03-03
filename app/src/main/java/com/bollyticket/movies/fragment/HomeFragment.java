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
package com.bollyticket.movies.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bollyticket.movies.R;
import com.bollyticket.movies.adapter.ImageSlideAdapter;
import com.bollyticket.movies.bean.Product;
import com.bollyticket.movies.json.GetJSONObject;
import com.bollyticket.movies.json.JsonReader;
import com.bollyticket.movies.utils.CheckNetworkConnection;
import com.bollyticket.movies.utils.CirclePageIndicator;
import com.bollyticket.movies.utils.PageIndicator;
import com.bollyticket.movies.utils.TagName;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String ARG_ITEM_ID = "home_fragment";

    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;

    private ViewPager mViewPager;
    TextView imgNameTxt;
    PageIndicator mIndicator;

    AlertDialog alertDialog;

    List<Product> products;
    RequestImgTask task;
    boolean stopSliding = false;
    String message;

    private Runnable animateViewPager;
    private Handler handler;

    String url = "http://phpnew-pranavkumar.rhcloud.com/products.json";
    FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViewById(view);


        mIndicator.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        if (products != null && products.size() != 0) {
                            stopSliding = false;
                            runnable(products.size());
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY_USER_VIEW);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (handler != null && stopSliding == false) {
                            stopSliding = true;
                            handler.removeCallbacks(animateViewPager);
                        }
                        break;
                }
                return false;
            }
        });

        return view;
    }

    private void findViewById(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
    }

    public void runnable(final int size) {
        handler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (mViewPager.getCurrentItem() == size - 1) {
                        mViewPager.setCurrentItem(0);
                    } else {
                        mViewPager.setCurrentItem(
                                mViewPager.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
                }
            }
        };
    }


    @Override
    public void onResume() {
        if (products == null) {
            sendRequest();
        } else {
            mViewPager.setAdapter(new ImageSlideAdapter(activity, products,
                    HomeFragment.this));

            mIndicator.setViewPager(mViewPager);
            runnable(products.size());
            handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
        }
        super.onResume();
    }


    @Override
    public void onPause() {
        if (task != null)
            task.cancel(true);
        if (handler != null) {
            handler.removeCallbacks(animateViewPager);
        }
        super.onPause();
    }

    private void sendRequest() {
        if (CheckNetworkConnection.isConnectionAvailable(activity)) {
            task = new RequestImgTask(activity);
            task.execute(url);
        } else {
            message = getResources().getString(R.string.no_internet_connection);
            showAlertDialog(message, true);
        }
    }

    public void showAlertDialog(String message, final boolean finish) {
        alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (finish)
                            activity.finish();
                    }
                });
        alertDialog.show();
    }

    private class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (products != null) {

                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private class RequestImgTask extends AsyncTask<String, Void, List<Product>> {
        private final WeakReference<Activity> activityWeakRef;
        Throwable error;

        public RequestImgTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Product> doInBackground(String... urls) {
            try {
                JSONObject jsonObject = getJsonObject(urls[0]);
                if (jsonObject != null) {
                    boolean status = jsonObject.getBoolean(TagName.TAG_STATUS);

                    if (status) {
                        JSONObject jsonData = jsonObject
                                .getJSONObject(TagName.TAG_DATA);
                        if (jsonData != null) {
                            products = JsonReader.getHome(jsonData);

                        } else {
                            message = jsonObject.getString(TagName.TAG_DATA);
                        }
                    } else {
                        message = jsonObject.getString(TagName.TAG_DATA);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return products;
        }


        public JSONObject getJsonObject(String url) {
            JSONObject jsonObject = null;
            try {
                jsonObject = GetJSONObject.getJSONObject(url);
            } catch (Exception e) {
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(List<Product> result) {
            super.onPostExecute(result);

            if (activityWeakRef != null && !activityWeakRef.get().isFinishing()) {
                if (error != null && error instanceof IOException) {
                    message = getResources().getString(R.string.time_out);
                    showAlertDialog(message, true);
                } else if (error != null) {
                    message = getResources().getString(R.string.error_occured);
                    showAlertDialog(message, true);
                } else {
                    products = result;
                    if (result != null) {
                        if (products != null && products.size() != 0) {

                            mViewPager.setAdapter(new ImageSlideAdapter(
                                    activity, products, HomeFragment.this));

                            mIndicator.setViewPager(mViewPager);

                            runnable(products.size());
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY);
                        } else {
                        }
                    } else {
                    }
                }
            }
        }
    }
}

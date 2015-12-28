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
package com.phpnew_pranavkumar.farmerproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pranav on 9/2/2015.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.phpnew_pranavkumar.farmerproject.R;
import com.phpnew_pranavkumar.farmerproject.bean.MovieData;
import com.phpnew_pranavkumar.farmerproject.bean.NewMovieData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder>{

    private List<NewMovieData> feedMovieList = new ArrayList<NewMovieData>();
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public NewAdapter(Context applicationContext, ArrayList<NewMovieData> feedMovieList) {
        this.feedMovieList = feedMovieList;
        this.mContext = applicationContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.nwrlsitem, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        try
        {

            String url = feedMovieList.get(position).moviethumbnail;

            String name=feedMovieList.get(position).moviename;

            holder.placeName.setText(name);
            holder.placeName.setTypeface(null, Typeface.NORMAL);
            // String url="http://www.indiancinemagallery.com/gallery/kajal-agarwal/Kajal-Agarwal-January-2014-pics-(7)2077.jpg";
//            Log.d("inside", url);
//            Glide.with(mContext).load(url)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(holder.placeImage);

//            Picasso.with(mContext)
//                    .load(url)
//
//                    .transform(PaletteTransformation.instance())
//                    .resize(200,200)
//
//                    .into(holder.placeImage, new Callback.EmptyCallback() {
//                        @Override public void onSuccess() {
//                            Bitmap bitmap = ((BitmapDrawable) holder.placeImage.getDrawable()).getBitmap(); // Ew!
//                            Palette palette = PaletteTransformation.getPalette(bitmap);
//                            // TODO apply palette to text views, backgrounds, etc.
//
//
//                            int mutedLight = palette.getVibrantColor(mContext.getResources().getColor(android.R.color.black));
//                            int newcolr=mContext.getResources().getColor(R.color.monsoon);
//                            holder.placeNameHolder.setBackgroundColor(mutedLight);
//
//                        }
//                    });

            Glide.with(mContext)
                    .load(url)
                    .asBitmap()
                    .placeholder(R.drawable.video_placeholder)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(holder.placeImage) {

                        @Override
                        public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation) {
                            super.onResourceReady(resource, glideAnimation);


                            Palette.generateAsync(resource, new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {

                                    int mutedLight = palette.getVibrantColor(mContext.getResources().getColor(android.R.color.black));
                                    holder.placeNameHolder.setBackgroundColor(mutedLight);



                                }
                            });

                        }
                    });



        }
        catch (Exception e)
        {

        }


    }


    @Override
    public int getItemCount()
    {
        return feedMovieList.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;

        public ViewHolder(View itemView) {
            super(itemView);


            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.item_img);
            placeImage.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }




}


package com.phpnew_pranavkumar.farmerproject.adapter;

/**
 * Created by kehooo on 11/19/2015.
 */

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
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.phpnew_pranavkumar.farmerproject.R;
import com.phpnew_pranavkumar.farmerproject.bean.MovieData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranav on 8/14/2015.
 */
public class MoviesvnAdapter extends RecyclerView.Adapter<MoviesvnAdapter.ViewHolder>{

    private List<MovieData> feedMovieList = new ArrayList<MovieData>();
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public MoviesvnAdapter(Context applicationContext, ArrayList<MovieData> feedMovieList) {
        this.feedMovieList = feedMovieList;
        this.mContext = applicationContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.newreleasecard, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        try
        {
            Log.d("inside", "onbinder");
            String url = feedMovieList.get(position).moviethumbnail;

            String name=feedMovieList.get(position).moviename;

            holder.placeName.setText(name);
            //holder.placeName.setTextSize(10);
            //holder.placeName.setTypeface(null, Typeface.BOLD);

//            String fontPath = "fonts/Face Your Fears.ttf";
//
//                       // Loading Font Face
//            Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
//
//            // Applying font
//            txtGhost.setTypeface(tf);

//            Glide.with(mContext)
//                    .load(url)
//                    .asBitmap()
//                    .placeholder(R.drawable.video_placeholder)
//
//                    .into(new BitmapImageViewTarget(holder.placeImage) {
//
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
//                            super.onResourceReady(resource, glideAnimation);
//
//
//                            Palette.generateAsync(resource, new Palette.PaletteAsyncListener() {
//                                @Override
//                                public void onGenerated(Palette palette) {
//
//                                    int mutedLight = palette.getVibrantColor(mContext.getResources().getColor(android.R.color.black));
//                                    holder.placeNameHolder.setBackgroundColor(mutedLight);
//                                }
//                            });
//
//                        }
//                    });



            Picasso.with(mContext)
                    .load(url)
                    .transform(PaletteTransformation.instance())
                    .resize(200,200)

                    .into(holder.placeImage, new Callback.EmptyCallback() {
                        @Override public void onSuccess() {
                            Bitmap bitmap = ((BitmapDrawable) holder.placeImage.getDrawable()).getBitmap(); // Ew!
                            Palette palette = PaletteTransformation.getPalette(bitmap);
                            // TODO apply palette to text views, backgrounds, etc.


                            int mutedLight = palette.getVibrantColor(mContext.getResources().getColor(android.R.color.black));
                            int newcolr=mContext.getResources().getColor(R.color.monsoon);
                            holder.placeNameHolder.setBackgroundColor(mutedLight);

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
        //return feedMovieList.size();
        return 10;
    }





    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;

        public ViewHolder(View itemView) {
            super(itemView);


            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
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


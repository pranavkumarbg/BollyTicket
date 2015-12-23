package com.phpnew_pranavkumar.farmerproject;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.phpnew_pranavkumar.farmerproject.services.MovieDownloadService;
import com.phpnew_pranavkumar.farmerproject.utils.BlurTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.startapp.android.publish.StartAppAd;

import java.util.concurrent.TimeUnit;


/**
 * Created by kehooo on 12/1/2015.
 */
public class MovieFullActivity extends AppCompatActivity implements Target {

    RelativeLayout relativeLayout;
    ImageView imageView,imageViewlol;
    private StartAppAd startAppAd = new StartAppAd(this);

    private static final int BACKGROUND_IMAGES_WIDTH = 360;
    private static final int BACKGROUND_IMAGES_HEIGHT = 360;
    private static final float BLUR_RADIUS = 25F;
    private final Handler handler = new Handler();
    AlertDialog levelDialog;
    final CharSequence[] items = {" Internal Player "," External Player "};
    private BlurTransformation blurTransformation;
    private int backgroundIndex;
    private Point backgroundImageTargetSize;
    String image;
    String flag;
    Button watch,downlaod;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviefullview);

        progressBar = (ProgressBar) findViewById(R.id.progressbarfull);
        blurTransformation = new BlurTransformation(this, BLUR_RADIUS);
        backgroundImageTargetSize = calculateBackgroundImageSizeCroppedToScreenAspectRatio(getWindowManager().getDefaultDisplay());

//        progressDialog = new ProgressDialog(MovieFullActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Loading..........");
//        progressDialog.setTitle("Movie");

        updateWindowBackground();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfull);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Movie");
        //ab.setDisplayHomeAsUpEnabled(true);
        ScrollView scrollView=(ScrollView) findViewById(R.id.scrollView);

        scrollView.setSmoothScrollingEnabled(true);
        imageView=(ImageView)findViewById(R.id.imageViewfull);
        Intent i = getIntent();

        flag= i.getStringExtra("flagurl");
        image=i.getStringExtra("flagimage");

        watch=(Button)findViewById(R.id.Button04);
        downlaod=(Button)findViewById(R.id.Button05);


        Glide.with(this)
                .load(image)
                .asBitmap()
                .placeholder(R.drawable.video_placeholder)
                .thumbnail(0.5f)
                .into(new BitmapImageViewTarget(imageView)
                {

                    @Override
                    public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation)
                    {
                        super.onResourceReady(resource, glideAnimation);

                        //progressDialog.dismiss();

                        //progressBar.setVisibility(View.GONE);

                    }
                });


        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MovieFullActivity.this,R.style.MyDialogTheme);
                builder.setTitle("Select The Player");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {


                        switch (item) {
                            case 0:
                                // Your code when first option seletced
                                // Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_LONG).show();
                                Samples.Sample sample=new Samples.Sample("Android screens (Matroska)", flag, PlayerActivity.TYPE_OTHER);

                                Intent mpdIntent = new Intent(MovieFullActivity.this, PlayerActivity.class)

                                        .setData(Uri.parse(sample.uri))
                                        .putExtra(PlayerActivity.CONTENT_ID_EXTRA, sample.contentId)
                                        .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, sample.type);
                                startActivity(mpdIntent);
                                break;
                            case 1:
                                // Your code when 2nd  option seletced
                                //Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();

                                Uri intentUri = Uri.parse(flag);

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setDataAndType(intentUri, "video/*");
                                startActivity(intent);


                                break;


                        }
                        levelDialog.dismiss();
                    }
                });
                levelDialog = builder.create();
                levelDialog.show();
            }
        });

        downlaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MovieDownloadService.class);
                intent.putExtra("movie",flag);
                startService(intent);
                Toast.makeText(getApplicationContext(), "Download started", Toast.LENGTH_LONG).show();

//                Intent i = new Intent();
//                i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
//                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 1, i, 0);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        startAppAd.onPause();

        handler.removeCallbacksAndMessages(null);


    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }



    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBitmapFailed(Drawable drawable) {
        getWindow().setBackgroundDrawable(drawable);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        changeBackground(new BitmapDrawable(getResources(), bitmap));
        progressBar.setVisibility(View.GONE);
       // progressDialog.dismiss();
        startAppAd.showAd(); // show the ad
        startAppAd.loadAd(); // load the next ad

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        // just prepare mentally
    }

    private void updateWindowBackground() {

        progressBar.setVisibility(View.VISIBLE);


        //progressDialog.show();

        Picasso.with(this).load(image)
                .resize(backgroundImageTargetSize.x, backgroundImageTargetSize.y).centerCrop()
                .transform(blurTransformation).error(R.drawable.video_placeholder).into((Target) this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateWindowBackground();
            }
        }, TimeUnit.SECONDS.toMillis(1));
    }



    private void changeBackground(Drawable drawable) {
        View decorView = getWindow().getDecorView();
        Drawable oldBackgroundDrawable = decorView.getBackground();
        TransitionDrawable transitionDrawable = new TransitionDrawable(
                new Drawable[]{oldBackgroundDrawable, drawable});
        setBackgroundCompat(decorView, transitionDrawable);
        transitionDrawable.startTransition(1000);
    }

    private static void setBackgroundCompat(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            //noinspection deprecation
            view.setBackgroundDrawable(drawable);
        }
    }

    private static Point calculateBackgroundImageSizeCroppedToScreenAspectRatio(Display display) {
        final Point screenSize = new Point();
        getSizeCompat(display, screenSize);
        int scaledWidth = (int) (((double) BACKGROUND_IMAGES_HEIGHT * screenSize.x) / screenSize.y);
        int croppedWidth = Math.min(scaledWidth, BACKGROUND_IMAGES_WIDTH);
        int scaledHeight = (int) (((double) BACKGROUND_IMAGES_WIDTH * screenSize.y) / screenSize.x);
        int croppedHeight = Math.min(scaledHeight, BACKGROUND_IMAGES_HEIGHT);
        return new Point(croppedWidth, croppedHeight);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private static void getSizeCompat(Display display, Point screenSize) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(screenSize);
        } else {
            screenSize.x = display.getWidth();
            screenSize.y = display.getHeight();
        }
    }


}

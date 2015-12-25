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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
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
public class MovieFullActivityNew extends AppCompatActivity implements Target {

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
    String flag1,flag2;
    Button watch,downlaod,send;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    String sendbar;
    RatingBar ratingBar;

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
        ab.setDisplayHomeAsUpEnabled(true);
        ScrollView scrollView=(ScrollView) findViewById(R.id.scrollView);

        scrollView.setSmoothScrollingEnabled(true);
        imageView=(ImageView)findViewById(R.id.imageViewfull);
        Intent i = getIntent();

        flag1= i.getStringExtra("flagurl1");
        flag2= i.getStringExtra("flagurl2");

        //Toast.makeText(getApplicationContext(), flag1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), flag2, Toast.LENGTH_SHORT).show();

        image=i.getStringExtra("flagimage");

        watch=(Button)findViewById(R.id.Button04);
        downlaod=(Button)findViewById(R.id.Button05);
        ratingBar=(RatingBar)findViewById(R.id.rating);
        send=(Button)findViewById(R.id.buttonsend);

        Glide.with(this)
                .load(image)
                .asBitmap()
                .placeholder(R.drawable.video_placeholder)
                .thumbnail(0.5f)
                .into(new BitmapImageViewTarget(imageView) {

                    @Override
                    public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);

                        //progressDialog.dismiss();

                        //progressBar.setVisibility(View.GONE);


                    }
                });


        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MovieFullActivityNew.this, R.style.MyDialogTheme);
                //builder.setTitle("Movie");
                //builder.setMessage("Select part1 or part2");
//                builder.setPositiveButton("play", null);
//                builder.setNegativeButton("Cancel", null);
//                builder.show();

                builder.setTitle("Choose One")

                        // specify the list array, the items to be selected by default (null for none),
                        // and the listener through which to receive call backs when items are selected
                        // again, R.array.choices were set in the resources res/values/strings.xml
                        .setSingleChoiceItems(R.array.choices, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {


                                //Toast.makeText(getApplicationContext(), "Some actions maybe? Selected index: " + arg1, Toast.LENGTH_LONG).show();
                            }

                        })

                                // Set the action buttons
                        .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // user clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog

                                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                if(selectedPosition==0)
                                {
                                    //Toast.makeText(getApplicationContext(), flag1, Toast.LENGTH_LONG).show();

                                    //playmovie(flag1);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MovieFullActivityNew.this, R.style.MyDialogTheme);

                                    builder.setTitle("Choose One")

                                            .setSingleChoiceItems(R.array.choicesnew, 0, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {


                                                }

                                            })

                                                    // Set the action buttons
                                            .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // user clicked OK, so save the mSelectedItems results somewhere
                                                    // or return them to the component that opened the dialog

                                                    int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                                    if(selectedPosition==0)
                                                    {
                                                        //Toast.makeText(getApplicationContext(), "selected: internal", Toast.LENGTH_LONG).show();

                                                        Samples.Sample sample=new Samples.Sample("Android screens (Matroska)", flag1, PlayerActivity.TYPE_OTHER);

                                                        Intent mpdIntent = new Intent(MovieFullActivityNew.this, PlayerActivity.class)

                                                                .setData(Uri.parse(sample.uri))
                                                                .putExtra(PlayerActivity.CONTENT_ID_EXTRA, sample.contentId)
                                                                .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, sample.type);
                                                        startActivity(mpdIntent);

                                                    }
                                                    else
                                                    {
                                                        //Toast.makeText(getApplicationContext(), "selected: external", Toast.LENGTH_LONG).show();

                                                        Uri intentUri = Uri.parse(flag1);

                                                        Intent intent = new Intent();
                                                        intent.setAction(Intent.ACTION_VIEW);
                                                        intent.setDataAndType(intentUri, "video/*");
                                                        startActivity(intent);
                                                    }
                                                    //Toast.makeText(getApplicationContext(), "selectedPosition: " + selectedPosition, Toast.LENGTH_LONG).show();


                                                }
                                            })

                                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // removes the dialog from the screen

                                                }
                                            })

                                            .show();

                                }
                                else
                                {
                                    //Toast.makeText(getApplicationContext(), flag2, Toast.LENGTH_LONG).show();

                                    //playmovie(flag2);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MovieFullActivityNew.this, R.style.MyDialogTheme);

                                    builder.setTitle("Choose One")

                                            .setSingleChoiceItems(R.array.choicesnew, 0, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {


                                                }

                                            })

                                                    // Set the action buttons
                                            .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // user clicked OK, so save the mSelectedItems results somewhere
                                                    // or return them to the component that opened the dialog

                                                    int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                                    if(selectedPosition==0)
                                                    {
                                                        //Toast.makeText(getApplicationContext(), "selected: internal", Toast.LENGTH_LONG).show();

                                                        Samples.Sample sample=new Samples.Sample("Android screens (Matroska)", flag2, PlayerActivity.TYPE_OTHER);

                                                        Intent mpdIntent = new Intent(MovieFullActivityNew.this, PlayerActivity.class)

                                                                .setData(Uri.parse(sample.uri))
                                                                .putExtra(PlayerActivity.CONTENT_ID_EXTRA, sample.contentId)
                                                                .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, sample.type);
                                                        startActivity(mpdIntent);

                                                    }
                                                    else
                                                    {
                                                        //Toast.makeText(getApplicationContext(), "selected: external", Toast.LENGTH_LONG).show();

                                                        Uri intentUri = Uri.parse(flag2);

                                                        Intent intent = new Intent();
                                                        intent.setAction(Intent.ACTION_VIEW);
                                                        intent.setDataAndType(intentUri, "video/*");
                                                        startActivity(intent);
                                                    }
                                                    //Toast.makeText(getApplicationContext(), "selectedPosition: " + selectedPosition, Toast.LENGTH_LONG).show();


                                                }
                                            })

                                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // removes the dialog from the screen

                                                }
                                            })

                                            .show();
                                }
                                //Toast.makeText(getApplicationContext(), "selectedPosition: " + selectedPosition, Toast.LENGTH_LONG).show();


                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // removes the dialog from the screen

                            }
                        })

                        .show();




            }
        });

        downlaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MovieFullActivityNew.this, R.style.MyDialogTheme);
                //builder.setTitle("Movie");
                //builder.setMessage("Select part1 or part2");
//                builder.setPositiveButton("play", null);
//                builder.setNegativeButton("Cancel", null);
//                builder.show();

                builder.setTitle("Choose One")

                        // specify the list array, the items to be selected by default (null for none),
                        // and the listener through which to receive call backs when items are selected
                        // again, R.array.choices were set in the resources res/values/strings.xml
                        .setSingleChoiceItems(R.array.choices, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {


                                //Toast.makeText(getApplicationContext(), "Some actions maybe? Selected index: " + arg1, Toast.LENGTH_LONG).show();
                            }

                        })

                                // Set the action buttons
                        .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // user clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog

                                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                                if (selectedPosition == 0) {
                                    //Toast.makeText(getApplicationContext(), flag1, Toast.LENGTH_LONG).show();

                                    //playmovie(flag1);
                                    Intent intent = new Intent(getApplicationContext(), MovieDownloadService.class);
                                    intent.putExtra("movie", flag1);
                                    startService(intent);
                                    Toast.makeText(getApplicationContext(), "Download started", Toast.LENGTH_LONG).show();


                                } else {
                                    //Toast.makeText(getApplicationContext(), flag2, Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(getApplicationContext(), MovieDownloadService.class);
                                    intent.putExtra("movie", flag2);
                                    startService(intent);
                                    Toast.makeText(getApplicationContext(), "Download started", Toast.LENGTH_LONG).show();
                                }
                                //Toast.makeText(getApplicationContext(), "selectedPosition: " + selectedPosition, Toast.LENGTH_LONG).show();


                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // removes the dialog from the screen

                            }
                        })

                        .show();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                sendbar = String.valueOf(v);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","bobbyrana1983@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, sendbar);

                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


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
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

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

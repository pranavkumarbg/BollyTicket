package com.phpnew_pranavkumar.farmerproject.services;

/**
 * Created by kehooo on 9/24/2015.
 */
import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;

public class MovieDownloadService extends IntentService
{
    private static final String TAG = "FileIntentService";

    public MovieDownloadService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {



        String image=intent.getStringExtra("movie");

        String fileName = image.substring(image.lastIndexOf('/') + 1);

        //String DownloadUrl = "http://w15.hugefiles.net:182/d/m5o3l6fnfo6xvoksgstlfqors67coocpl6apcpzabbtcboskpmzx5w7s/Yo_Yo_Honey_Singh_3A_Dheere_Dheere_Video_Song__OFFICIAL___7C_Hrithik_Roshan_2C_Sonam_Kapoor.mp4";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(image));
        request.setDescription("Downloading Movie....");   //appears the same in Notification bar while downloading
        request.setTitle("Movie");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        // request.setDestinationInExternalFilesDir(getApplicationContext(),"movies", "sample.mkv");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, fileName);


        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);



    }

    public void showDownload(View view) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);
    }

}

package com.phpnew_pranavkumar.farmerproject.json;

/**
 * Created by kehooo on 11/16/2015.
 */
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;

public class GetJSONObject {

    public static JSONObject getJSONObject(String url) throws IOException,
            JSONException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        // Use HttpURLConnection
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            jsonObject = jsonParser.getJSONHttpURLConnection(url);
        } else {
            // use HttpClient
            jsonObject = jsonParser.getJSONHttpClient(url);
        }
        return jsonObject;
    }
}

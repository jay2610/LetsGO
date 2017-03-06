package edu.project.LetsGO;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     RestClient client = YelpClientApp.getRestClient();
 *     // use client to send requests to API
 *
 */
public class YelpClientApp extends Application {
    private static Context context;

    public static YelpClient getRestClient() {
        return (YelpClient) YelpClient.getInstance(YelpClient.class, YelpClientApp.context);


    }

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        YelpClientApp.context = this;

        YelpClient client = YelpClientApp.getRestClient();
        client.search("food", "New York", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, Header[] headers, JSONObject body) {
                try {
                    JSONArray businessesJson = body.getJSONArray("businesses");

                    // Here we now have the json array of businesses!
                    //Log.d("DEBUG", businessesJson.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(getBaseContext(), "FAIL", Toast.LENGTH_LONG).show();
            }
        });
    }
}
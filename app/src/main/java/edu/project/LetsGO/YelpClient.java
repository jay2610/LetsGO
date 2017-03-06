package edu.project.LetsGO;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.model.Token;

public class YelpClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = YelpApi2.class; // Change this
    public static final String REST_URL = "http://api.yelp.com/v2"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "1CmQ3pxRA0UNGq_jZCLa0A";       // Change this
    public static final String TOKEN = "fvcSBu7sZ_Zow8iGuye5kwhp7OLCek_e";
    public static final String TOKEN_SECRET = "zkoTEsTo3s9frV5NyF28OzgDPYE";
    public static final String REST_CONSUMER_SECRET = "2iUAx1OiPV1U8YlHSAgWIb7_V1Q"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://aniYelp"; // Change this (here and in manifest)

    public YelpClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
        this.client.setAccessToken(new Token(TOKEN, TOKEN_SECRET));
    }

    public void search(String term, String location, AsyncHttpResponseHandler handler) {
        // http://api.yelp.com/v2/search?term=food&location=San+Francisco
        String apiUrl = getApiUrl("search");

        RequestParams params = new RequestParams();
        params.put("term", term);
        params.put("location", location);
        //params.put("sort", "2");
        client.get(apiUrl, params, handler);
    }



    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}
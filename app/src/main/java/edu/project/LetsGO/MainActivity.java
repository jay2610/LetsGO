package edu.project.LetsGO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

//import android.support.v7.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public Button settingsbutton;
    SearchView mSearchView;
    //recycler view objects
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Business> listItems;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsbutton = (Button) findViewById(R.id.button_settings);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        listItems = new ArrayList<>();
        loadRecyclerViewData();

        settingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void loadRecyclerViewData() {
        Log.d(TAG, "loadRecyclerViewData: Starts");
        mSearchView = (SearchView) findViewById(R.id.search_view);

        //       progress dialogue
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading data ..");
//        progressDialog.show();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                YelpClient client = YelpClientApp.getRestClient();

                // coordinates
                CoordinateOptions coordinate = CoordinateOptions.builder()
                        .latitude(37.7577)
                        .longitude(-122.4376).build();

//                Call<SearchResponse> call = yelpAPI.search(coordinate, params);
//                Response<SearchResponse> response = call.execute();
                client.search(query, "NewYork", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int code, Header[] headers, JSONObject body) {
                        // progressDialog.dismiss();
                        try {
                            JSONArray businessesJson = body.getJSONArray("businesses");

                            //  ArrayList<Business> businesses = Business.fromJson(businessesJson);
                            // Now we have an array of business objects
                            // Might now create an adapter BusinessArrayAdapter<Business> to load the businesses into a list
                            // You might also simply update the data in an existing array and then notify the adapter
                            listItems = new ArrayList<>();
                            for (int i = 0; i < businessesJson.length(); i++) {
                                JSONObject o = businessesJson.getJSONObject(i);
                                String m = o.getString("location");
                                Business buisnessItem = new Business(
                                        o.getString("name"),
                                        o.getString("id"),m,
                                        //o.getString("city"),
                                        o.getString("image_url"),
                                        o.getString("rating_img_url"),
                                        o.getString("mobile_url")
                                );
                                listItems.add(buisnessItem);

                                Log.d("DEBUG", businessesJson.toString());
                                adapter = new MyAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                                recyclerView.clearOnScrollListeners();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        Toast.makeText(getBaseContext(), "FAIL", Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
    }
}
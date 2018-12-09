package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Main screen for our API testing app.
 */
public final class MainActivity extends AppCompatActivity implements Serializable {
    /**
     * Default logging tag for messages from the main activity.
     */
    private static final String TAG = "Lab12:Main";

    /** Array that holds all URLs pulled from TicketMaster. */
    private ArrayList<String> links = new ArrayList<>();

    /** Array that holds all events pulled from TicketMaster. */
    private ArrayList<String> events = new ArrayList<>();

    /** Initialized null adapter. */
    private EventAdapter adapter;

    /** Initialized null recycler view. */
    private RecyclerView rvEvents;

    /** Checks if the RecyclerView has been filled. */
    private boolean isFilled = false;

    /** Initialized null button for switching activities. */
    private Button myEvents;

    /**
     * Request queue for our network requests.
     */
    private static RequestQueue requestQueue;


    /**
     * Run when activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(this);
        // Load the main layout for our activity
        setContentView(R.layout.activity_main);
        // Begin background web API call
        new RetrieveData().execute();
    }

    /**
     * Sets recycler view.
     */
    public void setRV() {
        rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
        // Create adapter passing in the data
        adapter = new EventAdapter(this, events, links);
        // Attach the adapter to the RecyclerView to populate items
        rvEvents.setAdapter(adapter);
        // Set layout manager
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        isFilled = true;
    }

    /**
     * Opens next view, which is the my events page.
     *
     * @param view next screen to jump to
     */
    public void openMyEvents(final View view) {
        Intent intent = new Intent(MainActivity.this, MyEventsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("names", events);
        intent.putExtras(b);
        startActivity(intent);
    }

    /**
     * Runs in background.
     */
    public class RetrieveData extends AsyncTask<Void, Void, String> {

        /** Exception to be thrown if any. */
        private Exception exception;

        /** Set button visible.*/
        protected void onPreExecute() {
            myEvents = findViewById(R.id.myEvents);
            myEvents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (!isFilled) {
                        setRV();
                        myEvents.setText("LOAD EVENTS");
                    } else {
                        openMyEvents(v);
                        myEvents.setText("OPEN MY EVENTS");
                    }
                }
            });
        }

        /** Execute loading Web API data.
         * @param urls all void because no input
         * @return strings with parsed data
         */
        protected String doInBackground(final Void... urls) {
            Log.w(TAG, "in bkg");
            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        "https://app.ticketmaster.com/discovery/v2/events.json?classificationName=music&countryCode=US&stateCode=IL&city=Champaign&apikey=NLkAS2bWNylyGck1kAuFaJ2k01NvNAtk",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {
                                Log.w(TAG, response.toString());
                                try {
                                    JSONObject embeddedObject = response.getJSONObject("_embedded");
                                    JSONArray jsonArray = embeddedObject.getJSONArray("events");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject embed = jsonArray.getJSONObject(i);
                                        String name = embed.getString("name");
                                        events.add(name);
                                        String url = embed.getString("url");
                                        links.add(url);
                                    }

                                    for (int i = 0; i < events.size(); i++) {
                                        Log.w(TAG, events.get(i));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.w(TAG, error.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Finished!";
        }

        /** Logs error if any.
         * @param response error message if any*/
        protected void onPostExecute(final String response) {
            if (response == null) {
                Log.d(TAG, "THERE WAS AN ERROR");
            }
        }
    }
}




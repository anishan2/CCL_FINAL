package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/** Displays my events. */
public class MyEventsActivity extends AppCompatActivity {
    /** ArrayList for my scheduled events. */
    private ArrayList<String> attended = new ArrayList<>();

    /**
     * Run when activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        final Button backButton = findViewById(R.id.backButton);
        final ImageView profilePic = findViewById(R.id.profilePic);
        final TextView attendedTV = findViewById(R.id.attendedTV);

        Bundle bundle = getIntent().getExtras();
        attended = (ArrayList<String>) bundle.getSerializable("names");
        attendedTV.setText("Number of events scheduled: " + attended.size());
        RecyclerView rvEvents = (RecyclerView) findViewById(R.id.rvAttended);

        // Initialize events

        // Create adapter passing in the data
        AttendedAdapter adapter = new AttendedAdapter(attended);
        // Attach the adapter to the RecyclerView to populate items
        rvEvents.setAdapter(adapter);
        // Set layout manager
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Opens next view, which is the home page.
     * @param view next screen to jump to
     */
    public void openAllEvents(final View view) {
        Intent intent = new Intent(MyEventsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;


/** Create the custom adapter extending from RecyclerView.Adapter. */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    /** List of events to bind to View. */
    private List<String> mEvents;
    /** List of links to bind to View. */
    private List<String> mLinks;
    /** Context for the UI. */
    private Context mContext;

    /**Pass in the event array into the constructor.
     *@param events events in the area
     * @param links links to purchase tickets
     * @param context context for UI*/
    public EventAdapter(final Context context, final List<String> events, final List<String> links) {
        mEvents = events;
        mLinks = links;
        mContext = context;
    }

    /**Initializes RV with a sub class constructor.*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**Text view in each item of the RecyclerView.*/
        private TextView eventTextView;
        /**Button in each item of the RecyclerView.*/
        private Button availButton;

        /** Initializes ViewHolder.
         * @param itemView view object for entire recyler view item
         * @param ctx context for UI*/
        public ViewHolder(final Context ctx, final View itemView) {
            super(itemView);
            eventTextView = (TextView) itemView.findViewById(R.id.event_name);
            availButton = (Button) itemView.findViewById(R.id.avail_button);
        }

    }

    /**Creates ViewHolder for RecyclerView.*/
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.event_item_style, parent, false);

        // Return a new holder instance
        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(context, contactView);
        return viewHolder;
    }

    /**Binds data to RecyclerView.
     */
    @Override
    public void onBindViewHolder(final EventAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final String e = mEvents.get(position);
        final String u = mLinks.get(position);

        // Set item views based on your views and data model - EDIT
        TextView textView = viewHolder.eventTextView;
        textView.setText(e);
        Button button = viewHolder.availButton;
        button.setText("Buy Tickets");
        button.setEnabled(true);

        viewHolder.availButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(u));
                mContext.startActivity(browse);
            }
        });
    }

    /** Returns array size. */
    @Override
    public int getItemCount() {
        return mEvents.size();
    }
}

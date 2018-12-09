package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/** Create the custom adapter extending from RecyclerView.Adapter. */
public class AttendedAdapter extends RecyclerView.Adapter<AttendedAdapter.ViewHolder> {

    /** List of events to bind to View. */
    private List<String> mEvents;

    /**Pass in the event array into the constructor.
     *@param events events passed into constructor*/
    public AttendedAdapter(final List<String> events) {
        mEvents = events;
    }

    /**Binds data to RecyclerView.*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**Text view in each item of the RecyclerView.*/
        private TextView eventTextView;
        /**Button in each item of the RecyclerView.*/
        private Button availButton;

        /** Binds data to RecyclerView.
         * @param itemView each item*/
        public ViewHolder(final View itemView) {
            super(itemView);
            eventTextView = (TextView) itemView.findViewById(R.id.event_name);
        }
    }

    /** Binds data to RecyclerView.
     */
    @Override
    public AttendedAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.event_item_style2, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    /**Binds data to RecyclerView.
     */
    @Override
    public void onBindViewHolder(final AttendedAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        String e = mEvents.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.eventTextView;
        textView.setText(e);
    }

    /** Returns the size of the array.
     */
    @Override
    public int getItemCount() {
        return mEvents.size();
    }

}

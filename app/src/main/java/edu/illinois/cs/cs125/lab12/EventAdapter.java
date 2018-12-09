package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/** Create the custom adapter extending from RecyclerView.Adapter. */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    /** List of events to bind to View. */
    private List<String> mEvents;
    /** List of events to bind to View. */
    private List<String> mLinks;

    /**Pass in the event array into the constructor.
     *@param events events passed into constructor
     * @param links links */
    public EventAdapter(final List<String> events, final List<String> links) {
        mEvents = events;
        mLinks = links;
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
            availButton = (Button) itemView.findViewById(R.id.avail_button);

//            availButton.setTag(R.integer., itemView);
//            btn_minus.setTag(R.integer.btn_minus_view, itemView);
//            btn_plus.setOnClickListener(this);
        }
    }

    /** Binds data to RecyclerView.
     */
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.event_item_style, parent, false);

        // Return a new holder instance
        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    /**Binds data to RecyclerView.
     */
    @Override
    public void onBindViewHolder(final EventAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        String e = mEvents.get(position);

        // Set item views based on your views and data model - EDIT
        TextView textView = viewHolder.eventTextView;
        textView.setText(e);
        Button button = viewHolder.availButton;
        button.setText("Buy Tickets");

        button.setEnabled(true);
    }

    /** Returns array size. */
    @Override
    public int getItemCount() {
        return mEvents.size();
    }


    /** Returns array size.
     * @param v view */
    public void onClick(final View v) {
        if (v.getId() ==  ){

            View tempview = (View) btn_plus.getTag(R.integer.btn_plus_view);
            TextView tv = (TextView) tempview.findViewById(R.id.number);
            int number = Integer.parseInt(tv.getText().toString()) + 1;
            tv.setText(String.valueOf(number));
            MainActivity.modelArrayList.get(getAdapterPosition()).setNumber(number);

        } else if(v.getId() == btn_minus.getId()) {

            View tempview = (View) btn_minus.getTag(R.integer.btn_minus_view);
            TextView tv = (TextView) tempview.findViewById(R.id.number);
            int number = Integer.parseInt(tv.getText().toString()) - 1;
            tv.setText(String.valueOf(number));
            MainActivity.modelArrayList.get(getAdapterPosition()).setNumber(number);
        }
    }

}

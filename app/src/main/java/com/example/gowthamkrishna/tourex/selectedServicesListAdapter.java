package com.example.gowthamkrishna.tourex;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class selectedServicesListAdapter extends RecyclerView.Adapter<selectedServicesListAdapter.MyViewHolder> {

    private List<selectedServicesList> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView service, title, availability, cost, frequency, reputation, response, success_rate, mobile_number, website, email_id;

        public MyViewHolder(View view) {
            super(view);
            service = (TextView) view.findViewById(R.id.service);
            title = (TextView) view.findViewById(R.id.title);
            availability= (TextView) view.findViewById(R.id.availability);
            cost= (TextView) view.findViewById(R.id.cost);
            frequency = (TextView) view.findViewById(R.id.frequency);
            reputation= (TextView) view.findViewById(R.id.reputation);
            response= (TextView) view.findViewById(R.id.response);
            success_rate= (TextView) view.findViewById(R.id.success_rate);
            mobile_number = (TextView) view.findViewById(R.id.mobile_number);
            website = (TextView) view.findViewById(R.id.website);
            email_id = (TextView) view.findViewById(R.id.email_id);
        }
    }


    public selectedServicesListAdapter(List<selectedServicesList> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selectedservicecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        selectedServicesList movie = moviesList.get(position);
        holder.service.setText("\n" + String.valueOf(movie.getService()));
        holder.title.setText(movie.getTitle());
        holder.availability.setText(String.valueOf(movie.getAvailability()));
        holder.cost.setText(String.valueOf(movie.getCost()));
        holder.frequency.setText(String.valueOf(movie.getFrequency()));
        holder.reputation.setText(String.valueOf(movie.getReputation()));
        holder.response.setText(String.valueOf(movie.getResponse()));
        holder.success_rate.setText(String.valueOf(movie.getSuccess_rate()));
        holder.mobile_number.setText(movie.getMobile_number());
        holder.website.setText(movie.getWebsite());
        holder.email_id.setText(movie.getEmail_id());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
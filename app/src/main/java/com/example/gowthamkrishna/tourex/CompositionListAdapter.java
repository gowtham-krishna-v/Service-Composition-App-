package com.example.gowthamkrishna.tourex;

import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CompositionListAdapter extends RecyclerView.Adapter<CompositionListAdapter.MyViewHolder> {

    private List<CompositionList> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, composition;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.composition);
            title.setMovementMethod(ScrollingMovementMethod.getInstance());
            composition = (TextView) view.findViewById(R.id.title);
        }
    }


    public CompositionListAdapter(List<CompositionList> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compositionlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CompositionList movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.composition.setText(movie.getComposition());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

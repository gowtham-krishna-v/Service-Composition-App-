package com.example.gowthamkrishna.tourex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InputQosValuesAdapter extends RecyclerView.Adapter<InputQosValuesAdapter.MyViewHolder> {

    private List<InputQosValues> qosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView parameter, year, value;

        public MyViewHolder(View view) {
            super(view);
            parameter = (TextView) view.findViewById(R.id.parameter);
            value = (TextView) view.findViewById(R.id.value);
        }
    }


    public InputQosValuesAdapter(List<InputQosValues> qosList) {
        this.qosList = qosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_input_qos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InputQosValues movie = qosList.get(position);
        holder.parameter.setText(movie.getparameter());
        holder.value.setText(movie.getvalue());
    }

    @Override
    public int getItemCount() {
        return qosList.size();
    }
}
package com.example.gowthamkrishna.tourex;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class selectedComposition extends AppCompatActivity {


    ArrayList<String> services = new ArrayList<String>();
    List<serviceStore> serviceList = new ArrayList<serviceStore>();
    private List<selectedServicesList> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private selectedServicesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_composition);


        Intent intent = getIntent();
        services = intent.getStringArrayListExtra("services");
        serviceList = (ArrayList<serviceStore>)intent.getSerializableExtra("SelectedComposition");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new selectedServicesListAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareServiceData();
    }

    public void prepareServiceData(){
        movieList.clear();
        for(serviceStore a : serviceList){
            movieList.add(new selectedServicesList(a));
        }
    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you wish to provide a feedback?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(selectedComposition.this, Feedback.class);
                        intent.putExtra("SelectedComposition" , (ArrayList<serviceStore>) serviceList);
                        intent.putExtra("services", services);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(selectedComposition.this, UserInput.class);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

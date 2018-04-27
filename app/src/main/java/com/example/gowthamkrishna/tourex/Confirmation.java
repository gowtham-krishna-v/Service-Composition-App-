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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Confirmation extends AppCompatActivity {

    private List<InputQosValues> qosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InputQosValuesAdapter mAdapter;

    private List<InputQosValues> serviceList = new ArrayList<>();
    private RecyclerView recyclerView2;
    private InputQosValuesAdapter mAdapter2;


    Button _btn_search;
    ArrayList<String> services = new ArrayList<String>();
    ArrayList<Integer> qos = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent=getIntent();
        services = intent.getStringArrayListExtra("services");
        qos = intent.getIntegerArrayListExtra("qos");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new InputQosValuesAdapter(qosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareQosDisplay();

        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
        mAdapter2 = new InputQosValuesAdapter(serviceList);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter2);
        prepareServiceDisplay();

        _btn_search = findViewById(R.id.btn_search);
        _btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newintent = new Intent(Confirmation.this, Process.class);
                newintent.putExtra("services", services);
                newintent.putExtra("qos",qos);
                startActivity(newintent);
            }
        });
    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to go back?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void prepareQosDisplay(){
        InputQosValues inputQosValues = new InputQosValues("\nAvailability", "\n" + qos.get(0).toString());
        qosList.add(inputQosValues);

        inputQosValues = new InputQosValues("\nCost", "\n" + qos.get(1).toString());
        qosList.add(inputQosValues);

        inputQosValues = new InputQosValues("\nFrequency", "\n" + qos.get(2).toString());
        qosList.add(inputQosValues);

        inputQosValues = new InputQosValues("\nReputation", "\n" + qos.get(3).toString());
        qosList.add(inputQosValues);

        inputQosValues = new InputQosValues("\nResponse", "\n" + qos.get(4).toString());
        qosList.add(inputQosValues);

        inputQosValues = new InputQosValues("\nSuccess rate", "\n" + qos.get(5).toString());
        qosList.add(inputQosValues);
    }

    public void prepareServiceDisplay(){
        InputQosValues inputQosValues;

        int count = 0;
        for(String serviceListItem: services ){
            count+=1;
            inputQosValues = new InputQosValues("\n" + getServiceName(Integer.parseInt(serviceListItem)), "\n" + Integer.toString(count));
            serviceList.add(inputQosValues);
        }
        count=0;
    }

    public String getServiceName(int val){
        switch (val){
            case 0 : return "Cab";
            case 1 : return "Clothing";
            case 2 : return "Jewellery";
            case 3 : return "Lodge";
            case 4 : return "Restaurant";
            case 5 : return "Shopping";
            case 6 : return "Sites";
            case 7 : return "Travel";
            default: return "";
        }
    }
}

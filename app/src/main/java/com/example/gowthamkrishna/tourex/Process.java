package com.example.gowthamkrishna.tourex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CompletableFuture;

public class Process extends AppCompatActivity {


    // Get a reference to our posts
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> services = new ArrayList<String>();
    ArrayList<Integer> qos = new ArrayList<Integer>();
    Button button;
    List<List<serviceStore>> serviceList = new ArrayList<List<serviceStore>>();
    List<serviceStore> temp;
    serviceStore tempService;
    TextView statusTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        database = FirebaseDatabase.getInstance();

        button = (Button)findViewById(R.id.button);
        Intent intent=getIntent();

        statusTextView = findViewById(R.id.textView11);


        services = intent.getStringArrayListExtra("services");
        qos = intent.getIntegerArrayListExtra("qos");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newInten = new Intent(Process.this,Processed.class);
                newInten.putExtra( "serviceList" , (ArrayList<List<serviceStore>>) serviceList);
                newInten.putExtra("services", services);
                newInten.putExtra("qos",qos);
                startActivity(newInten);
            }
        });

        ref = database.getReference().child("services");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot serviceSnapshot : dataSnapshot.getChildren()) {
                    temp = new ArrayList<serviceStore>();
                    for(DataSnapshot singleItem : serviceSnapshot.getChildren()) {
                        tempService = singleItem.getValue(serviceStore.class);
                        temp.add(tempService);
                    }
                    serviceList.add(temp);
                }
                statusTextView.setText("Data fetched from cloud....Please start the processing...");
                button.setEnabled(true);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}

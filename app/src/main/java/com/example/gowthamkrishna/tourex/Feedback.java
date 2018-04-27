package com.example.gowthamkrishna.tourex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {

    Button _btn_search;
    EditText availability, cost, frequency, reputation, response, success_rate;
    String ava,cos,freq,rep,res,suc;
    int ava_val,cos_val, freq_val, rep_val, res_val, suc_val;
    int i;
    double value;

    ArrayList<Integer> qos = new ArrayList<Integer>();
    ArrayList<String> services = new ArrayList<String>();
    List<serviceStore> serviceList = new ArrayList<serviceStore>();

    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        database = FirebaseDatabase.getInstance();
        //ref = database.getReference().child("services");

//        availability = findViewById(R.id.input_availability);
//        cost = findViewById(R.id.input_cost);
//        frequency = findViewById(R.id.input_frequency);
//        reputation = findViewById(R.id.input_reputation);
//        response = findViewById(R.id.input_response);
        success_rate = findViewById(R.id.input_success_rate);

        Intent intent = getIntent();
        services = intent.getStringArrayListExtra("services");
        serviceList = (ArrayList<serviceStore>)intent.getSerializableExtra("SelectedComposition");

        _btn_search = findViewById(R.id.btn_previous);
        _btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ava = availability.getText().toString();
//                cos = cost.getText().toString();
//                freq = frequency.getText().toString();
//                rep = reputation.getText().toString();
//                res = response.getText().toString();
                suc = success_rate.getText().toString();

                if(validate()){
                    i=0;
                    for(String singleService: services){
//                        value = (Integer.parseInt(ava)+serviceList.get(i).getAvailability())/2;
//                        database.getReference().child("services").child(getServiceName(Integer.parseInt(singleService))).child(getServiceNumber(serviceList.get(i).getService())).child("availability").setValue(value);
//                        value = (Integer.parseInt(cos)+serviceList.get(i).getCost())/2;
//                        database.getReference().child("services").child(getServiceName(Integer.parseInt(singleService))).child(getServiceNumber(serviceList.get(i).getService())).child("cost").setValue(value);
//                        value = (Integer.parseInt(freq)+serviceList.get(i).getFrequency())/2;
//                        database.getReference().child("services").child(getServiceName(Integer.parseInt(singleService))).child(getServiceNumber(serviceList.get(i).getService())).child("frequency").setValue(value);
//                        value = (Integer.parseInt(rep)+serviceList.get(i).getReputation())/2;
//                        database.getReference().child("services").child(getServiceName(Integer.parseInt(singleService))).child(getServiceNumber(serviceList.get(i).getService())).child("reputation").setValue(value);
//                        value = (Integer.parseInt(res)+serviceList.get(i).getResponse())/2;
//                        database.getReference().child("services").child(getServiceName(Integer.parseInt(singleService))).child(getServiceNumber(serviceList.get(i).getService())).child("response").setValue(value);
                        value = (Integer.parseInt(suc)+serviceList.get(i).getSuccess_rate())/2;
                        database.getReference().child("services").child(getServiceName(Integer.parseInt(singleService))).child(getServiceNumber(serviceList.get(i).getService())).child("success_rate").setValue(value);
                        i++;
                        value = 0;
                    }
                    Toast.makeText(Feedback.this,"Back to Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Feedback.this, UserInput.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Feedback.this,"Invalid Input", Toast.LENGTH_SHORT).show();
                }
                qos.clear();
            }
        });
    }


    public String getServiceNumber(int val){
        switch (val){
            case 1 : return "service_1";
            case 2 : return "service_2";
            case 3 : return "service_3";
            case 4 : return "service_4";
            case 5 : return "service_5";
            case 6 : return "service_6";
            case 7 : return "service_7";
            case 8 : return "service_8";
            case 9 : return "service_9";
            case 10 : return  "service_10";
            default: return "";
        }
    }

    public String getServiceName(int val){
        switch (val){
            case 0 : return "cab";
            case 1 : return "clothing";
            case 2 : return "jewellery";
            case 3 : return "lodge";
            case 4 : return "restaurant";
            case 5 : return "shopping";
            case 6 : return "sites";
            case 7 : return "travel";
            default: return "";
        }
    }

    public boolean validate(){
        boolean valid = true;

//        if(ava.isEmpty()){
//            availability.setError("Cannot be empty!");
//            valid = false;
//        }else{
//            ava_val = Integer.parseInt(ava);
//            if(ava_val <= 0 || ava_val > 10){
//                availability.setError("Enter value between 1 and 10");
//                valid = false;
//            }else{
//                qos.add(ava_val);
//            }
//        }
//
//        if(cos.isEmpty()){
//            cost.setError("Cannot be empty!");
//            valid = false;
//        }else{
//            cos_val = Integer.parseInt(cos);
//            if(cos_val <= 0 || cos_val > 10){
//                cost.setError("Enter value between 1 and 10");
//                valid = false;
//            }else{
//                qos.add(cos_val);
//            }
//        }
//
//        if(freq.isEmpty()){
//            frequency.setError("Cannot be empty!");
//            valid = false;
//        }else{
//            freq_val = Integer.parseInt(freq);
//            if(freq_val <= 0 || freq_val > 10){
//                frequency.setError("Enter value between 1 and 10");
//                valid = false;
//            }else{
//                qos.add(freq_val);
//            }
//        }
//
//        if(rep.isEmpty()){
//            reputation.setError("Cannot be empty!");
//            valid = false;
//        }else{
//            rep_val = Integer.parseInt(rep);
//            if(rep_val <= 0 || rep_val > 10){
//                reputation.setError("Enter value between 1 and 10");
//                valid = false;
//            }else{
//                qos.add(rep_val);
//            }
//        }
//
//        if(res.isEmpty()){
//            response.setError("Cannot be empty!");
//            valid = false;
//        }else{
//            res_val = Integer.parseInt(res);
//            if(res_val <= 0 || res_val > 10){
//                response.setError("Enter value between 1 and 10");
//                valid = false;
//            }else{
//                qos.add(res_val);
//            }
//        }

        if(suc.isEmpty()){
            success_rate.setError("Cannot be empty!");
            valid = false;
        }else{
            suc_val = Integer.parseInt(suc);
            if(suc_val <= 0 || suc_val > 10){
                success_rate.setError("Enter value between 1 and 10");
                valid = false;
            }else{
                qos.add(suc_val);
            }
        }

        return valid;
    }
}
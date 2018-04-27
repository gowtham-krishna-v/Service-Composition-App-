package com.example.gowthamkrishna.tourex;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UserInput2 extends AppCompatActivity {

    Button _btn_search;
    EditText availability, cost, frequency, reputation, response, success_rate;
    String ava,cos,freq,rep,res,suc;
    int ava_val,cos_val, freq_val, rep_val, res_val, suc_val;
    ArrayList<String> services = new ArrayList<String>();
    ArrayList<Integer> qos = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input2);
        Intent oldIntent = getIntent();
        services = oldIntent.getStringArrayListExtra("services");

        availability = findViewById(R.id.input_availability);
        cost = findViewById(R.id.input_cost);
        frequency = findViewById(R.id.input_frequency);
        reputation = findViewById(R.id.input_reputation);
        response = findViewById(R.id.input_response);
        success_rate = findViewById(R.id.input_success_rate);

        _btn_search = findViewById(R.id.btn_next);
        _btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ava = availability.getText().toString();
                cos = cost.getText().toString();
                freq = frequency.getText().toString();
                rep = reputation.getText().toString();
                res = response.getText().toString();
                suc = success_rate.getText().toString();

                if(validate()){
                    Intent intent = new Intent(UserInput2.this, Confirmation.class);
                    intent.putExtra("services", services);
                    intent.putExtra("qos",qos);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(UserInput2.this,"Invalid Input", Toast.LENGTH_SHORT).show();
                }
                qos.clear();
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

    public boolean validate(){
        boolean valid = true;

        if(ava.isEmpty()){
            availability.setError("Cannot be empty!");
            valid = false;
        }else{
            ava_val = Integer.parseInt(ava);
            if(ava_val <= 0 || ava_val > 10){
                availability.setError("Enter value between 1 and 10");
                valid = false;
            }else{
                qos.add(ava_val);
            }
        }

        if(cos.isEmpty()){
            cost.setError("Cannot be empty!");
            valid = false;
        }else{
            cos_val = Integer.parseInt(cos);
            if(cos_val <= 0 || cos_val > 10){
                cost.setError("Enter value between 1 and 10");
                valid = false;
            }else{
                qos.add(cos_val);
            }
        }

        if(freq.isEmpty()){
            frequency.setError("Cannot be empty!");
            valid = false;
        }else{
            freq_val = Integer.parseInt(freq);
            if(freq_val <= 0 || freq_val > 10){
                frequency.setError("Enter value between 1 and 10");
                valid = false;
            }else{
                qos.add(freq_val);
            }
        }

        if(rep.isEmpty()){
            reputation.setError("Cannot be empty!");
            valid = false;
        }else{
            rep_val = Integer.parseInt(rep);
            if(rep_val <= 0 || rep_val > 10){
                reputation.setError("Enter value between 1 and 10");
                valid = false;
            }else{
                qos.add(rep_val);
            }
        }

        if(res.isEmpty()){
            response.setError("Cannot be empty!");
            valid = false;
        }else{
            res_val = Integer.parseInt(res);
            if(res_val <= 0 || res_val > 10){
                response.setError("Enter value between 1 and 10");
                valid = false;
            }else{
                qos.add(res_val);
            }
        }

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

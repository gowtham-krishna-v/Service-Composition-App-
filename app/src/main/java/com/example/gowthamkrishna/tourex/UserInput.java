package com.example.gowthamkrishna.tourex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class UserInput extends AppCompatActivity {

    Button _next_button;
    ArrayList<String> services = new ArrayList<String>();
    CheckBox cab, clothing, jewellery, lodge, restaurant, shopping, sites, travel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);


        cab = findViewById(R.id.input_cab);
        clothing = findViewById(R.id.input_clothing);
        jewellery = findViewById(R.id.input_jewellery);
        lodge = findViewById(R.id.input_lodge);
        restaurant = findViewById(R.id.input_restaurant);
        shopping = findViewById(R.id.input_shopping);
        sites = findViewById(R.id.input_sites);
        travel = findViewById(R.id.input_travel);

        _next_button = findViewById(R.id.btn_next);
        _next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    getSelection();
                    userInput2();
                }else {
                    Toast.makeText(UserInput.this,"No Selections Made!", Toast.LENGTH_SHORT).show();
                }
                services.clear();
            }
        });
    }

    public boolean validate(){
        if(!cab.isChecked() && !clothing.isChecked() && !jewellery.isChecked() && !lodge.isChecked() && !restaurant.isChecked() && !shopping.isChecked() && !sites.isChecked() && !travel.isChecked() )
            return false;
        return true;
    }

    public void getSelection(){

        if(cab.isChecked())
            services.add("0");
        if(clothing.isChecked())
            services.add("1");
        if(jewellery.isChecked())
            services.add("2");
        if(lodge.isChecked())
            services.add("3");
        if(restaurant.isChecked())
            services.add("4");
        if(shopping.isChecked())
            services.add("5");
        if(sites.isChecked())
            services.add("6");
        if(travel.isChecked())
            services.add("7");
    }

    public void userInput2(){
        Intent intent = new Intent(this, UserInput2.class);
        intent.putExtra("services", services);
        startActivity(intent);
    }
}

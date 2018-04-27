package com.example.gowthamkrishna.tourex;

import java.io.Serializable;

//Class to store the Service Data
public class serviceStore implements Serializable{

    //Variable Declaration
    int service;
    String title;
    double availability;
    double cost;
    double frequency;
    double reputation;
    double response;
    double success_rate;
    String mobile_number;
    String website;
    String email_id;
    int fuzzy_tag;
    double topsis_tag;

    public void setService(int service){
        this.service = service;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAvailability(double availability){
        this.availability = availability;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public void setFrequency(double frequency){
        this.frequency = frequency;
    }

    public void setReputation(double reputation){
        this.reputation = reputation;
    }

    public void setResponse(double response){
        this.response = response;
    }

    public void setSuccess_rate(double success_rate){
        this.success_rate = success_rate;
    }

    public void setMobile_number(String mobile_number){
        this.mobile_number = mobile_number;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public void setEmail_id(String email_id){
        this.email_id = email_id;
    }

    public void setFuzzy_tag(int fuzzy_tag){
        this.fuzzy_tag = fuzzy_tag;
    }

    public void setTopsis_tag(double topsis_tag){
        this.topsis_tag = topsis_tag;
    }

    public int getService(){
        return service;
    }

    public String getTitle(){
        return title;
    }

    public double getAvailability(){
        return availability;
    }

    public double getCost(){
        return cost;
    }

    public double getFrequency(){
        return frequency;
    }

    public double getReputation(){
        return reputation;
    }

    public double getResponse(){
        return response;
    }

    public double getSuccess_rate(){
        return success_rate;
    }

    public String getMobile_number(){
        return mobile_number;
    }

    public String getWebsite(){
        return website;
    }

    public String getEmail_id(){
        return email_id;
    }

    public int getFuzzy_tag(){
        return fuzzy_tag;
    }

    public double getTopsis_tag(){
        return topsis_tag;
    }



    public serviceStore(){
        service = 0;
        title = "";
        availability = 0;
        cost = 0;
        frequency = 0;
        reputation = 0;
        response = 0;
        success_rate = 0;
        mobile_number = "";
        website = "";
        email_id = "";
        fuzzy_tag = 0;
        topsis_tag = 0;

    }

    //Constructor to initialize data to variables
    public serviceStore(int sno, String name, double availability, double cost, double frequency, double reputation, double response, double success_rate, String mobile, String website, String email){
        service=sno;
        title = name;
        this.availability=availability;
        this.cost=cost;
        this.frequency=frequency;
        this.reputation=reputation;
        this.response=response;
        this.success_rate=success_rate;
        fuzzy_tag=0;
        topsis_tag=0;
        mobile_number=mobile;
        this.website=website;
        email_id=email;
    }

    public serviceStore(serviceStore temp){
        this.service=temp.service;
        this.title = temp.title;
        this.availability=temp.availability;
        this.cost=temp.cost;
        this.frequency=temp.frequency;
        this.reputation=temp.reputation;
        this.response=temp.response;
        this.success_rate=temp.success_rate;
        this.fuzzy_tag=0;
        this.topsis_tag=0;
        this.mobile_number=temp.mobile_number;
        this.website=temp.website;
        this.email_id=temp.email_id;
    }

    //Function to print the data of a service
    public void printServiceQos(){
        System.out.println(service + "\t" + title + "\t" + availability + "\t" + cost + "\t" + frequency + "\t" + reputation + "\t" + response + "\t" + success_rate + "\t" + mobile_number + "\t" + website + "\t" + email_id);
    }

    //Function to print the data of a service
    public void printServiceQosWithFuzzyTag(){
        System.out.println(service + "\t" + availability + "\t" + cost + "\t" + frequency + "\t" + reputation + "\t" + response + "\t" + success_rate + "\t" + fuzzy_tag);
    }

    //Function to print the data of a service
    public void printServiceQosWithTopsisTag(){
        System.out.println(service + "\t" + title + "\t" + availability + "\t" + cost + "\t" + frequency + "\t" + reputation + "\t" + response + "\t" + success_rate + "\t" + topsis_tag);
    }

    //Function to print the fuzzy classified values
    void printFuzzyTag(){
        System.out.println(service + "\t" + reputation + "\t" + fuzzy_tag);
    }
}

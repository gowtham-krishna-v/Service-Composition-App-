package com.example.gowthamkrishna.tourex;

public class selectedServicesList {

    private int service;
    private String title;
    private double availability;
    private double cost;
    private double frequency;
    private double reputation;
    private double response;
    private double success_rate;
    private String mobile_number;
    private String website;
    private String email_id;

    public selectedServicesList() {
    }

    public selectedServicesList(int service, String title, double availability, double cost, double frequency, double reputation, double response, double success_rate, String mobile_number, String website, String email_id) {
        this.service = service;
        this.title = title;
        this.availability = availability;
        this.cost = cost;
        this.frequency = frequency;
        this.reputation = reputation;
        this.response = response;
        this.success_rate = success_rate;
        this.mobile_number = mobile_number;
        this.website = website;
        this.email_id = email_id;
    }

    public selectedServicesList(serviceStore a) {
        this.service = a.getService();
        this.title = a.getTitle();
        this.availability = a.getAvailability();
        this.cost = a.getCost();
        this.frequency = a.getFrequency();
        this.reputation = a.getReputation();
        this.response = a.getResponse();
        this.success_rate = a.getSuccess_rate();
        this.mobile_number = a.getMobile_number();
        this.website = a.getWebsite();
        this.email_id = a.getEmail_id();
    }



    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getTitle() {
        return "\n" + title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public double getResponse() {
        return response;
    }

    public void setResponse(double response) {
        this.response = response;
    }

    public double getSuccess_rate() {
        return success_rate;
    }

    public void setSuccess_rate(double success_rate) {
        this.success_rate = success_rate;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }


}
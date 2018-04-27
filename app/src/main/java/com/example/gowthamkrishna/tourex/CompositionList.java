package com.example.gowthamkrishna.tourex;


public class CompositionList {
    private String composition;
    private String title;

    public CompositionList() {
    }

    public CompositionList(String composition, String title) {
        this.composition = composition;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}

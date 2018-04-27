package com.example.gowthamkrishna.tourex;

public class InputQosValues {
    private String parameter, value;

    public InputQosValues() {
    }

    public InputQosValues(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public String getparameter() {
        return parameter;
    }

    public void setparameter(String name) {
        this.parameter = name;
    }

    public String getvalue() {
        return value;
    }

    public void setvalue(String value) {
        this.value = value;
    }
}

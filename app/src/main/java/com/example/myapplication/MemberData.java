package com.example.myapplication;

public class MemberData {
    private String name;
    private String color;

    public MemberData(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // Add an empty constructor so we can later parse JSON into MemberData using FireStore
    public MemberData() {
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}

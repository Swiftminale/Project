package com.example.first_project.model;

public enum Gender {
    MALE("name"),
    FEMALE("name");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.example.baitap4_intent;

public class Student {
    private int id;
    private String name;
    private String email;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getter và Setter
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
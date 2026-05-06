package com.collabconnect.model;

import java.util.List;

public class User {
    private String name;
    private String role;
    private List<String> skills;
    private String availability;
    private String email;

    public User(String name, String role, List<String> skills, String availability, String email) {
        this.name = name;
        this.role = role;
        this.skills = skills;
        this.availability = availability;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public List<String> getSkills() { return skills; }
    public String getAvailability() { return availability; }
    public String getEmail() { return email; }
    
    public void setAvailability(String availability) { this.availability = availability; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}

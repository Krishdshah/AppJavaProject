package com.collabconnect.model;

import java.util.List;

public class Project {
    private String title;
    private String description;
    private String status;
    private List<String> requiredSkills;
    private List<String> teamMembers;

    public Project(String title, String description, String status, List<String> requiredSkills, List<String> teamMembers) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.requiredSkills = requiredSkills;
        this.teamMembers = teamMembers;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public List<String> getRequiredSkills() { return requiredSkills; }
    public List<String> getTeamMembers() { return teamMembers; }
}

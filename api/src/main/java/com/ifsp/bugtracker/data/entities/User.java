package com.ifsp.bugtracker.data.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")
public class User extends BaseEntity {
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String pictureUrl;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Issue> issues;

    private User() {}

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.createdBy = "System-admin";
        this.createdAt = LocalDateTime.now();
        this.issues = null;
        this.name = name;
        this.email = email;
        this.pictureUrl =  "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void getPictureUrl(String url) {
        this.pictureUrl = url;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }
}

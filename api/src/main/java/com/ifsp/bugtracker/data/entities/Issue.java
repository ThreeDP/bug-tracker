package com.ifsp.bugtracker.data.entities;

import java.util.UUID;

import com.ifsp.bugtracker.data.enums.IssueStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Issue extends BaseEntity {
    @Column(nullable = false) 
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Issue(){}

    public Issue(
        String title,
        String description,
        User user) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.user = user;
        this.status = IssueStatus.New;
        this.createdBy = user.id.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatus getIssueStatus() {
        return this.status;
    }

    public void setIssueStatus(IssueStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

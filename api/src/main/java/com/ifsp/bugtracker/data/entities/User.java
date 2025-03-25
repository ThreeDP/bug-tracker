package com.ifsp.bugtracker.data.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifsp.bugtracker.data.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")

public class User extends BaseEntity implements UserDetails {
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(columnDefinition = "TEXT")
    private String pictureUrl;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Issue> issues;

    private User() {}

    public User(String name, String email, String password, UserRole role) {
        this.id = UUID.randomUUID();
        this.createdBy = "System-admin";
        this.createdAt = LocalDateTime.now();
        this.issues = null;
        this.name = name;
        this.email = email;
        this.pictureUrl =  "";
        this.password = password;
        this.role = role;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MODERATOR"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        if (this.role == UserRole.ANALYST) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_MODERATOR"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pass) {
        password = pass;
    }
}

package com.ifsp.bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsp.bugtracker.data.entities.Issue;
import java.util.UUID;

public interface IssueRepository extends JpaRepository<Issue, UUID> {

}

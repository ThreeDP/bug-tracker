package com.ifsp.bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifsp.bugtracker.data.entities.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}

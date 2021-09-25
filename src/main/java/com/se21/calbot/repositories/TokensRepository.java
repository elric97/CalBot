package com.se21.calbot.repositories;

import com.se21.calbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends JpaRepository<User, String> {
}

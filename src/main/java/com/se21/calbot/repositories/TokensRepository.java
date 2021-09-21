package com.se21.calbot.repositories;

import com.se21.calbot.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends MongoRepository<Token, String> {
}

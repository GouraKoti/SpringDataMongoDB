package com.molcon.henkelMongodb.repo;

import com.molcon.henkelMongodb.model.Henkel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HenkelRepo extends MongoRepository<Henkel, String> {
}

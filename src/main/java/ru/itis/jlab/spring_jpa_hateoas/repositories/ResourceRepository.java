package ru.itis.jlab.spring_jpa_hateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.jlab.spring_jpa_hateoas.models.Resource;

public interface ResourceRepository extends MongoRepository<Resource, ObjectId> {
}

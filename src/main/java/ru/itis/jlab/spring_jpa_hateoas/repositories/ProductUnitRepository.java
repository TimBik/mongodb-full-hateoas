package ru.itis.jlab.spring_jpa_hateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.jlab.spring_jpa_hateoas.models.ProductUnit;

import java.util.Optional;

public interface ProductUnitRepository extends MongoRepository<ProductUnit, ObjectId> {
    Optional<ProductUnit> findByCountLessThanAndCostGreaterThanAndDiscount(Integer count, Double cost, Double discount);
}

package ru.itis.jlab.spring_jpa_hateoas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.jlab.spring_jpa_hateoas.models.Product;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    @RestResource(path = "availableByTagSizeTagsContains", rel = "availableTagsSearch",exported = false)
    @Query("{ tags: { $size: ?0 , $in: ?1}, status: 'AVAILABLE' }")
    List<Product> findAllByTagsSizeAndTagsContains(int count, List<String> tags);
}

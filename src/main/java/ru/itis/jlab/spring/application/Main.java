package ru.itis.jlab.spring.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.itis.jlab.spring.models.Product;
import ru.itis.jlab.spring.models.Status;
import ru.itis.jlab.spring.models.Type;

import java.util.Collections;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);
        List<String> tagList = List.of("kovric", "mobile");

        Product product = Product.builder()
                .naming("kovrick")
                .tags(tagList)
                .state(Status.UNAVAILABLE)
                .type(Type.TECHNIQUE)
                .minPrice(50000d)
                .producer("citilink")
                .build();
        //insert opertaion
        template.save(product, "products");
        List<Product> products = template.find(new Query(
                where("minPrice").lt(60000d)
                        .orOperator(where("tags").is(Collections.emptyList()),
                                where("state").is(Status.UNAVAILABLE))), Product.class, "products");
        System.out.println(products);
        //update operation
        template.upsert(new Query(where("tags").is(tagList)), new Update().set("state", Status.AVAILABLE).set("minCount", 125).set("minPrice", 70000d), "products");

        System.out.println(template.find(new Query(
                where("minPrice").gt(50000d)
                        .orOperator(where("tags").is(Collections.emptyList()),
                                where("state").is(Status.AVAILABLE))), Product.class, "products"));
        //remove operation
        template.remove(new Query(where("producer").is("citilink")), "products");
    }
}
package ru.itis.jlab.driver.application;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ru.itis.jlab.driver.models.Status;
import ru.itis.jlab.driver.models.Type;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("shop");
        MongoCollection<Document> collection = database.getCollection("products");
        CodecRegistry pojoCodecRegistry =
                fromRegistries(getDefaultCodecRegistry(),
                        CodecRegistries.fromRegistries(
                                CodecRegistries.fromProviders(new EnumCodecProvider())));
        collection = collection.withCodecRegistry(pojoCodecRegistry);

        //creation of document with properties - just uncomment to use
        List<Object> tags = List.of("kovric", "mobile");
        Document document = new Document()
                .append("naming", "kivrik")
                .append("tags", tags)
                .append("state", Status.UNAVAILABLE)
                .append("type", Type.TECHNIQUE)
                .append("minPrice", 50000.32d)
                .append("producer", "citilink");
        collection.insertOne(document);

        Document searchQuery = new Document()
                .append("state", "available")
                .append("$or", Arrays.asList(
                        new Document("tags", tags),
                        new Document("minPrice", new Document("$lt", 400.0d))));
        for (Document currentDocument : collection.find(searchQuery)) {
            System.out.println("First search result_______________________________________________________________________________________________________________-");
            System.out.println(currentDocument.toJson());
        }

        collection.updateOne(eq("producer", "citilink"),
                new Document("$set", new Document("state", Status.AVAILABLE).append("naming", "new kovrick")));


        for (Document currentDocument : collection.find(or(new Document("state", Status.UNAVAILABLE),
                gt("minPrice", 10000d),
                new Document("tags", tags))).projection(fields(include("producer", "minPrice", "type", "naming"), excludeId()))) {
            System.out.println("Second search result______________________________");
            System.out.println(currentDocument.toJson());
        }


        collection.deleteOne(new Document("naming", "new kovrick"));


        collection.find().projection(new Document("naming", 1).append("_id", 0)).forEach(System.out::println);


    }
}

package ru.itis.jlab.driver.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Resource {
    private ObjectId objectId;
    private String link;
    private ResourceType resourceType;
}

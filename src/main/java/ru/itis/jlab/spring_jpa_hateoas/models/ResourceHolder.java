package ru.itis.jlab.spring_jpa_hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "resource_holder")
public class ResourceHolder {
    @Id
    private ObjectId objectId;
    private List<Resource> listOfResources;
}

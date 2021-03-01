package ru.itis.jlab.spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResourceListHolder {
    private ObjectId objectId;
    private List<Resource> listOfResources;
}

package ru.itis.jlab.driver.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class Product {
    private ObjectId objectId;
    private String naming;
    private List<String> tags;
    @JsonSerialize(using = ToStringSerializer.class)
    private Type type;
    @JsonSerialize(using = ToStringSerializer.class)
    private Status state;

    private Double minPrice;
    private Byte minCount;
    private Double minWeight;
    //can be separate object
    private String producer;
    private ResourceListHolder resourceListHolder;
}

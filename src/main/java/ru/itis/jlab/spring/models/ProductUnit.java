package ru.itis.jlab.spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductUnit {
    private ObjectId objectId;
    private Product product;
    private Integer count;
    private Double cost;
    private Double discount;
}

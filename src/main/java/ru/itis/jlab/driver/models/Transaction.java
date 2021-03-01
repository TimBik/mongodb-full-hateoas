package ru.itis.jlab.driver.models;

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
public class Transaction {
    private ObjectId objectId;
    private List<ProductUnit> productList;
    private String shopName;
}

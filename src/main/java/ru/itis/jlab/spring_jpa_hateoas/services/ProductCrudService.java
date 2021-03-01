package ru.itis.jlab.spring_jpa_hateoas.services;

import ru.itis.jlab.spring_jpa_hateoas.models.Product;

public interface ProductCrudService {
    Product makeAvailable(String objectId);
}

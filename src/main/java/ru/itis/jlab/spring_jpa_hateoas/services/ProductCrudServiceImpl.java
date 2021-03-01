package ru.itis.jlab.spring_jpa_hateoas.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.itis.jlab.spring_jpa_hateoas.models.Product;
import ru.itis.jlab.spring_jpa_hateoas.models.Status;
import ru.itis.jlab.spring_jpa_hateoas.repositories.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductCrudServiceImpl implements ProductCrudService {
    private final ProductRepository productRepository;

    @Override
    public Product makeAvailable(String objectId) {
        ObjectId realObjectId = new ObjectId(objectId);
        Product product = productRepository
                .findById(realObjectId)
                .orElseThrow(() -> new IllegalArgumentException("Can't find requested entity"));
        if (product.getStatus() == Status.AVAILABLE)
            throw new UnsupportedOperationException("Can't make available already available product");
        product.setStatus(Status.AVAILABLE);
        return productRepository.save(product);

    }
}

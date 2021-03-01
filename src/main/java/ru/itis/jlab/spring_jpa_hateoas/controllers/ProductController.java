package ru.itis.jlab.spring_jpa_hateoas.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.jlab.spring_jpa_hateoas.services.ProductCrudService;

@RepositoryRestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductCrudService productCrudService;

    @PutMapping("/products/{product-id}/make-available")
    public ResponseEntity<?> makeAvailableCourse(@PathVariable("product-id") String objectId) {
        return ResponseEntity.ok(
                EntityModel.of(productCrudService.makeAvailable(objectId)));
    }
}

package ru.itis.jlab.spring_jpa_hateoas.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ru.itis.jlab.spring_jpa_hateoas.controllers.ProductController;
import ru.itis.jlab.spring_jpa_hateoas.models.Product;
import ru.itis.jlab.spring_jpa_hateoas.models.Status;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class CoursesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Product>> {

    private final RepositoryEntityLinks links;

    @Override
    public EntityModel<Product> process(EntityModel<Product> model) {
        Product product = model.getContent();
        if (product != null && product.getStatus() != Status.AVAILABLE) {
            model.add(WebMvcLinkBuilder.linkTo(methodOn(ProductController.class)
                    .makeAvailableCourse(product.getObjectId().toString())).withRel("make-available"));
        }

        if (product != null && product.getStatus() == Status.AVAILABLE) {
            model.add(links.linkToSearchResource(Product.class, LinkRelation.of("availableTagsSearch")));
        }
        return model;
    }

}
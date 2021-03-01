package ru.itis.jlab.spring_jpa_hateoas;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.jlab.spring_jpa_hateoas.models.*;
import ru.itis.jlab.spring_jpa_hateoas.repositories.ProductRepository;
import ru.itis.jlab.spring_jpa_hateoas.repositories.ProductUnitRepository;
import ru.itis.jlab.spring_jpa_hateoas.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MainDataSetConfiguratorAndDeleteRemoveExample {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MongodbFullHateoasQuerydslApplication.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        ProductUnitRepository productUnitRepository = context.getBean(ProductUnitRepository.class);
        TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);

        List<String> carTags = List.of("game", "car", "children");
        List<String> phoneTags = List.of("phone", "mobile","modern");

        Product carProduct = Product.builder()
                .naming("car")
                .tags(carTags)
                .type(Type.CLOTHES)
                .status(Status.AVAILABLE)
                .minPrice(50000.543d)
                .minCount((byte) 1)
                .producer("MSB")
                .build();

        Product phoneProduct = Product.builder()
                .naming("phone")
                .tags(phoneTags)
                .type(Type.TECHNIQUE)
                .status(Status.UNAVAILABLE)
                .minPrice(200000.453d)
                .minCount((byte) 2)
                .producer("SAMSUNG")
                .build();

        productRepository.saveAll(List.of(carProduct, phoneProduct));

        ProductUnit carBuyUnit = ProductUnit.builder()
                .product(carProduct)
                .cost(60000d)
                .count(2)
                .discount(0d)
                .build();

        ProductUnit phoneBuyUnit = ProductUnit.builder()
                .product(phoneProduct)
                .cost(210000d)
                .count(1)
                .discount(5000d)
                .build();

        ProductUnit phoneBuyUnitTestDataToReplaceAndDelete = ProductUnit.builder()
                .product(phoneProduct)
                .cost(45334230d)
                .count(30)
                .discount(0d)
                .build();



        productUnitRepository.saveAll(List.of(carBuyUnit, phoneBuyUnit, phoneBuyUnitTestDataToReplaceAndDelete));

        Transaction transaction = Transaction.builder()
                .productList(List.of(carBuyUnit, phoneBuyUnit))
                .shopName("Mega")
                .date(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        ProductUnit productUnitInstance = productUnitRepository.findByCountLessThanAndCostGreaterThanAndDiscount(40, 220000d, 0d)
                .orElseThrow(()-> new IllegalStateException("Can't find existing lot"));

        productUnitInstance.setCost(800d);
        productUnitInstance.setDiscount(1d);

        productUnitRepository.save(productUnitInstance);

        ProductUnit productUnitInstanceFromDbAgain = productUnitRepository.findById(productUnitInstance.getObjectId())
                .orElseThrow(()-> new IllegalStateException("Can't find existing lot"));

        System.out.println(productUnitInstance);

        System.out.println(productUnitRepository.findAll().size());

        productUnitRepository.delete(productUnitInstanceFromDbAgain);
        System.out.println(productUnitRepository.findAll().size());

    }

}

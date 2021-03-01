package ru.itis.jlab.spring_jpa_hateoas.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import ru.itis.jlab.driver.application.EnumCodecProvider;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@EnableMongoRepositories(basePackages = "ru.itis.kpfu.simononboard.mongodb.ru.itis.jlab.spring_jpa_hateoas.repositories")
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    private final Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(environment.getProperty("spring.data.mongodb.uri"));
        CodecRegistry pojoCodecRegistry =
                fromRegistries(getDefaultCodecRegistry(),
                        CodecRegistries.fromRegistries(
                                CodecRegistries.fromProviders(new EnumCodecProvider())));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton(environment.getProperty("models.package.name"));
    }
}
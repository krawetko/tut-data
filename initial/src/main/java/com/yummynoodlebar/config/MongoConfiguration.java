package com.yummynoodlebar.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.yummynoodlebar.persistence.repository.MenuItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

/**
 * Created by krawetko on 28/08/14.
 */
@EnableMongoRepositories(value = "com.yummynoodlebar.persistence.repository", includeFilters = @ComponentScan.Filter(value = MenuItemRepository.class, type = ASSIGNABLE_TYPE))
@Configuration
public class MongoConfiguration {

    public
    @Bean
    MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
        return new MongoTemplate(mongo, "yummynoodle");
    }

    public
    @Bean
    Mongo mongo() throws UnknownHostException {
        return new MongoClient("localhost");
    }
}

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb+srv://admin:admin@cluster0.udh9u.mongodb.net/khalildiag?retryWrites=true&w=majority"));
    }


//    @Bean
//    public MongoTemplate mongoTemplate() {
//        String uri = "mongodb://yourUsername:yourPassword@57.129.70.159:27017/khalildiag?authSource=admin";
//        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
//    }
}

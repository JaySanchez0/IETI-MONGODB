package eci.ieti;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class Config {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://test:test@cluster0.2xxny.mongodb.net/TodoDB?authSource=admin&replicaSet=atlas-8zzq0v-shard-0&readPreference=primary&appname=MongoDB%20Compass&ssl=true");

        MongoClient mongoClient = new MongoClient(uri);

        return new SimpleMongoDbFactory( mongoClient, "TodoDB");

    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        return mongoTemplate;

    }

}
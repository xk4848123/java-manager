package com.nidecai.managerndc.common.managerinit;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

import lombok.Data;

@Data
public abstract class AbstractMongoConfig {


    private String uri;

    /*
     * Method that creates MongoDbFactory Common to both of the MongoDb
     * connections
     */
    public MongoDbFactory mongoDbFactory() {

        return new SimpleMongoDbFactory(new MongoClientURI(uri));
    }

    /*
     * Factory method to create the MongoTemplate
     */
    abstract public MongoTemplate getMongoTemplate();

}

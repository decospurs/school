package com.schoolapp.attendance.school.config;


//import com.google.common.annotations.VisibleForTesting;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Configuration
public class MongoDBConfiguration extends AbstractMongoConfiguration {

   // @VisibleForTesting
    @Value("${database.name:quabbly}")
    private String database;

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private int port;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }


}

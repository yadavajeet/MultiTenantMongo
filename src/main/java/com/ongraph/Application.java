package com.ongraph;

import com.ongraph.config.MongoDBCredentials;
import com.ongraph.config.MongoTenantTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity
@SpringBootApplication
@ComponentScan(basePackages = { "com.ongraph"})
public class Application {

	@Autowired
	public MongoDBCredentials mongoDBCredentials;

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTenantTemplate(
				new SimpleMongoDbFactory(new MongoClient(new MongoClientURI(mongoDBCredentials.getUri())),
						mongoDBCredentials.getDefaultDatabaseName()));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

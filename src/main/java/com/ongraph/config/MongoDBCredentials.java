package com.ongraph.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="mongodb")
public class MongoDBCredentials {

	private String uri;
	
	private String defaultDatabaseName;

	private String pepsicoDatabaseName;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDefaultDatabaseName() {
		return defaultDatabaseName;
	}

	public void setDefaultDatabaseName(String defaultDatabaseName) {
		this.defaultDatabaseName = defaultDatabaseName;
	}

	public String getPepsicoDatabaseName() {
		return pepsicoDatabaseName;
	}

	public void setPepsicoDatabaseName(String pepsicoDatabaseName) {
		this.pepsicoDatabaseName = pepsicoDatabaseName;
	}
}

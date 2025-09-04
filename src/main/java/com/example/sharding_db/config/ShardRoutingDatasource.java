package com.example.sharding_db.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ShardRoutingDatasource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	public static void setCurrentShard(String shardKey) {
		contextHolder.set(shardKey);
	}

	public static void clear() {
		contextHolder.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return contextHolder.get();
	}
}

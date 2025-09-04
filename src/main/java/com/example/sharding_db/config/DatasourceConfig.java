package com.example.sharding_db.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatasourceConfig {

	@Bean
	public DataSource dataSource(){
		ShardRoutingDatasource shardRoutingDatasource = new ShardRoutingDatasource();

		DataSource shard1 = DataSourceBuilder.create()
				.url("jdbc:mysql://localhost:3306/sharding1_db")
				.username("root")
				.password("adm1234")
				.build();

		DataSource shard2 = DataSourceBuilder.create()
				.url("jdbc:mysql://localhost:3306/sharding2_db")
				.username("root")
				.password("adm1234")
				.build();

		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("shard1", shard1);
		targetDataSources.put("shard2", shard2);


		shardRoutingDatasource.setTargetDataSources(targetDataSources);
		shardRoutingDatasource.setDefaultTargetDataSource(shard1);
		shardRoutingDatasource.afterPropertiesSet();
		return shardRoutingDatasource;

	}

}

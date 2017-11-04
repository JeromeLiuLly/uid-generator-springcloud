package com.candao.dms.jpa.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author JeromeLiu 多数据源配置
 *
 */
@Configuration
public class DataSourceConfig {

	/**
	 * 主数据源(写节点)
	 * 
	 * @return
	 */
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 从数据源(读节点)
	 * 
	 * @return
	 */
	@Bean(name = "secondaryDataSource")
	@Qualifier("secondaryDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
}
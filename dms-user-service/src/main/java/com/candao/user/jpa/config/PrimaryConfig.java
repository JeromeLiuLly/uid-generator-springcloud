package com.candao.user.jpa.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.candao.dms.framework.jpa.factory.BaseRepositoryFactoryBean;


/**
 * @author JeromeLiu
 * 设置Repository所在位置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, transactionManagerRef = "transactionManagerPrimary", basePackages = {"com.candao.user.jpa.primary"})
public class PrimaryConfig {

	@Autowired
	@Qualifier("primaryDataSource")
	private DataSource primaryDataSource;
	
	@Autowired
	private JpaProperties jpaProperties;

	@Primary
	@Bean(name = "entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}

	@Primary
	@Bean(name = "entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(primaryDataSource).properties(getVendorProperties(primaryDataSource))
				.packages("com.candao.user.jpa.bean") // 设置实体类所在位置
				.persistenceUnit("primaryPersistenceUnit").build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Primary
	@Bean(name = "transactionManagerPrimary")
	public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
	}

}
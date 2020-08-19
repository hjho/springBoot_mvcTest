package com.example.back.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import com.github.miemiedev.mybatis.paginator.dialect.OracleDialect;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.yml")
public class DatabaseConfiguration {

	// Context객체.
	@Autowired
	private ApplicationContext applicationContext; 
	//application.yml에서 spring.datasource.hikari에 대한 설명 읽기 
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	//application.yml에서 mybatis.configuration에 대한 설명 읽기
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
	// Hikari config설정으로 DataSource객체 생성.
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	// Mybatis Paginator 플러그인 객체
	@Bean
	public OffsetLimitInterceptor interceptor() {
		OffsetLimitInterceptor interceptor = new OffsetLimitInterceptor();
		interceptor.setDialect(new OracleDialect());
		return interceptor;
	}
	// HikariConfig, MyBatisConfig, Mapper.xml, ModelAliases, PlugIn을 포함하여 
	// SqlSessionFactory 객체 생성
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.example.back.model");
		sqlSessionFactoryBean.setPlugins(interceptor());
		return sqlSessionFactoryBean.getObject();
	}
	// SqlSessionFactory로 SqlSessionTemplate 인스턴스화.
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory); 
		return sessionTemplate;
	}
	
}

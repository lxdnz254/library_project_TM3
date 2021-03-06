package com.lxdnz.bit794.tm3.library_project.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.lxdnz.bit794.tm3.library_project.system.model"})
@EnableJpaRepositories(basePackages = {"com.lxdnz.bit794.tm3.library_project.database.repositorys"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}

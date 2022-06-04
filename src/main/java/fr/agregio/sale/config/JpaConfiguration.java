package fr.agregio.sale.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "fr.agregio.sale.infra")
@EnableJpaRepositories(basePackages = "fr.agregio.sale.infra")
public class JpaConfiguration {
}

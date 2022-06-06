package fr.agregio.sale.config;

import fr.agregio.sale.domain.ports.api.ParkCreationApi;
import fr.agregio.sale.domain.ports.spi.ParkCreationSpi;
import fr.agregio.sale.domain.services.ParkCreationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public ParkCreationApi productionParkCreationService(ParkCreationSpi parkCreationSpi) {
    return new ParkCreationService(parkCreationSpi);
  }
}

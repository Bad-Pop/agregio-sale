package fr.agregio.sale.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI agregioSaleOpenApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Agregio Sale Open API Documentation")
                .description(
                    "REST API used to manage sales for day slots by parks and markets segmentation")
                .version("v1.0.0-SNAPSHOT"))
        .addServersItem(new Server().url("http://localhost:8080/").description("LOCAL"));
  }
}

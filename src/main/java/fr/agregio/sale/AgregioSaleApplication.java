package fr.agregio.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"fr.agregio.sale"})
public class AgregioSaleApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgregioSaleApplication.class, args);
  }
}

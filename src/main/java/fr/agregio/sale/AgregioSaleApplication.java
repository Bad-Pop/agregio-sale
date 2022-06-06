package fr.agregio.sale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"fr.agregio.sale"})
public class AgregioSaleApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgregioSaleApplication.class, args);
  }
}

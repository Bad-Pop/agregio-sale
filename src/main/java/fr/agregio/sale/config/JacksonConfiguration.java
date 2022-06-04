package fr.agregio.sale.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.IGNORE_UNKNOWN;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@Configuration
public class JacksonConfiguration {

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new VavrModule())
        .registerModule(new JavaTimeModule())
        .configure(WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(WRITE_DATES_WITH_ZONE_ID, true)
        .configure(WRITE_ENUMS_USING_TO_STRING, true)
        .configure(IGNORE_UNKNOWN, true)
        .configure(FAIL_ON_NULL_FOR_PRIMITIVES, false);
  }
}

package fr.agregio.sale.client.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ProductionCapacityCreationDto {

  @JsonProperty(value = "capacity", required = true)
  double capacity;

  @JsonProperty(value = "day", required = true)
  LocalDate day;
}

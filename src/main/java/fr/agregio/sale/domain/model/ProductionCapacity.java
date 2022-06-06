package fr.agregio.sale.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProductionCapacity {
  double capacity;
  LocalDate day;
}

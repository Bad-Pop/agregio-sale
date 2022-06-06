package fr.agregio.sale.client.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.agregio.sale.domain.model.DaySlot;
import fr.agregio.sale.domain.model.MarketSegment;
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
public class OfferCreationDto {

  @JsonProperty(value = "marketSegment", required = true)
  MarketSegment marketSegment;

  @JsonProperty(value = "day", required = true)
  LocalDate day;

  @JsonProperty(value = "daySlot", required = true)
  DaySlot daySlot;

  @JsonProperty(value = "minPrice", required = true)
  double minPrice;

  @JsonProperty(value = "quantity", required = true)
  double quantity;
}

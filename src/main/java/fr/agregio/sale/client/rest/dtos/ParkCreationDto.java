package fr.agregio.sale.client.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.agregio.sale.domain.model.ProductionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ParkCreationDto {

  @JsonProperty(value = "name", required = true)
  String name;

  @JsonProperty(value = "productionType", required = true)
  ProductionType productionType;

  @JsonProperty(value = "productionCapacities")
  List<ProductionCapacityCreationDto> productionCapacities;

  @JsonProperty(value = "offers")
  List<OfferCreationDto> offers;
}

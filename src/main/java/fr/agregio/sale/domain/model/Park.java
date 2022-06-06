package fr.agregio.sale.domain.model;

import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

import static io.vavr.API.Seq;

@Value
@Builder
public class Park {
  @Default UUID id = UUID.randomUUID();
  String name;
  ProductionType productionType;
  @With @Default Seq<ProductionCapacity> productionCapacities = Seq();
  @With @Default Seq<Offer> offers = Seq();
}

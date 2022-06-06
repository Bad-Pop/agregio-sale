package fr.agregio.sale.client.rest.mappers;

import fr.agregio.sale.client.rest.dtos.ParkCreationDto;
import fr.agregio.sale.domain.model.Park;
import io.vavr.collection.List;

import static io.vavr.API.List;
import static io.vavr.API.Option;

public interface ParkCreationDtoMapper {

  static Park toDomain(ParkCreationDto dto) {
    return Park.builder()
        .name(dto.getName())
        .productionType(dto.getProductionType())
        .productionCapacities(
            Option(dto.getProductionCapacities())
                .map(List::ofAll)
                .getOrElse(List())
                .map(ProductionCapacityCreationDtoMapper::toDomain))
        .offers(
            Option(dto.getOffers())
                .map(List::ofAll)
                .getOrElse(List())
                .map(OfferCreationDtoMapper::toDomain))
        .build();
  }
}

package fr.agregio.sale.client.rest.mappers;

import fr.agregio.sale.client.rest.dtos.ProductionCapacityCreationDto;
import fr.agregio.sale.domain.model.ProductionCapacity;

public interface ProductionCapacityCreationDtoMapper {
  static ProductionCapacity toDomain(ProductionCapacityCreationDto dto) {
    return ProductionCapacity.builder().capacity(dto.getCapacity()).day(dto.getDay()).build();
  }
}

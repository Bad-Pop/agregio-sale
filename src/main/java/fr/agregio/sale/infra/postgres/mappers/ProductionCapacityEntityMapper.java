package fr.agregio.sale.infra.postgres.mappers;

import fr.agregio.sale.domain.model.ProductionCapacity;
import fr.agregio.sale.infra.postgres.entities.ProductionCapacityEntity;

public interface ProductionCapacityEntityMapper {

  static ProductionCapacityEntity fromDomain(ProductionCapacity dom) {
    return ProductionCapacityEntity.builder().capacity(dom.getCapacity()).day(dom.getDay()).build();
  }

  static ProductionCapacity toDomain(ProductionCapacityEntity entity) {
    return ProductionCapacity.builder().capacity(entity.getCapacity()).day(entity.getDay()).build();
  }
}

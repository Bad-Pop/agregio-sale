package fr.agregio.sale.infra.postgres.mappers;

import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.infra.postgres.entities.OfferEntity;

public interface OfferEntityMapper {

  static OfferEntity fromDomain(Offer dom) {
    return OfferEntity.builder()
        .id(dom.getId())
        .marketSegment(dom.getMarketSegment())
        .day(dom.getDay())
        .daySlot(dom.getDaySlot())
        .minPrice(dom.getMinPrice())
        .quantity(dom.getQuantity())
        .build();
  }

  static Offer toDomain(OfferEntity entity) {
    return Offer.builder()
        .id(entity.getId())
        .marketSegment(entity.getMarketSegment())
        .day(entity.getDay())
        .daySlot(entity.getDaySlot())
        .minPrice(entity.getMinPrice())
        .quantity(entity.getQuantity())
        .build();
  }
}

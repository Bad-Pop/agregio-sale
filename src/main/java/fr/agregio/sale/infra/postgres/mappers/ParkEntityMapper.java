package fr.agregio.sale.infra.postgres.mappers;

import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.infra.postgres.entities.ParkEntity;
import io.vavr.collection.List;
import io.vavr.collection.Seq;

import java.util.ArrayList;

import static io.vavr.API.List;
import static io.vavr.API.Option;

public interface ParkEntityMapper {

  static ParkEntity fromDomain(Park dom) {
    return ParkEntity.builder()
        .id(dom.getId())
        .name(dom.getName())
        .productionType(dom.getProductionType())
        .productionCapacities(
            Option(dom.getProductionCapacities())
                .map(capacities -> capacities.map(ProductionCapacityEntityMapper::fromDomain))
                .map(Seq::asJavaMutable)
                .getOrElse(new ArrayList<>()))
        .offers(
            Option(dom.getOffers())
                .map(offers -> offers.map(OfferEntityMapper::fromDomain))
                .map(Seq::asJavaMutable)
                .getOrElse(new ArrayList<>()))
        .build();
  }

  static Park toDomain(ParkEntity entity) {
    return Park.builder()
        .id(entity.getId())
        .name(entity.getName())
        .productionType(entity.getProductionType())
        .productionCapacities(
            Option(entity.getProductionCapacities())
                .map(List::ofAll)
                .getOrElse(List())
                .map(ProductionCapacityEntityMapper::toDomain))
        .offers(
            Option(entity.getOffers())
                .map(List::ofAll)
                .getOrElse(List())
                .map(OfferEntityMapper::toDomain))
        .build();
  }
}

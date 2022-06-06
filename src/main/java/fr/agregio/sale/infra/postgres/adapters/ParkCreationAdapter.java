package fr.agregio.sale.infra.postgres.adapters;

import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.ports.spi.ParkCreationSpi;
import fr.agregio.sale.domain.validations.Error;
import fr.agregio.sale.infra.postgres.entities.ParkEntity;
import fr.agregio.sale.infra.postgres.mappers.ParkEntityMapper;
import fr.agregio.sale.infra.postgres.repositories.ParkRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.API.Try;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParkCreationAdapter implements ParkCreationSpi {

  private final ParkRepository repository;

  @Override
  @Transactional
  public Either<Error, Park> save(Park park) {
    return Try(() -> ParkEntityMapper.fromDomain(park))
        .mapTry(this::setParentForEachChild)
        .mapTry(repository::save)
        .toEither()
        .map(ParkEntityMapper::toDomain)
        .mapLeft(throwable -> Error.of(throwable.getMessage(), park));
  }

  private ParkEntity setParentForEachChild(ParkEntity entity) {
    entity.getOffers().forEach(offer -> offer.setProductionPark(entity));
    entity.getProductionCapacities().forEach(capacity -> capacity.setProductionPark(entity));
    return entity;
  }
}

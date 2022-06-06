package fr.agregio.sale.infra.postgres.adapters;

import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.domain.validations.Error;
import fr.agregio.sale.infra.postgres.entities.OfferEntity;
import fr.agregio.sale.infra.postgres.mappers.OfferEntityMapper;
import fr.agregio.sale.infra.postgres.repositories.OfferRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.API.Try;

@Component
@RequiredArgsConstructor
public class OfferSearchAdapter {

  private final OfferRepository repository;

  @Transactional(readOnly = true)
  public Either<Error, Page<Offer>> search(int page, int size, String query, String sort) {
    final var spec = JpaSpecificationBuilder.<OfferEntity>build(query, sort);
    final var pageRequest = PageRequest.of(page, size);

    return Try(() -> repository.findAll(spec, pageRequest))
        .mapTry(pageResult -> pageResult.map(OfferEntityMapper::toDomain))
        .toEither()
        .mapLeft(throwable -> Error.of(throwable.getMessage(), throwable));
  }
}

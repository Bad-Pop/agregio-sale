package fr.agregio.sale.infra.postgres.adapters;

import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.validations.Error;
import fr.agregio.sale.infra.postgres.entities.ParkEntity;
import fr.agregio.sale.infra.postgres.mappers.ParkEntityMapper;
import fr.agregio.sale.infra.postgres.repositories.ParkRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.API.Try;

@Component
@RequiredArgsConstructor
public class ParkSearchAdapter {

  private final ParkRepository repository;

  @Transactional(readOnly = true)
  public Either<Error, Page<Park>> search(int page, int size, String query, String sort) {
    final var spec = JpaSpecificationBuilder.<ParkEntity>build(query, sort);
    final var pageRequest = PageRequest.of(page, size);

    return Try(() -> repository.findAll(spec, pageRequest))
        .mapTry(pageResult -> pageResult.map(ParkEntityMapper::toDomain))
        .toEither()
        .mapLeft(throwable -> Error.of(throwable.getMessage(), throwable));
  }
}

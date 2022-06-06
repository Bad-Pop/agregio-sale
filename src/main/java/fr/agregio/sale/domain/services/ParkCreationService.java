package fr.agregio.sale.domain.services;

import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.ports.api.ParkCreationApi;
import fr.agregio.sale.domain.ports.spi.ParkCreationSpi;
import fr.agregio.sale.domain.validations.Error;
import fr.agregio.sale.domain.validations.ParkValidator;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ParkCreationService implements ParkCreationApi {

  private final ParkCreationSpi spi;

  @Override
  public Either<Error, Park> create(Park park) {
    return ParkValidator.validate(park)
        .toEither()
        .flatMap(spi::save)
        .peek(saved -> log.info("Successfully saved park named {}", saved.getName()))
        .peekLeft(
            error ->
                log.error(
                    "An error occurred while trying to save a new park... \n Error details : [{}]",
                    error));
  }
}

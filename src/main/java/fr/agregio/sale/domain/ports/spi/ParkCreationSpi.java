package fr.agregio.sale.domain.ports.spi;

import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.validations.Error;
import io.vavr.control.Either;

public interface ParkCreationSpi {
  Either<Error, Park> save(Park park);
}

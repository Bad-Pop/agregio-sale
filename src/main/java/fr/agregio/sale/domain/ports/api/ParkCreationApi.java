package fr.agregio.sale.domain.ports.api;

import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.validations.Error;
import io.vavr.control.Either;

public interface ParkCreationApi {
  Either<Error, Park> create(Park park);
}

package fr.agregio.sale.domain.validations;

import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.model.ProductionCapacity;
import fr.agregio.sale.domain.model.ProductionType;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;
import static io.vavr.control.Validation.combine;

public interface ParkValidator {

  static Validation<Error, Park> validate(Park park) {
    return combine(
            validateId(park.getId()),
            validateName(park.getName()),
            validateProductionType(park.getProductionType()),
            validateCapacities(park.getProductionCapacities()),
            validateOffers(park.getOffers()),
            validateOffersQtySumForDayToDayCapacities(park))
        .ap((uuid, s, type, capacities, offers, o) -> park)
        .mapError(errors -> Error.of(errors.mkString("\n"), park));
  }

  private static Validation<String, UUID> validateId(UUID id) {
    return id == null ? Invalid("Production park id cannot be null") : Valid(id);
  }

  private static Validation<String, String> validateName(String name) {
    return name == null || name.isBlank()
        ? Invalid("Park name cannot be null or blank")
        : Valid(name);
  }

  private static Validation<String, ProductionType> validateProductionType(ProductionType type) {
    return type != null ? Valid(type) : Invalid("Production type is required");
  }

  private static Validation<String, Seq<ProductionCapacity>> validateCapacities(
      Seq<ProductionCapacity> capacities) {

    return capacities != null && doesNotContainsMoreThantOneCapacityPerDay(capacities)
        ? Valid(capacities)
        : Invalid(
            "Cannot validate production park without production capacities or with more than 1 capacity for a day");
  }

  private static boolean doesNotContainsMoreThantOneCapacityPerDay(
      Seq<ProductionCapacity> capacities) {
    return capacities.size() == capacities.distinctBy(ProductionCapacity::getDay).size();
  }

  private static Validation<String, Seq<Offer>> validateOffers(Seq<Offer> offers) {
    return offers != null
        ? Valid(offers)
        : Invalid("Cannot validate production park with null offers collection");
  }

  /** Check for each day if a park offer quantity is not greater than his production capacity */
  private static Validation<String, Park> validateOffersQtySumForDayToDayCapacities(Park park) {

    if (park.getProductionCapacities() == null || park.getOffers() == null) {
      return Invalid("Production capacities and/or Offers collections cannot be null");
    }

    var daysWithToManyOffersQuantitiesSum = new ArrayList<LocalDate>();

    park.getProductionCapacities()
        .forEach(
            capacity -> {
              var day = capacity.getDay();
              var offersForDay = park.getOffers().filter(offer -> offer.getDay().equals(day));
              if (!offersForDay.isEmpty()) {
                var offersQuantitiesSum = offersForDay.map(Offer::getQuantity).reduce(Double::sum);
                if (capacity.getCapacity() < offersQuantitiesSum) {
                  daysWithToManyOffersQuantitiesSum.add(day);
                }
              }
            });

    final var invalidDays = List.ofAll(daysWithToManyOffersQuantitiesSum);
    return invalidDays.isEmpty()
        ? Valid(null)
        : Invalid(
            "Offers quantity sums exceed production capacities for these days : "
                + invalidDays.mkString(", "));
  }
}

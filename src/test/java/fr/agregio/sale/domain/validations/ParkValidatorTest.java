package fr.agregio.sale.domain.validations;

import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.model.ProductionCapacity;
import io.vavr.collection.Seq;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static fr.agregio.sale.domain.model.DaySlot.TWELVE_TO_FIFTEEN_HOUR;
import static fr.agregio.sale.domain.model.MarketSegment.PRIMARY_RESERVE;
import static fr.agregio.sale.domain.model.ProductionType.SOLAR;
import static io.vavr.API.Seq;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ParkValidatorTest {

  @ParameterizedTest
  @MethodSource("provideValidParks")
  void should_be_valid(Park validPark) {
    final var actual = ParkValidator.validate(validPark);
    assertThat(actual).containsValidSame(validPark);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidParks")
  void should_not_be_valid(Park invalidPark) {
    final var actual = ParkValidator.validate(invalidPark);
    assertThat(actual).containsInvalidInstanceOf(Error.class);
  }

  private static Stream<Arguments> provideValidParks() {
    final var validId = UUID.randomUUID();
    final var validName = "validName";
    final var validType = SOLAR;
    final Seq<ProductionCapacity> validEmptyCapacities = Seq();
    final Seq<Offer> validEmptyOffers = Seq();

    final var day = LocalDate.of(2022, 6, 5);
    final var capacity = ProductionCapacity.builder().day(day).capacity(50).build();
    final var offer =
        Offer.builder()
            .id(validId)
            .marketSegment(PRIMARY_RESERVE)
            .day(day)
            .daySlot(TWELVE_TO_FIFTEEN_HOUR)
            .minPrice(40)
            .quantity(50)
            .build();

    return Stream.of(
        Arguments.of(
            Park.builder()
                .id(validId)
                .name(validName)
                .productionType(validType)
                .productionCapacities(validEmptyCapacities)
                .offers(validEmptyOffers)
                .build()),
        Arguments.of(
            Park.builder()
                .id(validId)
                .name(validName)
                .productionType(validType)
                .productionCapacities(Seq(capacity))
                .offers(Seq(offer))
                .build()));
  }

  private static Stream<Arguments> provideInvalidParks() {
    final var day = LocalDate.of(2022, 6, 5);
    final var capacity = ProductionCapacity.builder().day(day).capacity(50).build();
    final var offer =
        Offer.builder()
            .marketSegment(PRIMARY_RESERVE)
            .day(day)
            .daySlot(TWELVE_TO_FIFTEEN_HOUR)
            .minPrice(40)
            .quantity(50)
            .build();

    return Stream.of(
        Arguments.of(
            Park.builder() // park with invalid fields
                .id(null)
                .name(null)
                .productionType(null)
                .build()),
        Arguments.of(Park.builder().build()), // also park with invalid fields
        Arguments.of(
            Park.builder() // park with valid fields but invalid capacities and valid offers
                .id(UUID.randomUUID())
                .name("name")
                .productionType(SOLAR)
                .productionCapacities(Seq(capacity, capacity))
                .offers(Seq(offer))
                .build()),
        Arguments.of(
            Park.builder() // park with valid fields and capacities but invalid offers
                .id(UUID.randomUUID())
                .name("name")
                .productionType(SOLAR)
                .productionCapacities(Seq(capacity))
                .offers(Seq(offer, offer))
                .build()));
  }
}

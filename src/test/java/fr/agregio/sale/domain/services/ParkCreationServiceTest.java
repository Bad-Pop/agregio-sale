package fr.agregio.sale.domain.services;

import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.model.ProductionCapacity;
import fr.agregio.sale.domain.ports.spi.ParkCreationSpi;
import fr.agregio.sale.domain.validations.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static fr.agregio.sale.domain.model.DaySlot.NINE_TO_TWELVE;
import static fr.agregio.sale.domain.model.MarketSegment.PRIMARY_RESERVE;
import static fr.agregio.sale.domain.model.ProductionType.SOLAR;
import static io.vavr.API.*;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkCreationServiceTest {

  @InjectMocks private ParkCreationService service;

  @Mock private ParkCreationSpi spi;

  @Test
  void should_validate_and_create_new_park() {
    final var day = LocalDate.of(2022, 6, 5);
    final var given =
        Park.builder()
            .name("name")
            .productionType(SOLAR)
            .productionCapacities(Seq(ProductionCapacity.builder().capacity(40).day(day).build()))
            .offers(
                Seq(
                    Offer.builder()
                        .marketSegment(PRIMARY_RESERVE)
                        .day(day)
                        .daySlot(NINE_TO_TWELVE)
                        .minPrice(50.12)
                        .quantity(25)
                        .build()))
            .build();

    when(spi.save(given)).thenReturn(Right(given));

    final var actual = service.create(given);

    assertThat(actual).containsRightSame(given);
    verify(spi).save(given);
    verifyNoMoreInteractions(spi);
  }

  @Test
  void should_not_create_park_if_validation_failed() {
    final var day = LocalDate.of(2022, 6, 5);
    final var given =
        Park.builder()
            .name(null)
            .productionType(null)
            .productionCapacities(
                Seq(
                    ProductionCapacity.builder().capacity(40).day(day).build(),
                    ProductionCapacity.builder().capacity(40).day(day).build()))
            .build();

    final var actual = service.create(given);

    assertThat(actual).containsLeftInstanceOf(Error.class);
    verifyNoInteractions(spi);
  }

  @Test
  void should_validate_but_dont_create_new_park_on_spi_error() {
    final var day = LocalDate.of(2022, 6, 5);
    final var given =
        Park.builder()
            .name("name")
            .productionType(SOLAR)
            .productionCapacities(Seq(ProductionCapacity.builder().capacity(40).day(day).build()))
            .offers(
                Seq(
                    Offer.builder()
                        .marketSegment(PRIMARY_RESERVE)
                        .day(day)
                        .daySlot(NINE_TO_TWELVE)
                        .minPrice(50.12)
                        .quantity(25)
                        .build()))
            .build();

    when(spi.save(given)).thenReturn(Left(Error.of("message", null)));

    final var actual = service.create(given);

    assertThat(actual).containsLeftInstanceOf(Error.class);
    verify(spi).save(given);
    verifyNoMoreInteractions(spi);
  }
}

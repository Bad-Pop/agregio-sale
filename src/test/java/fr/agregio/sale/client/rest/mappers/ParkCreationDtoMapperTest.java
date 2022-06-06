package fr.agregio.sale.client.rest.mappers;

import fr.agregio.sale.client.rest.dtos.OfferCreationDto;
import fr.agregio.sale.client.rest.dtos.ParkCreationDto;
import fr.agregio.sale.client.rest.dtos.ProductionCapacityCreationDto;
import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.model.ProductionCapacity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static fr.agregio.sale.domain.model.DaySlot.NINE_TO_TWELVE;
import static fr.agregio.sale.domain.model.MarketSegment.PRIMARY_RESERVE;
import static fr.agregio.sale.domain.model.ProductionType.SOLAR;
import static io.vavr.API.Seq;
import static org.assertj.core.api.Assertions.assertThat;

class ParkCreationDtoMapperTest {

  @Test
  void should_map() {
    final var day = LocalDate.of(2022, 6, 5);
    final var given =
        ParkCreationDto.builder()
            .name("name")
            .productionType(SOLAR)
            .productionCapacities(
                java.util.List.of(
                    ProductionCapacityCreationDto.builder().capacity(40).day(day).build()))
            .offers(
                java.util.List.of(
                    OfferCreationDto.builder()
                        .marketSegment(PRIMARY_RESERVE)
                        .day(day)
                        .daySlot(NINE_TO_TWELVE)
                        .minPrice(50.12)
                        .quantity(25)
                        .build()))
            .build();

    final var actual = ParkCreationDtoMapper.toDomain(given);

    assertThat(actual)
        .usingRecursiveComparison()
        .ignoringFields("id", "offers.id")
        .isEqualTo(
            Park.builder()
                .name("name")
                .productionType(SOLAR)
                .productionCapacities(
                    Seq(ProductionCapacity.builder().capacity(40).day(day).build()))
                .offers(
                    Seq(
                        Offer.builder()
                            .marketSegment(PRIMARY_RESERVE)
                            .day(day)
                            .daySlot(NINE_TO_TWELVE)
                            .minPrice(50.12)
                            .quantity(25)
                            .build()))
                .build());
    assertThat(actual.getId()).isNotNull();
  }
}

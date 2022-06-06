package fr.agregio.sale.client.rest.mappers;

import fr.agregio.sale.client.rest.dtos.OfferCreationDto;
import fr.agregio.sale.domain.model.Offer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static fr.agregio.sale.domain.model.DaySlot.NINE_TO_TWELVE;
import static fr.agregio.sale.domain.model.MarketSegment.PRIMARY_RESERVE;
import static org.assertj.core.api.Assertions.assertThat;

class OfferCreationDtoMapperTest {

  @Test
  void should_map() {
    final var given =
        OfferCreationDto.builder()
            .marketSegment(PRIMARY_RESERVE)
            .day(LocalDate.of(2022, 6, 5))
            .daySlot(NINE_TO_TWELVE)
            .minPrice(50.12)
            .quantity(25)
            .build();

    final var actual = OfferCreationDtoMapper.toDomain(given);

    assertThat(actual)
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(
            Offer.builder()
                .marketSegment(PRIMARY_RESERVE)
                .day(LocalDate.of(2022, 6, 5))
                .daySlot(NINE_TO_TWELVE)
                .minPrice(50.12)
                .quantity(25)
                .build());
    assertThat(actual.getId()).isNotNull();
  }
}

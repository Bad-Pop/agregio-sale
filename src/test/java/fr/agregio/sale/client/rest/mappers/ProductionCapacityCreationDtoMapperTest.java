package fr.agregio.sale.client.rest.mappers;

import fr.agregio.sale.client.rest.dtos.ProductionCapacityCreationDto;
import fr.agregio.sale.domain.model.ProductionCapacity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ProductionCapacityCreationDtoMapperTest {

  @Test
  void should_map() {
    final var day = LocalDate.of(2022, 6, 5);
    final var given = ProductionCapacityCreationDto.builder().capacity(50).day(day).build();

    final var actual = ProductionCapacityCreationDtoMapper.toDomain(given);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(ProductionCapacity.builder().capacity(50).day(day).build());
  }
}

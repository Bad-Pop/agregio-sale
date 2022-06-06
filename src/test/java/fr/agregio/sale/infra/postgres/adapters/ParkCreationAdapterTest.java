package fr.agregio.sale.infra.postgres.adapters;

import fr.agregio.sale.domain.model.Offer;
import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.model.ProductionCapacity;
import fr.agregio.sale.domain.validations.Error;
import fr.agregio.sale.infra.postgres.entities.ParkEntity;
import fr.agregio.sale.infra.postgres.mappers.ParkEntityMapper;
import fr.agregio.sale.infra.postgres.repositories.ParkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static fr.agregio.sale.domain.model.DaySlot.NINE_TO_TWELVE;
import static fr.agregio.sale.domain.model.MarketSegment.PRIMARY_RESERVE;
import static fr.agregio.sale.domain.model.ProductionType.SOLAR;
import static io.vavr.API.Seq;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkCreationAdapterTest {

  @InjectMocks private ParkCreationAdapter adapter;
  @Mock private ParkRepository repository;

  @Test
  void should_save() {
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
    final var entity = ParkEntityMapper.fromDomain(given);

    when(repository.save(any(ParkEntity.class))).thenReturn(entity);

    final var actual = adapter.save(given);

    assertThat(actual).containsRightInstanceOf(Park.class);
    Assertions.assertThat(actual.get()).usingRecursiveComparison().isEqualTo(given);

    verify(repository).save(any(ParkEntity.class));
    verifyNoMoreInteractions(repository);
  }

  @Test
  void should_not_save() {
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
    final var repoException = new IllegalArgumentException();

    when(repository.save(any(ParkEntity.class))).thenThrow(repoException);

    final var actual = adapter.save(given);

    assertThat(actual).containsLeftInstanceOf(Error.class);

    verify(repository).save(any(ParkEntity.class));
    verifyNoMoreInteractions(repository);
  }
}

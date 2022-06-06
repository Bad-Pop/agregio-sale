package fr.agregio.sale.client.rest.resources;

import fr.agregio.sale.client.rest.dtos.OfferCreationDto;
import fr.agregio.sale.client.rest.dtos.ParkCreationDto;
import fr.agregio.sale.client.rest.dtos.ProductionCapacityCreationDto;
import fr.agregio.sale.client.rest.mappers.ParkCreationDtoMapper;
import fr.agregio.sale.domain.model.Park;
import fr.agregio.sale.domain.ports.api.ParkCreationApi;
import fr.agregio.sale.domain.validations.Error;
import fr.agregio.sale.infra.postgres.adapters.ParkSearchAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static fr.agregio.sale.client.rest.TestUtils.OBJECT_MAPPER;
import static fr.agregio.sale.domain.model.DaySlot.NINE_TO_TWELVE;
import static fr.agregio.sale.domain.model.MarketSegment.PRIMARY_RESERVE;
import static fr.agregio.sale.domain.model.ProductionType.SOLAR;
import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ParkResource.class})
class ParkResourceTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private ParkCreationApi parkCreationApi;
  @MockBean private ParkSearchAdapter searchAdapter;

  @Test
  void should_create_park() throws Exception {
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
    final var park = ParkCreationDtoMapper.toDomain(given);

    when(parkCreationApi.create(any(Park.class))).thenReturn(Right(park));

    mockMvc
        .perform(
            post("/parks")
                .content(OBJECT_MAPPER.writeValueAsString(given))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().string(OBJECT_MAPPER.writeValueAsString(park)));

    verify(parkCreationApi).create(any(Park.class));
    verifyNoMoreInteractions(parkCreationApi);
  }

  @Test
  void should_not_create_park() throws Exception {
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
    final var park = ParkCreationDtoMapper.toDomain(given);

    when(parkCreationApi.create(any(Park.class))).thenReturn(Left(Error.of("message", null)));

    mockMvc
        .perform(
            post("/parks")
                .content(OBJECT_MAPPER.writeValueAsString(given))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_JSON));

    verify(parkCreationApi).create(any(Park.class));
    verifyNoMoreInteractions(parkCreationApi);
  }
}

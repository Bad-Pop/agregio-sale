package fr.agregio.sale.client.rest.mappers;

import fr.agregio.sale.client.rest.dtos.OfferCreationDto;
import fr.agregio.sale.domain.model.Offer;

public interface OfferCreationDtoMapper {
    static Offer toDomain(OfferCreationDto dto) {
        return Offer.builder()
                .marketSegment(dto.getMarketSegment())
                .day(dto.getDay())
                .daySlot(dto.getDaySlot())
                .minPrice(dto.getMinPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}

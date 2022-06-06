package fr.agregio.sale.infra.postgres.entities;

import fr.agregio.sale.domain.model.DaySlot;
import fr.agregio.sale.domain.model.MarketSegment;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "offer",
    indexes = {@Index(columnList = "day", name = "offer_day_index")})
public class OfferEntity {

  @Id private UUID id;

  @Enumerated(STRING)
  private MarketSegment marketSegment;

  private LocalDate day;

  @Enumerated(STRING)
  private DaySlot daySlot;

  private double minPrice;
  private double quantity;

  @ManyToOne
  @JoinColumn(name = "park_id", insertable = false, updatable = false)
  private ParkEntity productionPark;
}

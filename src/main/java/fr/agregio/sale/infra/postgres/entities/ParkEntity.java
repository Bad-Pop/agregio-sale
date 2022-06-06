package fr.agregio.sale.infra.postgres.entities;

import fr.agregio.sale.domain.model.ProductionType;
import lombok.*;
import lombok.Builder.Default;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.NONE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "park")
public class ParkEntity {

  @Id private UUID id;
  private String name;

  @Enumerated(STRING)
  private ProductionType productionType;

  @Default
  @Setter(NONE)
  @JoinColumn(name = "park_id")
  @OneToMany(cascade = ALL, orphanRemoval = true)
  private List<ProductionCapacityEntity> productionCapacities = new ArrayList<>();

  @Default
  @Setter(NONE)
  @JoinColumn(name = "park_id")
  @OneToMany(cascade = ALL, orphanRemoval = true)
  private List<OfferEntity> offers = new ArrayList<>();
}

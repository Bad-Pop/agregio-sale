package fr.agregio.sale.infra.postgres.repositories;

import fr.agregio.sale.infra.postgres.control.ApplicationRepository;
import fr.agregio.sale.infra.postgres.entities.OfferEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferRepository extends ApplicationRepository<OfferEntity, UUID> {}

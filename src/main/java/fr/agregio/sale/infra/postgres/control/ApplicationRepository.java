package fr.agregio.sale.infra.postgres.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationRepository<T, ID>
    extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {}

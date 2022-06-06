package fr.agregio.sale.infra.postgres.adapters;

import io.github.perplexhub.rsql.RSQLJPASupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import static io.vavr.control.Option.when;

public interface JpaSpecificationBuilder {
  static <T> Specification<T> build(String query, String sort) {
    return when(
            StringUtils.isNotBlank(query),
            RSQLJPASupport.<T>toSpecification(query).and(RSQLJPASupport.toSort(sort)))
        .getOrElse(RSQLJPASupport.toSort(sort));
  }
}

package fr.agregio.sale.domain.validations;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
  String message;
  Object invalidValue;
}

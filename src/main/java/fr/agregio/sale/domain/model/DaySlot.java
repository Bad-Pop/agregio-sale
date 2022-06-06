package fr.agregio.sale.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public enum DaySlot {
  MIDNIGHT_TO_THREE_HOUR(LocalTime.of(0, 0, 0, 1), LocalTime.of(3, 0)),
  THREE_TO_SIX_HOUR(LocalTime.of(3, 0, 0, 1), LocalTime.of(6, 0)),
  SIX_TO_NINE_HOUR(LocalTime.of(6, 0, 0, 1), LocalTime.of(9, 0)),
  NINE_TO_TWELVE(LocalTime.of(9, 0, 0, 1), LocalTime.of(12, 0)),
  TWELVE_TO_FIFTEEN_HOUR(LocalTime.of(12, 0, 0, 1), LocalTime.of(15, 0)),
  FIFTEEN_TO_EIGHTEEN_HOUR(LocalTime.of(15, 0, 0, 1), LocalTime.of(18, 0)),
  EIGHTEEN_TO_TWENTY_ONE_HOUR(LocalTime.of(18, 0, 0, 1), LocalTime.of(21, 0)),
  TWENTY_ONE_HOUR_TO_MIDNIGHT(LocalTime.of(21, 0, 0, 1), LocalTime.of(0, 0));

  private final LocalTime start;
  private final LocalTime end;
}

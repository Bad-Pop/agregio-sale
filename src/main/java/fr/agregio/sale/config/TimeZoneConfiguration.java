package fr.agregio.sale.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TimeZoneConfiguration {

  @Value("${time-zone:UTC}")
  private String defaultTimeZone;

  @PostConstruct
  void setDefaultTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
    log.info("Set application default time zone to {}", TimeZone.getDefault().getID());
  }
}

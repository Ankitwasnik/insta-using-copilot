package com.talentica.copilot.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeUtil {

  private DateTimeUtil() {
  }

  public static Instant convertToInstant(LocalDateTime localDateTime) {
    return localDateTime == null ? null : localDateTime.toInstant(ZoneOffset.UTC);
  }
}

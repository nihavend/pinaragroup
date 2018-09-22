package com.likya.myra.test.timeshift;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

// http://www.javapractices.com/topic/TopicAction.do?Id=234
/** 
 Increment by 1 second each time you look at the clock.
 Starts with the default system clock's instant and time-zone.
 
 Example output:
  2018-05-26T14:00:12.778Z
  2018-05-26T14:00:13.778Z
  2018-05-26T14:00:14.778Z
  2018-05-26T14:00:15.778Z
  2018-05-26T14:00:16.778Z
   
 @since Java 8.
*/
public final class ClockTicker extends Clock {
  
  /** Simple demo of the behaviour of this class. */
  public static void main(String... args) {
    ClockTicker ticker = new ClockTicker();
    log(ticker.instant());
    log(ticker.instant());
    log(ticker.instant());
    log(ticker.instant());
    log(ticker.instant());
  }
  private static void log(Object msg){
    System.out.println(Objects.toString(msg));
  }
  
  @Override public ZoneId getZone() {
    return DEFAULT_TZONE;
  }
  
  @Override public Clock withZone(ZoneId zone) {
    return Clock.fixed(WHEN_STARTED, zone);
  }
  
  @Override public Instant instant() {
    return nextInstant();
  }
  
  //PRIVATE 
  private final Instant WHEN_STARTED = Instant.now();
  private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
  private long count = 0;
  
  private Instant nextInstant() {
    ++count;
    return WHEN_STARTED.plusSeconds(count);
  }
} 
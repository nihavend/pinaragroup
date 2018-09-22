package com.likya.myra.test.timeshift.plugin;
public interface TimeSource {

  /** Return the system time. */  
  long currentTimeMillis();

} 
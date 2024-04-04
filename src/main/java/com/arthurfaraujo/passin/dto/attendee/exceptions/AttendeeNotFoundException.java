package com.arthurfaraujo.passin.dto.attendee.exceptions;

public class AttendeeNotFoundException extends RuntimeException {
  public AttendeeNotFoundException(String message) {
    super(message);
  }
}

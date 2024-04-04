package com.arthurfaraujo.passin.domain.event.exceptions;

public class EventAlreadyExistException extends RuntimeException {
  public EventAlreadyExistException(String message) {
    super(message);
  }
}

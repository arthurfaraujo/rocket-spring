package com.arthurfaraujo.passin.domain.checkin.exceptions;

public class CheckinAlreadyExistException extends RuntimeException {
  public CheckinAlreadyExistException(String message) {
    super(message);
  }
}

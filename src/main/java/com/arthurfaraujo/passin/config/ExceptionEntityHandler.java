package com.arthurfaraujo.passin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arthurfaraujo.passin.domain.checkin.exceptions.CheckinAlreadyExistException;
import com.arthurfaraujo.passin.domain.event.exceptions.EventAlreadyExistException;
import com.arthurfaraujo.passin.domain.event.exceptions.EventFullException;
import com.arthurfaraujo.passin.domain.event.exceptions.EventNotFoundException;
import com.arthurfaraujo.passin.dto.attendee.exceptions.AttendeeAlreadyExistException;
import com.arthurfaraujo.passin.dto.attendee.exceptions.AttendeeNotFoundException;
import com.arthurfaraujo.passin.dto.generic.ExceptionResponseDTO;

@ControllerAdvice
public class ExceptionEntityHandler {

  @ExceptionHandler(EventNotFoundException.class)
  public ResponseEntity<Object> handleEventNotFound(EventNotFoundException exception) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(EventAlreadyExistException.class)
  public ResponseEntity<Object> handleEventAlreadyExist(EventAlreadyExistException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @ExceptionHandler(EventFullException.class)
  public ResponseEntity<ExceptionResponseDTO> handleEventFull(EventFullException exception) {
    return ResponseEntity.badRequest().body(new ExceptionResponseDTO(exception.getMessage()));
  }

  @ExceptionHandler(AttendeeAlreadyExistException.class)
  public ResponseEntity<Object> handleAttendeeAlreadyExist(AttendeeAlreadyExistException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @ExceptionHandler(AttendeeNotFoundException.class)
  public ResponseEntity<Object> handleAttendeeNotFound(AttendeeNotFoundException exception) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(CheckinAlreadyExistException.class)
  public ResponseEntity<Object> handleCheckinAlreadyExist(CheckinAlreadyExistException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }
}

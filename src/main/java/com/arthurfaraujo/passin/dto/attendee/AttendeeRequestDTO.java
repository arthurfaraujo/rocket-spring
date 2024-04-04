package com.arthurfaraujo.passin.dto.attendee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendeeRequestDTO {
  private String name;
  private String email;
  public String eventId;
}

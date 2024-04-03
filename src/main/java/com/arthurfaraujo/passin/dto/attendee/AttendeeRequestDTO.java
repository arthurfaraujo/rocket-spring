package com.arthurfaraujo.passin.dto.attendee;

import com.arthurfaraujo.passin.domain.event.Event;

public record AttendeeRequestDTO(String name, String email, Event event) {

}

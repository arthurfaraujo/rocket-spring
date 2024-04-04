package com.arthurfaraujo.passin.dto.attendee;

import java.time.LocalDateTime;

import com.arthurfaraujo.passin.domain.attendee.Attendee;

public record AttendeeResponseDTO(Attendee attendee, LocalDateTime checkedInAt) {

}

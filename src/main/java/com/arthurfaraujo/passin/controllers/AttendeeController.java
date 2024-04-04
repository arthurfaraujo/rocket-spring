package com.arthurfaraujo.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arthurfaraujo.passin.dto.attendee.AttendeeBadgeResponseDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeResponseDTO;
import com.arthurfaraujo.passin.services.AttendeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

  private final AttendeeService service;

  @GetMapping(path = "/{id}")
  public ResponseEntity<AttendeeResponseDTO> getAttendeeById(@PathVariable String id) {
    AttendeeResponseDTO attendeeResponseDTO = this.service.getAttendeeById(id);

    return ResponseEntity.ok(attendeeResponseDTO);
  }

  @PostMapping(path = "/{id}/check-in")
  public ResponseEntity<Object> checkIn(@PathVariable String id) {
    this.service.checkInAttendee(id);

    return ResponseEntity.ok().build();
  }

  @GetMapping(path = "/{id}/badge")
  public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String id, UriComponentsBuilder uriComponentsBuilder) {
    AttendeeBadgeResponseDTO badge = this.service.getAttendeeBadge(id, uriComponentsBuilder);

    return ResponseEntity.ok(badge);
  }
}

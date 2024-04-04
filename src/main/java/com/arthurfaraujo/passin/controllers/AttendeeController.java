package com.arthurfaraujo.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arthurfaraujo.passin.dto.attendee.AttendeeResponseDTO;
import com.arthurfaraujo.passin.services.AttendeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

  private final AttendeeService service;

  @GetMapping(path = "{id}")
  public ResponseEntity<AttendeeResponseDTO> getAttendeeById(@PathVariable String id) {
    AttendeeResponseDTO attendeeResponseDTO = this.service.getAttendeeById(id);

    return ResponseEntity.ok(attendeeResponseDTO);
  }
}

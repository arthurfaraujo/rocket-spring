package com.arthurfaraujo.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arthurfaraujo.passin.dto.attendee.AttendeeIdDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeRequestDTO;
import com.arthurfaraujo.passin.services.AttendeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

  private final AttendeeService service;

  @GetMapping
  public ResponseEntity<String> getTeste() {
    return ResponseEntity.ok("sucesso!");
  }

  @PostMapping
  public ResponseEntity<AttendeeIdDTO> addAttendee(@RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
    AttendeeIdDTO attendeeIdDTO = this.service.addAttendee(body);

    var uri = uriComponentsBuilder.path("/attendees/{id}").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();
    
    return ResponseEntity.created(uri).body(attendeeIdDTO);
  }
}

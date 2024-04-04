package com.arthurfaraujo.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arthurfaraujo.passin.dto.attendee.AttendeeIdDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeListResponseDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeRequestDTO;
import com.arthurfaraujo.passin.dto.event.EventIdDTO;
import com.arthurfaraujo.passin.dto.event.EventRequestDTO;
import com.arthurfaraujo.passin.dto.event.EventResponseDTO;
import com.arthurfaraujo.passin.services.AttendeeService;
import com.arthurfaraujo.passin.services.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;
  private final AttendeeService attendeeService;

  @GetMapping("/{id}")
  public ResponseEntity<EventResponseDTO> getEventById(@PathVariable String id) {
    EventResponseDTO event = this.eventService.getEventDetail(id);

    return ResponseEntity.ok(event);
  }

  @GetMapping("/{id}/attendees")
  public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String id) {
    AttendeeListResponseDTO attendeeList = this.attendeeService.getAllAttendeesDetailsByEvent(id);

    return ResponseEntity.ok(attendeeList);
  }

  @PostMapping
  public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body,
      UriComponentsBuilder uriComponentsBuilder) {
    EventIdDTO eventIdDTO = this.eventService.createEvent(body);

    var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

    return ResponseEntity.created(uri).body(eventIdDTO);
  }

  @PostMapping(path = "{id}/attendees")
  public ResponseEntity<AttendeeIdDTO> addAttendeeToEvent(@RequestBody AttendeeRequestDTO body,
      @PathVariable String id, UriComponentsBuilder uriComponentsBuilder) {
    
    body.setEventId(id);
    AttendeeIdDTO attendeeIdDTO = this.eventService.addAttendeeToEvent(body);

    var uri = uriComponentsBuilder.path("/attendees/{id}").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

    return ResponseEntity.created(uri).body(attendeeIdDTO);
  }
}

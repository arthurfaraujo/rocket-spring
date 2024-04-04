package com.arthurfaraujo.passin.services;

import java.text.Normalizer;

import org.springframework.stereotype.Service;

import com.arthurfaraujo.passin.domain.event.Event;
import com.arthurfaraujo.passin.domain.event.exceptions.EventNotFoundException;
import com.arthurfaraujo.passin.dto.event.EventIdDTO;
import com.arthurfaraujo.passin.dto.event.EventRequestDTO;
import com.arthurfaraujo.passin.dto.event.EventResponseDTO;
import com.arthurfaraujo.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;
  private final AttendeeService attendeeService;

  private String genSlug(String title) {
    String normalizedTitle = Normalizer.normalize(title, Normalizer.Form.NFD);

    return normalizedTitle.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
        .replaceAll("[^\\w\\s]", "")
        .replaceAll("\\s+", "-")
        .toLowerCase();
  }

  public EventResponseDTO getEventDetail(String eventId) {
    Event event = this.eventRepository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
    Integer attendeeAmount = this.attendeeService.getAttendeeAmountByEvent(eventId);

    return new EventResponseDTO(event, attendeeAmount);
  }

  public EventIdDTO createEvent(EventRequestDTO event) {
    Event newEvent = new Event();
    newEvent.setTitle(event.title());
    newEvent.setDetails(event.details());
    newEvent.setMaxAttendees(event.maxAttendees());
    newEvent.setSlug(genSlug(event.title()));

    this.eventRepository.save(newEvent);

    return new EventIdDTO(newEvent.getId());
  }
}

package com.arthurfaraujo.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthurfaraujo.passin.domain.attendee.Attendee;
import com.arthurfaraujo.passin.domain.checkin.CheckIn;
import com.arthurfaraujo.passin.domain.event.Event;
import com.arthurfaraujo.passin.domain.event.exceptions.EventNotFoundException;
import com.arthurfaraujo.passin.dto.attendee.AttendeeDetailDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeIdDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeListResponseDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeRequestDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeResponseDTO;
import com.arthurfaraujo.passin.repositories.AttendeeRepository;
import com.arthurfaraujo.passin.repositories.CheckInRepository;
import com.arthurfaraujo.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {

  private final AttendeeRepository attendeeRepository;
  private final CheckInRepository checkInRepository;
  private final EventRepository eventRepository;

  public AttendeeResponseDTO getAttendeeById(String attendeeId) {
    Attendee attendee = this.attendeeRepository.findById(attendeeId)
        .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));

    LocalDateTime checkedInAt = getCheckedInAt(attendeeId);

    return new AttendeeResponseDTO(attendee, checkedInAt);
  }

  public Integer getAttendeeAmountByEvent(String eventId) {
    return this.attendeeRepository.findByEventId(eventId).size();
  }

  public AttendeeListResponseDTO getAllAttendeesDetailsByEvent(String eventId) {
    List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);

    List<AttendeeDetailDTO> attendeeDetailList = attendeesList.stream().map(attendee -> {
      LocalDateTime checkedInAt = getCheckedInAt(attendee.getId());

      return new AttendeeDetailDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(),
          checkedInAt);
    }).toList();

    return new AttendeeListResponseDTO(attendeeDetailList);
  }

  public AttendeeIdDTO addAttendee(AttendeeRequestDTO attendee) {
    Attendee newAttendee = new Attendee();
    Event event = this.eventRepository.findById(attendee.getEventId())
        .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + attendee.getEventId()));

    newAttendee.setName(attendee.getName());
    newAttendee.setEmail(attendee.getEmail());
    newAttendee.setEvent(event);
    newAttendee.setCreatedAt(LocalDateTime.now() );

    // puxar o evento antes com esse id e aí passar pro setter

    this.attendeeRepository.save(newAttendee);

    return new AttendeeIdDTO(newAttendee.getId());
  }

  private LocalDateTime getCheckedInAt(String attendeeId) {
    Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendeeId);
    return checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
  }
}

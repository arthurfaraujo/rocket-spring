package com.arthurfaraujo.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthurfaraujo.passin.domain.attendee.Attendee;
import com.arthurfaraujo.passin.domain.event.Event;
import com.arthurfaraujo.passin.dto.attendee.AttendeeDetailDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeIdDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeListResponseDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeRequestDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeResponseDTO;
import com.arthurfaraujo.passin.dto.attendee.exceptions.AttendeeAlreadyExistException;
import com.arthurfaraujo.passin.dto.attendee.exceptions.AttendeeNotFoundException;
import com.arthurfaraujo.passin.repositories.AttendeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {

  private final AttendeeRepository attendeeRepository;
  private final CheckInService checkInService;

  public AttendeeResponseDTO getAttendeeById(String attendeeId) {
    Attendee attendee = this.attendeeRepository.findById(attendeeId)
        .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));

    LocalDateTime checkedInAt = this.checkInService.getCheckedInAt(attendeeId);

    return new AttendeeResponseDTO(attendee, checkedInAt);
  }

  public Integer getAttendeeAmountByEvent(String eventId) {
    return this.attendeeRepository.findByEventId(eventId).size();
  }

  public AttendeeListResponseDTO getAllAttendeesDetailsByEvent(String eventId) {
    List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);

    List<AttendeeDetailDTO> attendeeDetailList = attendeesList.stream().map(attendee -> {
      LocalDateTime checkedInAt = this.checkInService.getCheckedInAt(attendee.getId());

      return new AttendeeDetailDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(),
          checkedInAt);
    }).toList();

    return new AttendeeListResponseDTO(attendeeDetailList);
  }

  public AttendeeIdDTO addAttendee(AttendeeRequestDTO attendee, Event event) {
    Attendee newAttendee = new Attendee();
    newAttendee.setName(attendee.getName());
    newAttendee.setEmail(attendee.getEmail());
    newAttendee.setEvent(event);
    newAttendee.setCreatedAt(LocalDateTime.now());
    
    this.attendeeRepository.save(newAttendee);

    return new AttendeeIdDTO(newAttendee.getId());
  }

  public void verifyAttendeeExist(String email) {
    Optional<Attendee> attendee = this.attendeeRepository.findByEmail(email);

    if (attendee.isPresent())
      throw new AttendeeAlreadyExistException("Attendee not found with id: " + attendee.get().getId());
  }
  
  public void checkInAttendee(String attendeeId) {
    Attendee attendee = getAttendee(attendeeId);

    this.checkInService.createCheckIn(attendee);
  }

  private Attendee getAttendee(String attendeeId) {
    return this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Attendee don't exist!"));
  }
}

package com.arthurfaraujo.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthurfaraujo.passin.domain.attendee.Attendee;
import com.arthurfaraujo.passin.domain.checkin.CheckIn;
import com.arthurfaraujo.passin.dto.attendee.AttendeeDetailDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeIdDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeListResponseDTO;
import com.arthurfaraujo.passin.dto.attendee.AttendeeRequestDTO;
import com.arthurfaraujo.passin.repositories.AttendeeRepository;
import com.arthurfaraujo.passin.repositories.CheckInRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {

  private final AttendeeRepository attendeeRepository;
  private final CheckInRepository checkInRepository;

  public Integer getAttendeeAmountByEvent(String eventId) {
    return this.attendeeRepository.findByEventId(eventId).size();
  }

  public AttendeeListResponseDTO getAllAttendeesDetailsByEvent(String eventId) {
    List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);

    List<AttendeeDetailDTO> attendeeDetailList = attendeesList.stream().map(attendee -> {
      Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
      LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
      
      return new AttendeeDetailDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
    }).toList();

    return new AttendeeListResponseDTO(attendeeDetailList);
  }

  public AttendeeIdDTO addAttendee(AttendeeRequestDTO attendee) {
    Attendee newAttendee = new Attendee();
    newAttendee.setName(attendee.name());
    newAttendee.setEmail(attendee.email());
    newAttendee.setEvent(attendee.event());

    this.attendeeRepository.save(newAttendee);

    return new AttendeeIdDTO(newAttendee.getId());
  }
}

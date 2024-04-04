package com.arthurfaraujo.passin.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arthurfaraujo.passin.domain.attendee.Attendee;
import com.arthurfaraujo.passin.domain.checkin.CheckIn;
import com.arthurfaraujo.passin.domain.checkin.exceptions.CheckinAlreadyExistException;
import com.arthurfaraujo.passin.repositories.CheckInRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckInService {
  private final CheckInRepository checkInRepository;

  public LocalDateTime getCheckedInAt(String attendeeId) {
    Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendeeId);
    return checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
  }

  public void createCheckIn(Attendee attendee) {
    Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
    if (checkIn.isPresent())
      throw new CheckinAlreadyExistException("Attendee already checked in");

    CheckIn newCheckIn = new CheckIn();
    newCheckIn.setAttendee(attendee);
    newCheckIn.setCreatedAt(LocalDateTime.now());

    this.checkInRepository.save(newCheckIn);
  }
}

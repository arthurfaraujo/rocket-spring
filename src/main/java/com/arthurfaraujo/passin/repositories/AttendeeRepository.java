package com.arthurfaraujo.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurfaraujo.passin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {

}

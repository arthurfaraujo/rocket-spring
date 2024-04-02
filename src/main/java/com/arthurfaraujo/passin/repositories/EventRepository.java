package com.arthurfaraujo.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurfaraujo.passin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String> {
}

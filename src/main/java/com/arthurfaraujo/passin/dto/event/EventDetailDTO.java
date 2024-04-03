package com.arthurfaraujo.passin.dto.event;

public record EventDetailDTO(
    String id, String title,
    String details, String slug,
    Integer maxAttendees, Integer attendeesAmount) {}

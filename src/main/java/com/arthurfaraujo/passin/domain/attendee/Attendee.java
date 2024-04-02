package com.arthurfaraujo.passin.domain.attendee;

import java.time.LocalDateTime;

import com.arthurfaraujo.passin.domain.event.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;

}

package com.arthurfaraujo.passin.domain.checkin;

import java.time.LocalDateTime;

import com.arthurfaraujo.passin.domain.attendee.Attendee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "check_in")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "attendee_id", nullable = false)
  private Attendee attendee;

}

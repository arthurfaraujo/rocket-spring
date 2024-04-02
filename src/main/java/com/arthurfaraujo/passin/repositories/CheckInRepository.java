package com.arthurfaraujo.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurfaraujo.passin.domain.checkin.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

}

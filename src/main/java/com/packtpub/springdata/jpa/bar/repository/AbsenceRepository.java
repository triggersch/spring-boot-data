package com.packtpub.springdata.jpa.bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packtpub.springdata.jpa.bar.model.Absence;

public interface AbsenceRepository  extends JpaRepository<Absence, Long> {
}

package com.mandiwal.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mandiwal.persistence.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

	School findBySchoolName(String schoolName);
}

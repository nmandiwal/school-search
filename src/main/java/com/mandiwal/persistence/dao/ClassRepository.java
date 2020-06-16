package com.mandiwal.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mandiwal.persistence.model.ClassRoom;

public interface ClassRepository extends JpaRepository<ClassRoom, Long> {

	List<ClassRoom> findByIdIn(List<Long> classRoomIds);

	ClassRoom findBySchoolIdAndClassNumberAndYear(Long schoolId, Integer classNumber, Integer year);
}

package com.mandiwal.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mandiwal.persistence.model.UserClassRoomMapping;

public interface UserClassMappingRepository extends JpaRepository<UserClassRoomMapping, Long> {

	List<UserClassRoomMapping> findByUsername(String username);

	List<UserClassRoomMapping> findByClassRoomIdIn(List<Long> classRoomIds);

	List<UserClassRoomMapping> findByUsernameAndClassRoomId(String username, Long classRoomId);
}

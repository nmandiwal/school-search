package com.mandiwal.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mandiwal.persistence.model.UserClassRoomMappingHistory;

public interface UserClassMappingHistoryRepository extends JpaRepository<UserClassRoomMappingHistory, Long> {
}

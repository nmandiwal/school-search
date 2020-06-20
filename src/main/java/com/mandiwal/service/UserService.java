package com.mandiwal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mandiwal.persistence.dao.ClassRepository;
import com.mandiwal.persistence.dao.SchoolRepository;
import com.mandiwal.persistence.dao.UserClassMappingHistoryRepository;
import com.mandiwal.persistence.dao.UserClassMappingRepository;
import com.mandiwal.persistence.dao.UserRepository;
import com.mandiwal.persistence.model.ClassRoom;
import com.mandiwal.persistence.model.School;
import com.mandiwal.persistence.model.User;
import com.mandiwal.persistence.model.UserClassRoomMapping;
import com.mandiwal.persistence.model.UserClassRoomMappingHistory;
import com.mandiwal.vo.ClassRecord;
import com.mandiwal.vo.Classmate;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ClassRepository classRepository;

	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	UserClassMappingRepository classMappingRepository;

	@Autowired
	UserClassMappingHistoryRepository classMappingHistoryRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public List<Classmate> getClassmates(String username) {
		List<User> users = new ArrayList<>();
		List<UserClassRoomMapping> classMappings = classMappingRepository.findByUsername(username);
		List<Long> classRoomIds = classMappings.stream().map(a -> a.getClassRoomId()).distinct()
				.collect(Collectors.toList());
		if (classRoomIds.size() > 0) {
			classMappings = classMappingRepository.findByClassRoomIdIn(classRoomIds);
			List<String> userNames = classMappings.stream().map(a -> a.getUsername()).distinct()
					.collect(Collectors.toList());
			userNames.remove(username);
			if (userNames.size() > 0) {
				users = userRepository.findByUsernameIn(userNames);
			}
		} else {
			System.out.print("Class data for current user is not recorded");
		}
		return mapUserToClassmate(users);
	}

	private List<Classmate> mapUserToClassmate(List<User> users) {
		return users.stream()
				.map(user -> Classmate.builder().ImageUrl(user.getImageUrl()).name(user.getPassword()).build())
				.collect(Collectors.toList());
	}

	public boolean isClassDataRecorded(String username) {
		return !classMappingRepository.findByUsername(username).isEmpty();
	}

	public List<ClassRecord> getClassRecords(String username) {
		List<ClassRoom> classRooms = new ArrayList<>();
		List<UserClassRoomMapping> classMappings = classMappingRepository.findByUsername(username);
		List<Long> classRoomIds = classMappings.stream().map(a -> a.getClassRoomId()).collect(Collectors.toList());
		if (classRoomIds.size() > 0) {
			classRooms = classRepository.findByIdIn(classRoomIds);
		}
		return mapClassRoomToClassRecord(classRooms);
	}

	private List<ClassRecord> mapClassRoomToClassRecord(List<ClassRoom> classRooms) {
		return classRooms.stream()
				.map(classRoom -> ClassRecord.builder().id(classRoom.getId())
						.schoolName(classRoom.getSchool().getSchoolName()).classNumber(classRoom.getClassNumber())
						.year(classRoom.getYear()).build())
				.sorted(Comparator.comparingInt(ClassRecord::getClassNumber)).collect(Collectors.toList());

	}

	public void deleteClassRoom(String username, Long classRoomId) {
		List<UserClassRoomMapping> classMappings = classMappingRepository.findByUsernameAndClassRoomId(username,
				classRoomId);
		classMappingRepository.deleteAll(classMappings);
	}

	public void addClassRoom(String username, ClassRecord classRecord) {
		String schoolName = classRecord.getSchoolName();
		if (null != schoolName && !schoolName.isBlank()) {
			School school = schoolRepository.findBySchoolName(schoolName);
			ClassRoom classRoom = classRepository.findBySchoolIdAndClassNumberAndYear(school.getId(),
					classRecord.getClassNumber(), classRecord.getYear());
			if (classRoom == null) {
				classRoom = ClassRoom.builder().school(school).classNumber(classRecord.getClassNumber())
						.year(classRecord.getYear()).build();
				classRepository.save(classRoom);
			}
			UserClassRoomMapping classRoomMapping = new UserClassRoomMapping();
			classRoomMapping.setClassRoomId(classRoom.getId());
			classRoomMapping.setUsername(username);
			classMappingRepository.save(classRoomMapping);
			UserClassRoomMappingHistory classRoomMappingHistory = new UserClassRoomMappingHistory();
			classRoomMappingHistory.setClassRoomId(classRoom.getId());
			classRoomMappingHistory.setUsername(username);
			classRoomMappingHistory.setCreatedDate(LocalDate.now());
			classMappingHistoryRepository.save(classRoomMappingHistory);
		} else {
			logger.warn("School Name was not selected by " + username);
		}
	}

	public ClassRoom getClassRoom(long classRoomId) {
		return classRepository.findById(classRoomId).get();
	}

	public List<String> getSchools() {
		return schoolRepository.findAll().stream().map(a -> a.getSchoolName()).collect(Collectors.toList());
	}

}

package com.mandiwal.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "USER_CLASS_ROOM_MAPPING")
public class UserClassRoomMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "USER_NAME", nullable = false)
	private String username;

	@Column(name = "CLASS_ROOM_ID", nullable = false)
	private Long classRoomId;

	public UserClassRoomMapping() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}

	@Override
	public String toString() {
		return "UserClassRoomMapping [id=" + id + ", username=" + username + ", classRoomId=" + classRoomId + "]";
	}

}

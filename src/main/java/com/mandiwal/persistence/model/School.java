package com.mandiwal.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "SCHOOL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "SCHOOL_NAME", nullable = false)
	private String schoolName;

}

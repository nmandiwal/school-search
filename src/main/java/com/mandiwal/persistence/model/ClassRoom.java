package com.mandiwal.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CLASS_ROOM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@Column(name = "SCHOOL_ID", nullable = false)
//	private String schoolId;

	@Column(name = "CLASS_NUMBER", nullable = false)
	private Integer classNumber;

	@Column(name = "YEAR", nullable = false)
	private Integer year;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SCHOOL_ID", referencedColumnName = "id")
	private School school;

}

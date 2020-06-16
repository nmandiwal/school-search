package com.mandiwal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassRecord {
	private Long id;
	private String schoolName;
	private Integer classNumber;
	private Integer year;
}

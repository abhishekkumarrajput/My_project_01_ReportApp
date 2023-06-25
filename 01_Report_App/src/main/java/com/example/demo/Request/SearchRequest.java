package com.example.demo.Request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	
	private String planeName;
	private String planeStatus;
	private String gender;
	private String planStartDate;   // yy-MM-dd
	private String planEndDate;  
	
	
}

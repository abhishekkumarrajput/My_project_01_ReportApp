package com.example.demo.Model;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Data
@Table(name="CITIZEN_PLAN_INFO")
public class CitiZenPlan {
@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citiZenId;
	private String citiZenName;
	private String planeName;
	private String planeStatus;
	private String gender;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private String deniedReason;
	private String terminatedReason;
	private LocalDate terminatedDate;

}

package com.bridgelabz.candidatemodule.dto;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
public class CandidateDTO {
	   @GenericGenerator(name = "learner_details", strategy = "increment")
	   @GeneratedValue(generator = "learner_details")
	   @Pattern(regexp = "^[A-Z]{1}[0-9]{2}$", message = "cicId Invalid")
	   private String cicId;
	   @Pattern(regexp = "^[A-Z]{1}[a-z\\s]{2,}[a-zA-Z]{3,}$", message = "Full name Invalid")
	   private String fullName;
	   @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email is invalid")
	   private String email;
	   @Pattern(regexp = "^[9,8,7,6]{1}[0-9]{9}$", message = "mobile number is invalid")
	   private String mobileNum;
	   @NotBlank(message = "hiredDate cannot be empty")
	   private String hiredDate;
	   @NotBlank(message = "degree cannot be empty")
	   private String degree;
	   private Double aggrPer;
	   @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "city is Invalid")
	   private String city;
	   @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "State is Invalid")
	   private String state;
	   @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "job location cannot be empty")
	   private String preferredJobLocation;
	   @NotBlank(message = "status cannot be empty")
	   private String status;
	   @Pattern(regexp = "^[2]{1}[0]{1}[0-9]{2}$", message = "passedout year is invalid")
	   private String passedOutYear;
	   @NotBlank(message = "creator user cannot be empty")
	   private String creatorUser;
	   @NotBlank(message = "candidate status cannot be empty")
	   private String candidateStatus;

	   @CreationTimestamp
	   private LocalDateTime createdTimeStamp;

	   @UpdateTimestamp
	   private LocalDateTime updatedTimeStamp;
}

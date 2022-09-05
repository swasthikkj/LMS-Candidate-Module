package com.bridgelabz.candidatemodule.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.bridgelabz.candidatemodule.dto.CandidateDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "candidate")
@Data
@NoArgsConstructor
public class CandidateModel {
	@Id
	@GenericGenerator(name = "candidate", strategy = "increment")
	@GeneratedValue(generator = "candidate")
	@Column(name = "ID")
	private Long id;
	@Column(name = "CIC_ID")
	private String cicId;
	@Column(name = "FULL_NAME")
	private String fullName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "MOBILE_NUMBER")
	private String mobileNum;
	@Column(name = "HIRED_DATE")
	private String hiredDate;
	@Column(name = "DEGREE")
	private String degree;
	@Column(name = "AGGREGATE_PERCENTAGE")
	private Double aggrPer;
	@Column(name = "CITY")
	private String city;
	@Column(name = "STATE")
	private String state;
	@Column(name = "PREFFERED_JOB_LOCATION")
	private String preferredJobLocation;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PASSED_OUT_YEAR")
	private String passedOutYear;
	@Column(name = "CREATOR_USER")
	private String creatorUser;
	@Column(name = "CANDIDATE_STATUS")
	private String candidateStatus;

	@CreationTimestamp
	private LocalDateTime createdTimeStamp;

	@UpdateTimestamp
	private LocalDateTime updatedTimeStamp;

	public CandidateModel(CandidateDTO candidateDTO) {
		this.cicId = candidateDTO.getCicId();
		this.fullName = candidateDTO.getFullName();
		this.email = candidateDTO.getEmail();
		this.mobileNum = candidateDTO.getMobileNum();
		this.hiredDate = candidateDTO.getHiredDate();
		this.degree = candidateDTO.getDegree();
		this.aggrPer = candidateDTO.getAggrPer();
		this.city = candidateDTO.getCity();
		this.state = candidateDTO.getState();
		this.preferredJobLocation = candidateDTO.getPreferredJobLocation();
		this.status = candidateDTO.getStatus();
		this.passedOutYear = candidateDTO.getPassedOutYear();
		this.creatorUser = candidateDTO.getCreatorUser();
		this.candidateStatus = candidateDTO.getCandidateStatus();
		this.createdTimeStamp = candidateDTO.getCreatedTimeStamp().now();
		this.updatedTimeStamp = candidateDTO.getUpdatedTimeStamp().now();
	}
}

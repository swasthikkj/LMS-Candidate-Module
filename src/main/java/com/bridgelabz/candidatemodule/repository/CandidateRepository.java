package com.bridgelabz.candidatemodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.candidatemodule.model.CandidateModel;
@Repository
public interface CandidateRepository extends JpaRepository<CandidateModel, Long> {
	
	List<CandidateModel> findByStatus(String status);

}

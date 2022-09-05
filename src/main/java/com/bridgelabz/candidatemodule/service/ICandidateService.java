package com.bridgelabz.candidatemodule.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.candidatemodule.dto.CandidateDTO;
import com.bridgelabz.candidatemodule.model.CandidateModel;

public interface ICandidateService {

	CandidateModel addCandidate(CandidateDTO candidateDTO, /*Long techId*/ String token);

	CandidateModel updateCandidate(CandidateDTO candidateDTO, Long id, String token);

	Optional<CandidateModel> getCandidateById(Long id, String token);

	List<CandidateModel> getAllCandidates(String token);

	CandidateModel deleteCandidate(Long id, String token);

	List<CandidateModel> getCandidateByStatus(String status, String token);

	CandidateModel changeStatus(Long id, String status, String token);

	long statusCount(String status, String token);

}

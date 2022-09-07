package com.bridgelabz.candidatemodule.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.candidatemodule.dto.CandidateDTO;
import com.bridgelabz.candidatemodule.exception.CustomNotFoundException;
import com.bridgelabz.candidatemodule.model.CandidateModel;
import com.bridgelabz.candidatemodule.repository.CandidateRepository;
import com.bridgelabz.candidatemodule.util.TokenUtil;

@Service
public class CandidateService implements ICandidateService {
	@Autowired
	CandidateRepository candidateRepository;

	//	@Autowired
	//	TokenUtil tokenUtil;

	@Autowired
	MailService mailService;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public CandidateModel addCandidate(CandidateDTO candidateDTO, String token) {
		//		Long candidateId = tokenUtil.decodeToken(token);
		//		Optional<CandidateModel> isTokenPresent = candidateRepository.findById(candidateId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			CandidateModel model = new CandidateModel(candidateDTO);
			candidateRepository.save(model);
			String body = "candidate added successfully with candidate Id" + model.getId();
			String subject = "candidate added Successfully";
			mailService.send(model.getEmail(), subject, body);
			return model;

		} throw new CustomNotFoundException(400, "Token not found");

	}

	@Override
	public CandidateModel updateCandidate(CandidateDTO candidateDTO, Long id, String token) {
		//		Long candidateId = tokenUtil.decodeToken(token);
		//		Optional<CandidateModel> isTokenPresent = candidateRepository.findById(candidateId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(id);
			if(isCandidatePresent.isPresent()) {
				isCandidatePresent.get().setCicId(candidateDTO.getCicId());
				isCandidatePresent.get().setFullName(candidateDTO.getFullName());
				isCandidatePresent.get().setEmail(candidateDTO.getEmail());
				isCandidatePresent.get().setMobileNum(candidateDTO.getMobileNum());
				isCandidatePresent.get().setHiredDate(candidateDTO.getHiredDate());
				isCandidatePresent.get().setDegree(candidateDTO.getDegree());
				isCandidatePresent.get().setAggrPer(candidateDTO.getAggrPer());
				isCandidatePresent.get().setCity(candidateDTO.getCity());
				isCandidatePresent.get().setState(candidateDTO.getState());
				isCandidatePresent.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
				isCandidatePresent.get().setStatus(candidateDTO.getStatus());
				isCandidatePresent.get().setPassedOutYear(candidateDTO.getPassedOutYear());
				isCandidatePresent.get().setCreatorUser(candidateDTO.getCreatorUser());
				isCandidatePresent.get().setCandidateStatus(candidateDTO.getCandidateStatus());
				isCandidatePresent.get().setCreatedTimeStamp(LocalDateTime.now());
				isCandidatePresent.get().setUpdatedTimeStamp(LocalDateTime.now());
				candidateRepository.save(isCandidatePresent.get());
				String body = "candidate updated successfully with candidate Id" + isCandidatePresent.get().getId();
				String subject = "candidate updated Successfully";
				mailService.send(isCandidatePresent.get().getEmail(), subject, body);
				return isCandidatePresent.get();
			}
			throw new CustomNotFoundException(400, "not present");
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public Optional<CandidateModel> getCandidateById(Long id, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			return candidateRepository.findById(id);
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public List<CandidateModel> getAllCandidates(String token) {
		//		Long candidateId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<CandidateModel> getAllCandidates = candidateRepository.findAll();
			if(getAllCandidates.size() > 0) {
				return getAllCandidates;
			} else {
				throw new CustomNotFoundException(400, "candidate not present");
			}
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public CandidateModel deleteCandidate(Long id, String token) {
		//		Long candidateId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(id);
			if(isCandidatePresent.isPresent()) {
				candidateRepository.delete(isCandidatePresent.get());
				String body = "candidate deleted successfully with candidate Id" + isCandidatePresent.get().getId();
				String subject = "candidate deleted Successfully";
				mailService.send(isCandidatePresent.get().getEmail(), subject, body);
				return isCandidatePresent.get();
			}
			throw new CustomNotFoundException(400, "Candidate not found");
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public List<CandidateModel> getCandidateByStatus(String status, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<CandidateModel> isStatusPresent = candidateRepository.findByStatus(status);
			if (isStatusPresent.size() > 0) {
				return isStatusPresent;
			}
			throw new CustomNotFoundException(400, "candidate not found");
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public CandidateModel changeStatus(Long id, String status, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<CandidateModel> isIdPresent = candidateRepository.findById(id);
			if (isIdPresent.isPresent()) {
				isIdPresent.get().setStatus(status);
				candidateRepository.save(isIdPresent.get());
				return isIdPresent.get();
			}
			throw new CustomNotFoundException(400, "Status not found");
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public long statusCount(String status, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<CandidateModel> isStatusPresent = candidateRepository.findByStatus(status);
			if(isStatusPresent.size() > 0) {
				return isStatusPresent.stream().count();
			}
			throw new CustomNotFoundException(400, "status not found");
		}
		throw new CustomNotFoundException(400, "Token not found");
	}
}

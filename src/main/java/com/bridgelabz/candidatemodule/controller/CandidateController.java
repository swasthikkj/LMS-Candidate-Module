package com.bridgelabz.candidatemodule.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.candidatemodule.util.Response;
import com.bridgelabz.candidatemodule.dto.CandidateDTO;
import com.bridgelabz.candidatemodule.model.CandidateModel;
import com.bridgelabz.candidatemodule.service.ICandidateService;

/**
 * Purpose:create candidate controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/candidatemodule")
public class CandidateController {
	@Autowired
	ICandidateService candidateService;
	
	/**
	 * Purpose:add candidate
	 * @Param token 
	 */

	@PostMapping("/addCandidate")
	public ResponseEntity<Response> addCandidate(@Valid @RequestBody CandidateDTO candidateDTO, /*@RequestParam Long techId*/ @RequestHeader String token) {
		CandidateModel candidateModel = candidateService.addCandidate(candidateDTO, token);
		Response response = new Response(200, "candidate added successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	/**
	 * Purpose:update candidate
	 * @Param token and id
	 */
	@PutMapping("updateCandidate/{id}")
	public ResponseEntity<Response> updateCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @PathVariable Long id, @RequestHeader String token) {
		CandidateModel candidateModel = candidateService.updateCandidate(candidateDTO, id, token);
		Response response = new Response(200, "candidate updated successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get candidate by id
	 * @Param token and id
	 */
	@GetMapping("/getCandidateData/{id}")
	public ResponseEntity<Response> getCandidateById(@PathVariable Long id, @RequestHeader String token) {
		Optional<CandidateModel> candidateModel = candidateService.getCandidateById(id, token);
		Response response = new Response(200, "candidate fetched by id successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get all candidates
	 * @Param token 
	 */
	@GetMapping("/getAllCandidates")
	public ResponseEntity<Response> getAllCandidates(@RequestHeader String token) {
		List<CandidateModel> candidateModel = candidateService.getAllCandidates(token);
		Response response = new Response(200, "All candidates fetched successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/**
	 * Purpose:delete candidate
	 * @Param token and id
	 */
	@DeleteMapping("/deleteCandidate/{id}")
	public ResponseEntity<Response> deleteCandidate(@PathVariable Long id, @RequestHeader String token) {
		CandidateModel candidateModel = candidateService.deleteCandidate(id, token);
		Response response = new Response(200, "candidate deleted successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get candidate by status
	 * @Param token and status
	 */
	@GetMapping("/getCandidate/{status}")
	public ResponseEntity<Response> getCandidateByStatus(@RequestHeader String status, @RequestHeader String token) {
		List<CandidateModel> candidateModel = candidateService.getCandidateByStatus(status, token);
		Response response = new Response(200, "candidate status fetched successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	/**
	 * Purpose:change candidate status
	 * @Param token, id and status
	 */
	@PutMapping("/changeStatus/{id}")
	public ResponseEntity<Response> changeStatus(@PathVariable("id") Long id, @RequestParam String status, @RequestHeader String token) {
		CandidateModel candidateModel = candidateService.changeStatus(id, status, token);
		Response response = new Response(200, "candidate status changed successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	/**
	 * Purpose:get candidate status count
	 * @Param token and status
	 */
	@GetMapping("/getStatusCount")
	public ResponseEntity<Response> statusCount(@RequestHeader String status, @RequestHeader String token) {
		long candidateModel = candidateService.statusCount(status, token);
		Response response = new Response(200, "candidate status count fetched successfully", candidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

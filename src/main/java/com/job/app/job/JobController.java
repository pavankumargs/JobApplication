package com.job.app.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobService jobService;

	@GetMapping
	public ResponseEntity<List<Job>> findAll() {
		List<Job> jobs = jobService.findAll();
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("Job Created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> findJobById(@PathVariable("id") Long id) {
		Job job = jobService.findJobById(id);
		if (job != null) {
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable("id") Long id) {
		boolean isDeleted = jobService.deleteJobById(id);
		if (isDeleted) {
			return new ResponseEntity<>("Job With ID - " + id + " Deleted Successfully", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("Job With ID - " + id + "Not Found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJobById(@PathVariable("id") Long id, @RequestBody Job updatedJob) {
		boolean isUpdated = jobService.updateJobById(id, updatedJob);
		if (isUpdated) {
			return new ResponseEntity<>("Job With ID - " + id + " Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Job With ID - " + id + " Not Found", HttpStatus.NOT_FOUND);
	}
}

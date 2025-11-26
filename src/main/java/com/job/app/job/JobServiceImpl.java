package com.job.app.job;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<Job> findAll() {
		List<Job> jobs = jobRepository.findAll();
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job findJobById(Long id) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		if (jobOptional.isPresent()) {
			Job job = jobOptional.get();
			return job;
		}
		return null;
	}

	@Override
	public boolean updateJobById(Long id, Job updatedJob) {
		Optional<Job> optionalJob = jobRepository.findById(id);
		if (optionalJob.isPresent()) {
			Job job = optionalJob.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setLocation(updatedJob.getLocation());

			jobRepository.save(job);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteJobById(Long id) {
		Optional<Job> optionalJob = jobRepository.findById(id);
		if (optionalJob.isPresent()) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

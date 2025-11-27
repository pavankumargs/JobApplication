package com.job.app.job;

import java.util.List;

public interface JobService {

	public List<Job> findAll();

	public void createJob(Job job);

	public Job findJobById(Long id);

	public boolean deleteJobById(Long id);

	public boolean updateJobById(Long id, Job job);
	
}

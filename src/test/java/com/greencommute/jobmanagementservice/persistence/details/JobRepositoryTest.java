package com.greencommute.jobmanagementservice.persistence.details;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;
import com.greencommute.jobmanagementservice.persistence.IJobRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class JobRepositoryTest {

	@Autowired
	IJobRepository repository;

	@Test
	void testFindAllJobs() {
		ImmutableGetJobsQueryParams filter = ImmutableGetJobsQueryParams.builder().isGreenCommute(true).lat(17.4326)
				.lon(78.4071).distance(20000).build();

		List<ImmutableLiteJobResponse> jobs = repository.findAllJobs(filter);
		assertNotNull(jobs);
		assertEquals(9, jobs.size());
	}


	@Test
	void testFindAllJobsNullFilter() {
		assertThrows(NullPointerException.class, () -> repository.findAllJobs(null));
	}

	@Test
	void testFindJobById() {
		UUID jobId = UUID.fromString("f74cdff8-34d9-4d00-aaa3-eee4e99cd225");
		ImmutableJobResponse job = repository.findJobById(jobId);
		assertNotNull(job);
		assertEquals(job.getID(), jobId);
	}

}

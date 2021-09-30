package com.greencommute.jobmanagementservice.persistence.details;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.persistence.IUserJobMappingRepository;
import com.greencommute.jobmanagementservice.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserJobMappingRepositoryTest {

	@Autowired
	IUserJobMappingRepository repository;

	@Test
	void testFindSavedJobs() {
		UUID userId = UUID.fromString(Constants.USER_ID);
		List<ImmutableLiteJobResponse> savedJobs = repository.findSavedJobs(userId);
		assertNotNull(savedJobs);
		assertTrue(savedJobs.isEmpty());
	}

	@Test
	void testSaveJob1() {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("f74cdff8-34d9-4d00-aaa3-eee4e99cd225");
		repository.saveJob(userId, jobId);
		List<ImmutableLiteJobResponse> savedJobs = repository.findSavedJobs(userId);
		assertNotNull(savedJobs);
		assertEquals(1, savedJobs.size());
		repository.unSaveJob(userId, jobId);
	}

	@Test
	void testSaveJobDuplicateJobSave() {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("91a9213b-9f70-44ae-9e76-8fa9891e215d");
		repository.saveJob(userId, jobId);
		assertThrows(UnableToExecuteStatementException.class, () -> repository.saveJob(userId, jobId));
		repository.unSaveJob(userId, jobId);
	}

	@Test
	void testUnSaveJob() {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("91a9213b-9f70-44ae-9e76-8fa9891e215d");
		repository.saveJob(userId, jobId);
		List<ImmutableLiteJobResponse> savedJobs = repository.findSavedJobs(userId);
		assertNotNull(savedJobs);
		assertEquals(1, savedJobs.size());
		repository.unSaveJob(userId, jobId);
		savedJobs = repository.findSavedJobs(userId);
		assertNotNull(savedJobs);
		assertTrue(savedJobs.isEmpty());
	}

	@Test
	void testUnSaveJobNotPresent() {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("91a9213b-9f70-44ae-9e76-8fa9891e215d");
		repository.unSaveJob(userId, jobId);
		List<ImmutableLiteJobResponse> savedJobs = repository.findSavedJobs(userId);
		assertNotNull(savedJobs);
	}

//	@Test
	void testSaveJobInvalidUserId() {
		UUID userId = UUID.fromString("91a9213b-9f70-44ae-9e76-8fa9891e215d");
		UUID jobId = UUID.fromString("91a9213b-9f70-44ae-9e76-8fa9891e215d");
		assertThrows(UnableToExecuteStatementException.class, () -> repository.saveJob(userId, jobId));
	}

	@Test
	void testSaveJobInvalidJobId() {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("91a9213b-9f70-44ae-9e76-8fa9891e2159");
		assertThrows(UnableToExecuteStatementException.class, () -> repository.saveJob(userId, jobId));
	}

}

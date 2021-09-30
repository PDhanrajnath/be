package com.greencommute.jobmanagementservice.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.greencommute.jobmanagementservice.exception.JobManagementServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.model.ImmutableErrorResponse;
import com.greencommute.jobmanagementservice.service.IUserJobMappingService;
import com.greencommute.jobmanagementservice.utils.Constants;

import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserJobMappingController.class)
class UserJobMappingControllerTest {

	@Autowired
	private MockMvc fMockMvc;

	@Autowired
	private ObjectMapper fObjectMapper;

	@MockBean
	IUserJobMappingService fUserJobMappingService;

	@Test
	void testFetchSavedJob() throws Exception {
		UUID userId = UUID.fromString(Constants.USER_ID);
		List<ImmutableLiteJobResponse> jobs = new ArrayList<>();
		jobs.add(ImmutableLiteJobResponse.builder().title("job title")
				.iD(UUID.fromString("f74cdff8-34d9-4d00-aaa3-eee4e99cd225")).companyLogoUrl("logo")
				.companyName("companyName").datePosted(LocalDate.now()).address("address").build());
		when(fUserJobMappingService.findSavedJobs(userId)).thenReturn(jobs);

		fMockMvc.perform(get("/api/saved-jobs")).andExpect(status().isOk())
				.andExpect(content().string(fObjectMapper.writeValueAsString(jobs)));
	}

	@Test
	void testFetchSavedJobFailure() throws Exception {
		UUID userId = UUID.fromString(Constants.USER_ID);
		when(fUserJobMappingService.findSavedJobs(userId)).thenThrow(new RuntimeException("Unable to retrieve data."));
		ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
				.errorMessage("Request could not be processed due to an internal error.Please try again later.")
				.description("Internal Server Error")
				.statusCode(INTERNAL_SERVER_ERROR.value())
				.timeStamp(Instant.now().toEpochMilli()).build();
		fMockMvc.perform(get("/api/saved-jobs")).andExpect(status().is5xxServerError())
				.andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
				.andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
				.andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
	}

	@Test
	void testSaveJob() throws Exception {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("f74cdff8-34d9-4d00-aaa3-eee4e99cd225");
		fMockMvc.perform(post("/api/saved-jobs/f74cdff8-34d9-4d00-aaa3-eee4e99cd225")).andExpect(status().isOk());

		verify(fUserJobMappingService, times(1)).saveJob(userId, jobId);
	}

	@Test
	void testSaveAlreadySavedJob() throws Exception {
		UUID userId = UUID.fromString(Constants.USER_ID);
		String jobId = "f74cdff8-34d9-4d00-aaa3-eee4e99cd225";
		doThrow(new JobManagementServiceException("Job already present in saved jobs."))
				.when(fUserJobMappingService).saveJob(userId,UUID.fromString(jobId));
		ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
				.errorMessage("Job already present in saved jobs.")
				.description("CONFLICT")
				.statusCode(CONFLICT.value())
				.timeStamp(Instant.now().toEpochMilli()).build();
		fMockMvc.perform(post("/api/saved-jobs/" + jobId))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
				.andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
				.andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
	}

	@Test
	void testUnSaveJob() throws Exception {
		UUID userId = UUID.fromString(Constants.USER_ID);
		UUID jobId = UUID.fromString("f74cdff8-34d9-4d00-aaa3-eee4e99cd225");
		fMockMvc.perform(delete("/api/saved-jobs/f74cdff8-34d9-4d00-aaa3-eee4e99cd225")).andExpect(status().isOk());

		verify(fUserJobMappingService, times(1)).unSaveJob(userId, jobId);
	}

	@Test
	void testUnSaveJobNullParam() throws Exception {

		ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
				.errorMessage("Http method not supported. Method : POST").description("Method not allowed")
				.statusCode(METHOD_NOT_ALLOWED.value()).timeStamp(Instant.now().toEpochMilli()).build();

		fMockMvc.perform(post("/api/saved-jobs")).andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
				.andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
				.andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
	}

	@Test
	void testSaveJobNullParam() throws Exception {
		ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
				.errorMessage("Http method not supported. Method : DELETE").description("Method not allowed")
				.statusCode(METHOD_NOT_ALLOWED.value()).timeStamp(Instant.now().toEpochMilli()).build();

		fMockMvc.perform(delete("/api/saved-jobs")).andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
				.andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
				.andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
	}

	@Test
	void testUnSaveJobInvalidParam() throws Exception {

		ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
				.errorMessage("Invalid input received by the server. input : invalid").description("Bad Request")
				.statusCode(BAD_REQUEST.value()).timeStamp(Instant.now().toEpochMilli()).build();
		fMockMvc.perform(post("/api/saved-jobs/invalid")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
				.andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
				.andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
	}

	@Test
	void testSaveJobInvalidParam() throws Exception {
		ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
				.errorMessage("Invalid input received by the server. input : invalid").description("Bad Request")
				.statusCode(BAD_REQUEST.value()).timeStamp(Instant.now().toEpochMilli()).build();
		fMockMvc.perform(delete("/api/saved-jobs/invalid")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
				.andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
				.andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
	}

}

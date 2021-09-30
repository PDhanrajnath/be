package com.greencommute.jobmanagementservice.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.exception.JobNotFoundException;
import com.greencommute.jobmanagementservice.model.ExperienceLevel;
import com.greencommute.jobmanagementservice.model.ImmutableErrorResponse;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;
import com.greencommute.jobmanagementservice.model.JobType;
import com.greencommute.jobmanagementservice.model.Skills;
import com.greencommute.jobmanagementservice.service.IJobService;


@RunWith(SpringRunner.class)
@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc fMockMvc;

    @Autowired
    private ObjectMapper fObjectMapper;

    @MockBean
    private IJobService fJobService;

    private final ImmutableJobResponse fJob = getJob();

    private final ImmutableLiteJobResponse fJobLite = getJobLite();

    @Test
    void fetchJobsStatusOkTest()  throws Exception {
        ImmutableGetJobsQueryParams filter = ImmutableGetJobsQueryParams.builder()
                .isGreenCommute(true)
                .skills(Skills.JAVA_DEVELOPER)
                .jobType(JobType.CONTRACT)
                .experienceLevel(ExperienceLevel.FRESHER)
                .lat(33.0)
                .lon(45.0)
                .distance(10)
                .build();
        ImmutableGetJobsQueryParams noFilter = ImmutableGetJobsQueryParams.builder().build();
        Mockito.when(fJobService.findAllJobs(filter)).thenReturn(List.of(fJobLite));
        fMockMvc.perform(get("/api/jobs?is_green_commute=true&skill=JAVA_DEVELOPER&job_type=CONTRACT&lat=33.0&lon=45.0&distance=10&experience_level=FRESHER"))
                .andExpect(status().isOk())
                .andExpect(content().string(fObjectMapper.writeValueAsString(List.of(fJobLite))));
        Mockito.when(fJobService.findAllJobs(noFilter)).thenReturn(List.of());
        fMockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().string(fObjectMapper.writeValueAsString(List.of())));
    }

    @Test
    void fetchJobsStatusBadRequestTest()  throws Exception {
        ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage("Invalid input received by the server. input : random")
                .description("Bad Request")
                .statusCode(BAD_REQUEST.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        fMockMvc.perform(get("/api/jobs?is_green_commute=random"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
                .andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
                .andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));

        fMockMvc.perform(get("/api/jobs?job_type=random"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
                .andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
                .andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));

        fMockMvc.perform(get("/api/jobs?distance=random"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
                .andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
                .andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
    }

    @Test
    void fetchJobBYIdStatusOkTest() throws Exception {
        Mockito.when(fJobService.findJobById(fJob.getID())).thenReturn(fJob);
        fMockMvc.perform(get("/api/jobs/" + fJob.getID()))
                .andExpect(status().isOk())
                .andExpect(content().string(fObjectMapper.writeValueAsString(fJob)));
    }

    @Test
    void fetchJobBYIdStatusNotFoundTest() throws Exception {
        Mockito.when(fJobService.findJobById(fJob.getID())).thenThrow(new JobNotFoundException("Invalid Job Id"));
        ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage("Invalid Job Id")
                .description("Not Found")
                .statusCode(NOT_FOUND.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        fMockMvc.perform(get("/api/jobs/" + fJob.getID()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
                .andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
                .andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
    }


    @Test
    void fetchJobBYIdStatusBadRequestTest() throws Exception {
        ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage("Invalid input received by the server. input : invalid")
                .description("Bad Request")
                .statusCode(BAD_REQUEST.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        fMockMvc.perform(get("/api/jobs/invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message").value(errorResponse.getErrorMessage()))
                .andExpect(jsonPath("$.description").value(errorResponse.getDescription()))
                .andExpect(jsonPath("$.status_code").value(errorResponse.getStatusCode()));
    }

    @Test
    void fetchSkillsStatusOkTest() throws Exception {
        fMockMvc.perform(get("/api/skills/"))
                .andExpect(status().isOk())
                .andExpect(content().string(fObjectMapper.writeValueAsString(Arrays.asList(Skills.values()))));
    }


    private ImmutableJobResponse getJob(){
        Double[] location = {33.0,45.0};
        return ImmutableJobResponse.builder()
                .jobType(JobType.CONTRACT)
                .experienceLevel(ExperienceLevel.FRESHER)
                .address("Adikmet, Hyderabad")
                .companyLogoUrl("https://m.media-amazon.com/images/I/61b2FkbZsEL._AC_SS350_.jpg")
                .addAllRequirements(List.of("Skilled in Java."))
                .addAllSkills(List.of(Skills.JAVA_DEVELOPER))
                .companyName("Zemoso")
                .datePosted(LocalDate.of(2021,1,1))
                .description("job")
                .isActive(true)
                .isGreenCommute(true)
                .location(location)
                .iD(UUID.fromString("5d60f30f-359c-4735-b9f2-2a76270c1d90"))
                .title("Java Developer")
                .build();
    }

    private ImmutableLiteJobResponse getJobLite(){
        return ImmutableLiteJobResponse.builder()
                .address(fJob.getAddress())
                .companyLogoUrl(fJob.getCompanyLogoUrl())
                .companyName(fJob.getCompanyName())
                .datePosted(fJob.getDatePosted())
                .iD(fJob.getID())
                .title(fJob.getTitle())
                .build();
    }
}

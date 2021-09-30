package com.greencommute.jobmanagementservice.service.details;

import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.exception.JobNotFoundException;
import com.greencommute.jobmanagementservice.model.ExperienceLevel;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;
import com.greencommute.jobmanagementservice.model.JobType;
import com.greencommute.jobmanagementservice.model.Skills;
import com.greencommute.jobmanagementservice.persistence.IJobRepository;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private IJobRepository jobRepository;

    private JobService service;
//
//    @Before
//    public void setup() {
//        service = new JobService(jobRepository);
//    }

    @Test
    void testFindAllJobsEmptyFilter() {
        service = new JobService(jobRepository);
        ImmutableGetJobsQueryParams filter = ImmutableGetJobsQueryParams.builder().build();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2020-11-12", formatter);

        List<ImmutableLiteJobResponse> jobs = new ArrayList<ImmutableLiteJobResponse>();
        ImmutableLiteJobResponse job = ImmutableLiteJobResponse.builder()
                .iD(UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf"))
                .companyName("Zemoso")
                .title("Software Engineer")
                .companyLogoUrl("www,google.com")
                .address("Raidurg, Telangana")
                .datePosted(date)
                .build();
        jobs.add(job);

        when(jobRepository.findAllJobs(filter)).thenReturn(jobs);

        List<ImmutableLiteJobResponse> resJobs = service.findAllJobs(filter);

        assertNotNull(resJobs);
        assertEquals(jobs, resJobs);
    }

    @Test
    void testFindAllJobsFilter() {
        service = new JobService(jobRepository);
        ImmutableGetJobsQueryParams filter = ImmutableGetJobsQueryParams.builder()
                .isGreenCommute(true)
                .skills(Skills.PRODUCT_MANAGER)
                .jobType(JobType.FULL_TIME)
                .experienceLevel(ExperienceLevel.FRESHER)
                .lat(17.4326)
                .lon(78.4071)
                .distance(20000)
                .build();

        List<ImmutableLiteJobResponse> jobs = new ArrayList<ImmutableLiteJobResponse>();

        when(jobRepository.findAllJobs(filter)).thenReturn(jobs);

        List<ImmutableLiteJobResponse> resJobs = service.findAllJobs(filter);

        assertNotNull(resJobs);
        assertEquals(jobs, resJobs);
    }

    @Test
    void testFindJobById() {
        service = new JobService(jobRepository);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2020-11-12", formatter);
        Double[] location = new Double[]{17.4326, 78.4071};
        UUID jobId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");

        ImmutableJobResponse job = ImmutableJobResponse.builder()
                .iD(jobId)
                .title("Product Manager")
                .isActive(true)
                .datePosted(date)
                .description("test description")
                .location(location)
                .companyLogoUrl("www.google.com")
                .companyName("Test123")
                .isGreenCommute(true)
                .jobType(JobType.FULL_TIME)
                .experienceLevel(ExperienceLevel.FRESHER)
                .address("Raidurg, Hyderabad")
                .build();

        when(jobRepository.findJobById(jobId)).thenReturn(job);

        ImmutableJobResponse resJob = service.findJobById(jobId);

        assertNotNull(resJob);
        assertEquals(job, resJob);
    }

    @Test
    void testFindJobByIdFail() {
        service = new JobService(jobRepository);
        UUID jobId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        doThrow(UnableToExecuteStatementException.class).when(jobRepository).findJobById(jobId);
        assertThrows(JobNotFoundException.class, () -> service.findJobById(jobId));
    }

}
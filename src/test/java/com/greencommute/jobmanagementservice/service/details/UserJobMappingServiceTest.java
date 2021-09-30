package com.greencommute.jobmanagementservice.service.details;

import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.exception.JobManagementServiceException;
import com.greencommute.jobmanagementservice.exception.JobNotFoundException;
import com.greencommute.jobmanagementservice.persistence.IUserJobMappingRepository;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserJobMappingServiceTest {
    @Mock
    private IUserJobMappingRepository userJobMappingRepository;

    private UserJobMappingService service;

    @Test
    void testFindSavedJobs() {
        service = new UserJobMappingService(userJobMappingRepository);
        List<ImmutableLiteJobResponse> jobs = new ArrayList<ImmutableLiteJobResponse>();
        UUID userId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        when(userJobMappingRepository.findSavedJobs(userId)).thenReturn(jobs);
        List<ImmutableLiteJobResponse> resJobs = service.findSavedJobs(userId);
        assertNotNull(resJobs);
        assertEquals(jobs, resJobs);
    }

    @Test
    void testSaveJob() {
        service = new UserJobMappingService(userJobMappingRepository);
        UUID userId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        UUID jobId = UUID.fromString("551b1431-52f3-4efe-be4e-4342599a19bf");
        service.saveJob(userId, jobId);
        verify(userJobMappingRepository,times(1)).saveJob(userId,jobId);
    }

    @Test
    void testSaveJobFailed() {
        service = new UserJobMappingService(userJobMappingRepository);
        UUID userId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        UUID jobId = UUID.fromString("551b1431-52f3-4efe-be4e-4342599a19bf");
        doThrow(UnableToExecuteStatementException.class).when(userJobMappingRepository).saveJob(userId,jobId);
        assertThrows(JobManagementServiceException.class, () -> service.saveJob(userId, jobId));
    }

    @Test
    void testUnSaveJob() {
        service = new UserJobMappingService(userJobMappingRepository);
        UUID userId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        UUID jobId = UUID.fromString("551b1431-52f3-4efe-be4e-4342599a19bf");
        List<ImmutableLiteJobResponse> jobs = new ArrayList<ImmutableLiteJobResponse>();
        jobs.add(ImmutableLiteJobResponse.builder()
                .iD(jobId)
                .companyName("Zemoso")
                .title("Software Engineer")
                .companyLogoUrl("www,google.com")
                .address("Raidurg, Telangana")
                .datePosted(LocalDate.now())
                .build());
        when(userJobMappingRepository.findSavedJobs(userId)).thenReturn(jobs);
        service.unSaveJob(userId, jobId);
        verify(userJobMappingRepository,times(1)).unSaveJob(userId,jobId);
    }

    @Test
    void testUnSaveJobFailed() {
        service = new UserJobMappingService(userJobMappingRepository);
        UUID userId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        UUID jobId = UUID.fromString("551b1431-52f3-4efe-be4e-4342599a19bf");
        List<ImmutableLiteJobResponse> jobs = new ArrayList<ImmutableLiteJobResponse>();
        jobs.add(ImmutableLiteJobResponse.builder()
                .iD(jobId)
                .companyName("Zemoso")
                .title("Software Engineer")
                .companyLogoUrl("www,google.com")
                .address("Raidurg, Telangana")
                .datePosted(LocalDate.now())
                .build());
        when(userJobMappingRepository.findSavedJobs(userId)).thenReturn(jobs);
        doThrow(UnableToExecuteStatementException.class).when(userJobMappingRepository).unSaveJob(userId,jobId);
        assertThrows(JobManagementServiceException.class, () -> service.unSaveJob(userId, jobId));
    }

    @Test
    void testUnSaveJobNotFound() {
        service = new UserJobMappingService(userJobMappingRepository);
        UUID userId = UUID.fromString("451b1231-52f3-4efe-be4e-4342599a19bf");
        UUID jobId = UUID.fromString("551b1431-52f3-4efe-be4e-4342599a19bf");
        List<ImmutableLiteJobResponse> jobs = new ArrayList<ImmutableLiteJobResponse>();
        when(userJobMappingRepository.findSavedJobs(userId)).thenReturn(jobs);
        assertThrows(JobNotFoundException.class, () -> service.unSaveJob(userId, jobId));
    }
}

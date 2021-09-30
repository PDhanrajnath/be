package com.greencommute.jobmanagementservice.persistence;

import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;

import java.util.List;
import java.util.UUID;

public interface IUserJobMappingRepository {

    List<ImmutableLiteJobResponse> findSavedJobs(UUID userId);

    void saveJob(UUID userId, UUID jobId);

    void unSaveJob(UUID userId, UUID jobId);
}

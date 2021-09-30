package com.greencommute.jobmanagementservice.persistence;

import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;

import java.util.List;
import java.util.UUID;

public interface IJobRepository {

    List<ImmutableLiteJobResponse> findAllJobs(ImmutableGetJobsQueryParams filter);

    ImmutableJobResponse findJobById(UUID jobId);
}

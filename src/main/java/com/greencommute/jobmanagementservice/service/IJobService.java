package com.greencommute.jobmanagementservice.service;

import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;

import java.util.List;
import java.util.UUID;

public interface IJobService {

    List<ImmutableLiteJobResponse> findAllJobs(ImmutableGetJobsQueryParams filter);

    ImmutableJobResponse findJobById(UUID jobId);
}

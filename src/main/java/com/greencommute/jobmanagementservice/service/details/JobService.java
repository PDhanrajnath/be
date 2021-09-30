package com.greencommute.jobmanagementservice.service.details;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.exception.JobNotFoundException;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;
import com.greencommute.jobmanagementservice.persistence.IJobRepository;
import com.greencommute.jobmanagementservice.service.IJobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService implements IJobService {

    private final IJobRepository fJobRepository;

    public JobService(final IJobRepository jobRepository){
        Preconditions.checkNotNull(jobRepository, "Job repository");
        fJobRepository = jobRepository;
    }

    @Override
    public List<ImmutableLiteJobResponse> findAllJobs(final ImmutableGetJobsQueryParams filter) {
        return fJobRepository.findAllJobs(filter);
    }

    @Override
    public ImmutableJobResponse findJobById(final UUID jobId) {
        Preconditions.checkNotNull(jobId, "job id");
        ImmutableJobResponse job;
        try {
            job = fJobRepository.findJobById(jobId);
        }
        catch (Exception e){
            throw new JobNotFoundException("Invalid Job Id");
        }
        return job;
    }
}

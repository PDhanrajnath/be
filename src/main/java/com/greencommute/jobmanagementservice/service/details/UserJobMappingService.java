package com.greencommute.jobmanagementservice.service.details;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.exception.JobManagementServiceException;
import com.greencommute.jobmanagementservice.exception.JobNotFoundException;
import com.greencommute.jobmanagementservice.persistence.IUserJobMappingRepository;
import com.greencommute.jobmanagementservice.service.IUserJobMappingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserJobMappingService implements IUserJobMappingService {

    private final IUserJobMappingRepository fUserJobMappingRepository;

    public UserJobMappingService(IUserJobMappingRepository userJobMappingRepository) {
        this.fUserJobMappingRepository = userJobMappingRepository;
    }

    @Override
    public List<ImmutableLiteJobResponse> findSavedJobs(final UUID userId) {
        Preconditions.checkNotNull(userId, "User id");
        return fUserJobMappingRepository.findSavedJobs(userId);
    }

    @Override
    public void saveJob(final UUID userId, final UUID jobId) {
        Preconditions.checkNotNull(userId, "Null User id");
        Preconditions.checkNotNull(jobId, "Job id");
        try {
            fUserJobMappingRepository.saveJob(userId, jobId);
        }
        catch (Exception e){
            throw new JobManagementServiceException("Job already present in saved jobs.");
        }

    }

    @Override
    public void unSaveJob(final UUID userId, final UUID jobId) {
        Preconditions.checkNotNull(userId, "User id");
        Preconditions.checkNotNull(jobId, "Job id");
        if (findSavedJobs(userId).stream().noneMatch(x -> x.getID().equals(jobId))){
            throw new JobNotFoundException("Job not found");
        }
        try {
            fUserJobMappingRepository.unSaveJob(userId, jobId);
        }
        catch (Exception e){
            throw new JobManagementServiceException("Unable to unsave the job.");
        }
    }
}

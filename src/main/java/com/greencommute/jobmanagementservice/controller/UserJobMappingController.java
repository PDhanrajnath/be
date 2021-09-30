package com.greencommute.jobmanagementservice.controller;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.service.IUserJobMappingService;
import com.greencommute.jobmanagementservice.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserJobMappingController {

    private final IUserJobMappingService fUserJobMappingService;

    public UserJobMappingController(final IUserJobMappingService userJobMappingService) {
        Preconditions.checkNotNull(userJobMappingService, "Job Mapping Service");
        fUserJobMappingService = userJobMappingService;
    }

    /**
     *<p>This method will fetch saved jobs for a user after applying the search filters</p>
     * @return jobResponseList
     */
    @GetMapping(value = "/api/saved-jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ImmutableLiteJobResponse>> fetchSavedJob() {

        List<ImmutableLiteJobResponse> jobList=fUserJobMappingService.findSavedJobs(UUID.fromString(Constants.USER_ID));

        return ResponseEntity.ok().body(jobList);
    }

    /**
     *<p>This method will save the job details by an id for user</p>
     * @return response
     */
    @PostMapping(value = "/api/saved-jobs/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveJob(@PathVariable final UUID jobId) {
        Preconditions.checkNotNull(jobId, "Job id");
        fUserJobMappingService.saveJob(UUID.fromString(Constants.USER_ID), jobId);

        return ResponseEntity.ok("Job saved in the user's saved jobs list.");
    }

    /**
     *<p>This method will remove the job details by an id for user</p>
     * @return response
     */
    @DeleteMapping(value = "/api/saved-jobs/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> unSaveJob( @PathVariable final UUID jobId) {
        Preconditions.checkNotNull(jobId, "Job id");
        fUserJobMappingService.unSaveJob(UUID.fromString(Constants.USER_ID), jobId);

        return ResponseEntity.ok("Job removed from the user's saved jobs list.");
    }

}

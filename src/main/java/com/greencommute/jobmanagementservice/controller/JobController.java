package com.greencommute.jobmanagementservice.controller;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.model.ExperienceLevel;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;
import com.greencommute.jobmanagementservice.model.JobType;
import com.greencommute.jobmanagementservice.model.Skills;
import com.greencommute.jobmanagementservice.service.IJobService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class JobController {

    private final IJobService fJobService;

    public JobController(final IJobService jobService) {
        Preconditions.checkNotNull(jobService, "Job Service");
        fJobService = jobService;
    }

    /**
     *<p>This method will fetch all jobs after applying the search filters</p>
     * @return jobResponseList
     */
    @GetMapping(value = "/api/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ImmutableLiteJobResponse>> fetchAllJob(
            @RequestParam(required = false, name = "is_green_commute") final Optional<Boolean> isGreenCommute,
            @RequestParam(required = false, name = "skill") final Optional<Skills> skills,
            @RequestParam(required = false, name = "lat") final Optional<Double> lat,
            @RequestParam(required = false, name = "lon") final Optional<Double> lon,
            @RequestParam(required = false, name = "distance") final Optional<Integer> distance,
            @RequestParam(required = false, name = "job_type") final Optional<JobType> jobType,
            @RequestParam(required = false, name = "experience_level") final Optional<ExperienceLevel> expLevel) {

        ImmutableGetJobsQueryParams filter = ImmutableGetJobsQueryParams.builder()
                .isGreenCommute(isGreenCommute)
                .skills(skills)
                .jobType(jobType)
                .experienceLevel(expLevel)
                .lat(lat)
                .lon(lon)
                .distance(distance)
                .build();

        return ResponseEntity.ok().body(fJobService.findAllJobs(filter));
    }

    /**
     *<p>This method will fetch the job details by an id</p>
     * @return jobResponse
     */
    @GetMapping(value = "/api/jobs/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImmutableJobResponse> fetchJobById( @PathVariable final UUID jobId) {
        Preconditions.checkNotNull(jobId, "Job id");
        ImmutableJobResponse job=fJobService.findJobById(jobId);

        return ResponseEntity.ok().body(job);
    }

    /**
     *<p>This method will fetch the skills present in the database</p>
     * @return skillsList
     */
    @GetMapping(value = "/api/skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Skills>> fetchAllSkills() {
        return ResponseEntity.ok().body(Arrays.asList(Skills.values()));
    }

}

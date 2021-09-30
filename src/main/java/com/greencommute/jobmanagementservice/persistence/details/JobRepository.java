package com.greencommute.jobmanagementservice.persistence.details;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.model.ImmutableGetJobsQueryParams;
import com.greencommute.jobmanagementservice.persistence.IJobRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class JobRepository implements IJobRepository {

    private final Jdbi fJdbi;

    static final String GET_JOBS_QUERY = "select * from fn_fetch_jobs(:isGreenCommute, :skills, :jobType, :experienceLevel, :geography, :distance) join organisations ON company_id=organisations.id ;";
    static final String GET_JOB_BY_ID_QUERY = "select * from jobs join organisations ON jobs.company_id=organisations.id where jobs.id=:jobId";

    public JobRepository(final DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "Jobs data source");
        fJdbi = Jdbi.create(dataSource);
    }

    @Override
    public List<ImmutableLiteJobResponse> findAllJobs(final ImmutableGetJobsQueryParams filter) {
        Preconditions.checkNotNull(filter, "Filter");
        Optional<Double> lat = filter.getLat();
        Optional<Double> lon = filter.getLon();
        Optional<Integer> distance = filter.getDistance();
        final String geography = lat.isPresent() && lon.isPresent() && distance.isPresent() ?
                "POINT("+ lat.get() + " " + lon.get() + ")" : null;
        return fJdbi.withHandle(handle ->
                handle.createQuery(GET_JOBS_QUERY)
                    .bind("isGreenCommute", filter.getIsGreenCommute())
                    .bind("skills", filter.getSkills())
                    .bind("jobType", filter.getJobType())
                    .bind("experienceLevel", filter.getExperienceLevel())
                    .bind("geography", geography)
                    .bind("distance", distance.orElse(null))
                    .map(new LiteJobResponseMapper())
                    .list());
    }

    @Override
    public ImmutableJobResponse findJobById(final UUID jobId) {
        Preconditions.checkNotNull(jobId, "Job id");

        return fJdbi.withHandle(handle ->
                handle.createQuery(GET_JOB_BY_ID_QUERY)
                        .bind("jobId", jobId)
                        .map(new JobResponseMapper())
                        .one());
    }


}

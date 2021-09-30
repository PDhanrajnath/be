package com.greencommute.jobmanagementservice.persistence.details;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.persistence.IUserJobMappingRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class UserJobMappingRepository implements IUserJobMappingRepository {

    private final Jdbi fJdbi;

    public UserJobMappingRepository(final DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "UserJobMapping data source");
        fJdbi = Jdbi.create(dataSource);
    }

    static final String USER_ID_PARAM = "userId";

    static final String GET_SAVED_JOB_BY_USER_ID_QUERY = "SELECT j.*, o.* FROM user_job_mappings m JOIN jobs j ON  m.job_id=j.id AND m.user_id=:userId JOIN organisations o ON j.company_id=o.id";
    static final String SAVE_JOB_QUERY = "INSERT INTO user_job_mappings (user_id, job_id) VALUES (:userId, :jobId)";
    static final String UNSAVE_JOB_QUERY = "DELETE FROM user_job_mappings WHERE user_id=:userId AND job_id=:jobId";

    @Override
    public List<ImmutableLiteJobResponse> findSavedJobs(final UUID userId) {
        Preconditions.checkNotNull(userId, "User id");
        return fJdbi.withHandle(handle ->
                handle.createQuery(GET_SAVED_JOB_BY_USER_ID_QUERY)
                        .bind(USER_ID_PARAM, userId)
                        .map(new LiteJobResponseMapper())
                        .list());
    }

    @Override
    public void saveJob(final UUID userId, final UUID jobId) {
        Preconditions.checkNotNull(userId, "Null user id");
        Preconditions.checkNotNull(jobId, "Job id");
        fJdbi.withHandle(handle ->
                handle.createUpdate(SAVE_JOB_QUERY)
                        .bind(USER_ID_PARAM, userId)
                        .bind("jobId", jobId)
                        .execute());
    }

    @Override
    public void unSaveJob(final UUID userId, final UUID jobId) {
        Preconditions.checkNotNull(userId, "User id");
        Preconditions.checkNotNull(jobId, "Job id");
        fJdbi.withHandle(handle ->
                handle.createUpdate(UNSAVE_JOB_QUERY)
                        .bind(USER_ID_PARAM, userId)
                        .bind("jobId", jobId)
                        .execute());
    }
}

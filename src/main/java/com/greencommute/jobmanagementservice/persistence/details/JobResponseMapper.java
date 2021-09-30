package com.greencommute.jobmanagementservice.persistence.details;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableJobResponse;
import com.greencommute.jobmanagementservice.model.ExperienceLevel;
import com.greencommute.jobmanagementservice.model.JobType;
import com.greencommute.jobmanagementservice.model.Skills;
import com.greencommute.jobmanagementservice.utils.Constants;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class JobResponseMapper implements RowMapper<ImmutableJobResponse> {

    private List<Skills> convertSkills(final String[] args){
        Preconditions.checkNotNull(args, "Skills array");
        return Arrays.stream(args).map(Skills::valueOf).collect(Collectors.toList());
    }

    private List<String> convertRequirements(final String[] args){
        Preconditions.checkNotNull(args, "Requirements array");
        return Arrays.stream(args).collect(Collectors.toList());
    }

    @Override
    public ImmutableJobResponse map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        Preconditions.checkNotNull(rs, "results stream");
        Preconditions.checkNotNull(ctx, "context");
        return  ImmutableJobResponse.builder()
                .iD(UUID.fromString(rs.getString(Constants.ID)))
                .title(rs.getString(Constants.TITLE))
                .isActive(rs.getBoolean(Constants.IS_ACTIVE))
                .jobType(JobType.valueOf(rs.getString(Constants.JOB_TYPE)))
                .isGreenCommute(rs.getBoolean(Constants.IS_GREEN_COMMUTE))
                .datePosted(rs.getDate(Constants.DATE_POSTED).toLocalDate())
                .description(rs.getString(Constants.DESCRIPTION))
                .address(rs.getString(Constants.ADDRESS))
                .experienceLevel(ExperienceLevel.valueOf(rs.getString(Constants.EXPERIENCE_LEVEL)))
                .companyName(rs.getString(Constants.COMPANY_NAME))
                .companyLogoUrl(rs.getString(Constants.COMPANY_LOGO_URL))
                .skills(convertSkills((String[]) rs.getArray(Constants.SKILLS).getArray()))
                .location((Double[]) rs.getArray(Constants.LOCATION).getArray())
                .requirements(convertRequirements((String[]) rs.getArray(Constants.REQUIREMENTS).getArray()))
                .build();
    }
}

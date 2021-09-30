package com.greencommute.jobmanagementservice.persistence.details;

import com.google.common.base.Preconditions;
import com.greencommute.jobmanagementservice.dto.response.ImmutableLiteJobResponse;
import com.greencommute.jobmanagementservice.utils.Constants;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class LiteJobResponseMapper implements RowMapper<ImmutableLiteJobResponse> {
    
    @Override
    public ImmutableLiteJobResponse map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        Preconditions.checkNotNull(rs, "results stream");
        Preconditions.checkNotNull(ctx, "context");
        return  ImmutableLiteJobResponse.builder()
                .iD(UUID.fromString(rs.getString(Constants.ID)))
                .title(rs.getString(Constants.TITLE))
                .companyName(rs.getString(Constants.COMPANY_NAME))
                .companyLogoUrl(rs.getString(Constants.COMPANY_LOGO_URL))
                .datePosted(rs.getDate(Constants.DATE_POSTED).toLocalDate())
                .address(rs.getString(Constants.ADDRESS))
                .build();
    }
}

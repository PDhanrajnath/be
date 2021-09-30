package com.greencommute.jobmanagementservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value.Immutable
public abstract class LiteJobResponse {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DATE_POSTED = "date_posted";
    public static final String ADDRESS = "address";
    public static final String COMPANY_LOGO_URL = "company_logo_url";
    public static final String COMPANY_NAME = "name";

    @JsonProperty(ID)
    public abstract UUID getID();

    @JsonProperty(TITLE)
    public abstract String getTitle();

    @JsonProperty(DATE_POSTED)
    public abstract LocalDate getDatePosted();

    @JsonProperty(ADDRESS)
    public abstract String getAddress();

    @JsonProperty(COMPANY_LOGO_URL)
    public abstract String getCompanyLogoUrl();

    @JsonProperty(COMPANY_NAME)
    public abstract String getCompanyName();

}

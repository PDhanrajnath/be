package com.greencommute.jobmanagementservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greencommute.jobmanagementservice.model.ExperienceLevel;
import com.greencommute.jobmanagementservice.model.JobType;
import com.greencommute.jobmanagementservice.model.Skills;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public abstract class JobResponse {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String IS_ACTIVE = "is_active";
    public static final String DATE_POSTED = "date_posted";
    public static final String DESCRIPTION = "description";
    public static final String SKILLS = "skills";
    public static final String REQUIREMENTS = "requirements";
    public static final String ADDRESS = "address";
    public static final String LOCATION = "location_point";
    public static final String COMPANY_LOGO_URL = "company_logo_url";
    public static final String COMPANY_NAME = "name";
    public static final String IS_GREEN_COMMUTE = "is_green_commute";
    public static final String JOB_TYPE = "job_type";
    public static final String EXPERIENCE_LEVEL = "experience_level";

    @JsonProperty(ID)
    public abstract UUID getID();

    @JsonProperty(TITLE)
    public abstract String getTitle();

    @JsonProperty(IS_ACTIVE)
    public abstract Boolean getIsActive();

    @JsonProperty(DATE_POSTED)
    public abstract LocalDate getDatePosted();

    @JsonProperty(DESCRIPTION)
    public abstract String getDescription();

    @JsonProperty(SKILLS)
    public abstract List<Skills> getSkills();

    @JsonProperty(REQUIREMENTS)
    public abstract List<String> getRequirements();

    @JsonProperty(ADDRESS)
    public abstract String getAddress();

    @JsonProperty(LOCATION)
    public abstract Double[] getLocation();

    @JsonProperty(COMPANY_LOGO_URL)
    public abstract String getCompanyLogoUrl();

    @JsonProperty(COMPANY_NAME)
    public abstract String getCompanyName();

    @JsonProperty(IS_GREEN_COMMUTE)
    public abstract Boolean getIsGreenCommute();

    @JsonProperty(JOB_TYPE)
    public abstract JobType getJobType();

    @JsonProperty(EXPERIENCE_LEVEL)
    public abstract ExperienceLevel getExperienceLevel();


}

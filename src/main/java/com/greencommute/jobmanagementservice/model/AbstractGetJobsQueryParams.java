package com.greencommute.jobmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public abstract class AbstractGetJobsQueryParams {

    public static final String SKILLS = "skills";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String DISTANCE = "distance";
    public static final String IS_GREEN_COMMUTE = "is_green_commute";
    public static final String JOB_TYPE = "job_type";
    public static final String EXPERIENCE_LEVEL = "experience_level";

    @JsonProperty(SKILLS)
    public abstract Optional<Skills> getSkills();

    @JsonProperty(LAT)
    public abstract Optional<Double> getLat();

    @JsonProperty(LON)
    public abstract Optional<Double> getLon();

    @JsonProperty(DISTANCE)
    public abstract Optional<Integer> getDistance();

    @JsonProperty(IS_GREEN_COMMUTE)
    public abstract Optional<Boolean> getIsGreenCommute();

    @JsonProperty(JOB_TYPE)
    public abstract Optional<JobType> getJobType();

    @JsonProperty(EXPERIENCE_LEVEL)
    public abstract Optional<ExperienceLevel> getExperienceLevel();

    @Value.Check
    void check() {
        Optional<Double> lat = getLat();
        Optional<Double> lon = getLon();
        Optional<Integer> distance = getDistance();
        lat.ifPresent(latitude -> Preconditions.checkArgument(latitude >= -90 || latitude <= 90,
                "Lat should be between -90 and 90"));
        lon.ifPresent(longitude -> Preconditions.checkArgument(longitude >= -180 || longitude <= 180,
                "lon should be between -180 and 180"));
        distance.ifPresent(distanceFromPoint -> Preconditions.checkArgument(distanceFromPoint > 0,
                "distance Should be greater than zero"));
    }
}

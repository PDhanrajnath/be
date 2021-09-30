package com.greencommute.jobmanagementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractErrorResponse {

    private static final String TIMESTAMP = "timestamp";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String DESCRIPTION = "description";
    private static final String  STATUS_CODE="status_code";

    @JsonProperty(TIMESTAMP)
    public abstract long getTimeStamp();

    @JsonProperty(ERROR_MESSAGE)
    public abstract String getErrorMessage();

    @JsonProperty(STATUS_CODE)
    public abstract int getStatusCode();

    @JsonProperty(DESCRIPTION)
    public abstract String getDescription();
}

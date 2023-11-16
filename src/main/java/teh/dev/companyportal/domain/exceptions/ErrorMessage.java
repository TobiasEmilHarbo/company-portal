package teh.dev.companyportal.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    COMPANY_NOT_FOUND("Company not found."),
    INVALID_SOCIAL_SECURITY_NUMBER("Social security number is invalid."),
    RESOURCE_PERMISSION_MISSING("You do not have permission to access this resource.");

    @JsonValue
    private final String value;
}

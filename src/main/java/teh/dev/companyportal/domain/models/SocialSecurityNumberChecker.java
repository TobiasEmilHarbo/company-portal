package teh.dev.companyportal.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocialSecurityNumberChecker {
    private boolean isValid;
}

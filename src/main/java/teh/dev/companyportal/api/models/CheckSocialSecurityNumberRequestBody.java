package teh.dev.companyportal.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSocialSecurityNumberRequestBody {
    private String socialSecurityNumber;
}

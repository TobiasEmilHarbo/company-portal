package teh.dev.companyportal.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerData {
    private String name;
    private String socialSecurityNumber;
}

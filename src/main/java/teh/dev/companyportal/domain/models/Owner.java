package teh.dev.companyportal.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Owner {
    private String name;
    private String socialSecurityNumber;
}

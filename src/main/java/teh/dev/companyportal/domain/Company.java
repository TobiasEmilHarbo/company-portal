package teh.dev.companyportal.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private String name;
    private String country;
    private String phoneNumber;
}

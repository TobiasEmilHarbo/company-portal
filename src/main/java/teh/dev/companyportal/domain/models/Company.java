package teh.dev.companyportal.domain.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Company {
    private String id;
    private String name;
    private String country;
    private String phoneNumber;
    private List<String> ownerIds;
}

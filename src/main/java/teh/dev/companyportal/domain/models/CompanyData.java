package teh.dev.companyportal.domain.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents the raw data of a company
 */
@Data
@Builder
public class CompanyData {
    private String name;
    private String country;
    private String phoneNumber;
    private List<String> ownerIds;
}

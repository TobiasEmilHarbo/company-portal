package teh.dev.companyportal.api.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddCompanyRequestBody {
    private String name;
    private String country;
    private String phoneNumber;
    private List<String> ownerIds;
}

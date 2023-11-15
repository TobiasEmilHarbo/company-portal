package teh.dev.companyportal.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddOwnerRequestBody {
    private String name;
    private String socialSecurityNumber;
}

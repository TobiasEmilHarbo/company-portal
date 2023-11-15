package teh.dev.companyportal.api.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddOwnerRequestBody {
    private String socialSecurityNumber;
}

package teh.dev.companyportal.external.socialSecurityNumber.api.models;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String socialSecurityNumber;
}

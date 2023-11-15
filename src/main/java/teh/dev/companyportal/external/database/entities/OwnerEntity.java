package teh.dev.companyportal.external.database.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OwnerEntity {
    private String id;
    private String name;
    private String socialSecurityNumber;
    private LocalDateTime created;
    private LocalDateTime updated;
}

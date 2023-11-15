package teh.dev.companyportal.external.database.entities;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CompanyEntity {
    String id;
    String name;
    String country;
    String phoneNumber;
    List<String> ownerIds;
    LocalDateTime created;
    LocalDateTime updated;
}

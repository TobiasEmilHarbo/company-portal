package teh.dev.companyportal.external.database;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.domain.models.OwnerData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class DataGeneratorService {
    private final Faker faker = new Faker(Locale.of("da-DK"));

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public CompanyData generateCompanyData() {
        return CompanyData.builder()
                .name(faker.company().name())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .country("DK")
                .ownerIds(List.of())
            .build();
    }

    public OwnerData generateOwnerData() {
        return OwnerData.builder()
                .name(faker.name().name())
                .socialSecurityNumber(faker.number().digits(8))
                .build();
    }
}

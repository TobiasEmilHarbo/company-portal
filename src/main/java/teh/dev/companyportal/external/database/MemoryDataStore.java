package teh.dev.companyportal.external.database;

import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.external.database.entities.CompanyEntity;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MemoryDataStore implements DataStore {
    private final Map<String, CompanyEntity> companyStore;

    public MemoryDataStore() {
        this.companyStore = new HashMap<>();

        this.createCompany(CompanyData.builder()
                        .phoneNumber("12345678")
                        .country("DK")
                        .name("ACME")
                .build());

        this.createCompany(CompanyData.builder()
                .phoneNumber("7645788")
                .country("DK")
                .name("ACME 2")
                .build());
    }

    @Override
    public List<Company> getCompanies() {
        return this.companyStore.values().stream()
                .map((companyEntity -> Company.builder()
                    .id(companyEntity.getId())
                    .country(companyEntity.getCountry())
                    .name(companyEntity.getName())
                    .phoneNumber(companyEntity.getPhoneNumber())
                .build())).toList();
    }

    @Override
    public Company getCompany(String id) {
        CompanyEntity companyEntity = this.companyStore.get(id);

        if(Objects.isNull(companyEntity)) {
            return null;
        }

        return Company.builder()
                .id(companyEntity.getId())
                .name(companyEntity.getName())
                .phoneNumber(companyEntity.getPhoneNumber()).build();
    }

    @Override
    public Company createCompany(CompanyData company) {
        String id = UUID.randomUUID().toString();
        LocalDateTime createDate = LocalDateTime.now();

        CompanyEntity entity = CompanyEntity.builder()
                .id(id)
                .name(company.getName())
                .country(company.getCountry())
                .phoneNumber(company.getPhoneNumber())
                .ownerIds(company.getOwnerIds())
                .created(createDate)
                .updated(createDate)
            .build();

        this.companyStore.put(id, entity);

        return getCompany(id);
    }
}

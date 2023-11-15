package teh.dev.companyportal.external.database;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.domain.models.Owner;
import teh.dev.companyportal.domain.models.OwnerData;
import teh.dev.companyportal.external.database.entities.CompanyEntity;
import teh.dev.companyportal.external.database.entities.OwnerEntity;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MemoryDataStore implements DataStore {
    private final Map<String, OwnerEntity> ownerStore;
    private final Map<String, CompanyEntity> companyStore;

    public MemoryDataStore() {
        Faker faker = new Faker(Locale.of("da-DK"));

        this.ownerStore = new HashMap<>();

        int socialSecurityNumber = faker.number().numberBetween(8, 8);
        String name = faker.name().name();

        this.companyStore = new HashMap<>();

        this.createCompany(CompanyData.builder()
                .name(faker.company().name())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .country("DK")
                .ownerIds(List.of())
                .build());

        this.createCompany(CompanyData.builder()
                .name(faker.company().name())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .country("DK")
                .ownerIds(List.of())
                .build());
    }

    @Override
    public List<Company> getCompanies() {
        return this.companyStore.values().stream()
                .map((companyEntity -> Company.builder()
                    .id(companyEntity.getId())
                    .name(companyEntity.getName())
                    .phoneNumber(companyEntity.getPhoneNumber())
                    .country(companyEntity.getCountry())
                    .ownerIds(companyEntity.getOwnerIds())
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
                .phoneNumber(companyEntity.getPhoneNumber())
                .country(companyEntity.getCountry())
                .ownerIds(companyEntity.getOwnerIds())
                .build();
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

    @Override
    public Company updateCompany(String companyId, Company companyData) {

        LocalDateTime updateDate = LocalDateTime.now();

        CompanyEntity companyEntity = this.companyStore.get(companyId);

        companyEntity.setName(companyData.getName());
        companyEntity.setCountry(companyData.getCountry());
        companyEntity.setPhoneNumber(companyData.getPhoneNumber());
        companyEntity.setOwnerIds(companyData.getOwnerIds());
        companyEntity.setUpdated(updateDate);

        return this.getCompany(companyId);
    }

    @Override
    public Owner registerOwner(OwnerData owner) {
        String id = UUID.randomUUID().toString();
        LocalDateTime createDate = LocalDateTime.now();

        OwnerEntity entity = OwnerEntity.builder()
                .id(id)
                .name(owner.getName())
                .socialSecurityNumber(owner.getSocialSecurityNumber())
                .created(createDate)
                .updated(createDate)
                .build();

        this.ownerStore.put(id, entity);

        return Owner.builder()
                .id(id)
                .name(owner.getName())
                .socialSecurityNumber(owner.getSocialSecurityNumber())
                .build();
    }

    @Override
    public Owner getOwnerById(String id) {
        OwnerEntity ownerEntity = this.ownerStore.get(id);

        if(Objects.isNull(ownerEntity)) {
            return null;
        }

        return Owner.builder()
                .id(ownerEntity.getId())
                .name(ownerEntity.getName())
                .socialSecurityNumber(ownerEntity.getSocialSecurityNumber())
            .build();
    }

    @Override
    public Owner getOwnerBySocialSecurityNumber(String socialSecurityNumber) {
        Optional<OwnerEntity> optionalOwnerEntity = ownerStore.values().stream()
                .filter((ownerEntity ->
                        Objects.equals(ownerEntity.getSocialSecurityNumber(), socialSecurityNumber)))
                .findFirst();

        if(optionalOwnerEntity.isEmpty()) {
            return null;
        }

        OwnerEntity ownerEntity = optionalOwnerEntity.get();

        return Owner.builder()
                .id(ownerEntity.getId())
                .name(ownerEntity.getName())
                .socialSecurityNumber(ownerEntity.getSocialSecurityNumber())
                .build();
    }
}

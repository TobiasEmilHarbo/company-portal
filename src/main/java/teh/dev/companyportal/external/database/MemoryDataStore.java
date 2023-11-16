package teh.dev.companyportal.external.database;

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

    private final DataGeneratorService dataGeneratorService;
    private final Map<String, OwnerEntity> ownerStore;
    private final Map<String, CompanyEntity> companyStore;

    public MemoryDataStore(DataGeneratorService dataGeneratorService) {
        this.dataGeneratorService = dataGeneratorService;

        this.ownerStore = new HashMap<>();
        this.companyStore = new HashMap<>();

        OwnerData ownerData = dataGeneratorService.generateOwnerData();
        Owner owner = this.createOwner(ownerData);

        CompanyData companyDataOne = dataGeneratorService.generateCompanyData();
        CompanyData companyDataTwo = dataGeneratorService.generateCompanyData();

        companyDataOne.setOwnerIds(List.of(owner.getId()));
        companyDataTwo.setOwnerIds(List.of(owner.getId()));

        this.createCompany(companyDataOne);
        this.createCompany(companyDataTwo);
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
        String id = dataGeneratorService.generateId();
        LocalDateTime createDate = dataGeneratorService.getCurrentLocalDateTime();

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
    public Company updateCompany(String companyId, Company company) {

        LocalDateTime updateDate = dataGeneratorService.getCurrentLocalDateTime();

        CompanyEntity companyEntity = this.companyStore.get(companyId);

        companyEntity.setName(company.getName());
        companyEntity.setCountry(company.getCountry());
        companyEntity.setPhoneNumber(company.getPhoneNumber());
        companyEntity.setOwnerIds(company.getOwnerIds());
        companyEntity.setUpdated(updateDate);

        return this.getCompany(companyId);
    }

    @Override
    public Owner createOwner(OwnerData owner) {
        String id = dataGeneratorService.generateId();
        LocalDateTime createDate = dataGeneratorService.getCurrentLocalDateTime();

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

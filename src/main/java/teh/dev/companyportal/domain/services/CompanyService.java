package teh.dev.companyportal.domain.services;

import jakarta.ws.rs.WebApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.exceptions.CompanyNotFoundException;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.domain.models.Owner;
import teh.dev.companyportal.external.database.MemoryDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final OwnerService ownerService;
    private final MemoryDataStore dataStore;

    public Company getCompany(String companyId) {
        return dataStore.getCompany(companyId);
    }

    public List<Company> getCompanies() {
        return dataStore.getCompanies();
    }

    public Company createCompany(CompanyData companyData) {
        return dataStore.createCompany(companyData);
    }

    public Owner addOwner(String companyId, String ownerSocialSecurityNumber) throws CompanyNotFoundException {
        Company company = dataStore.getCompany(companyId);

        if(Objects.isNull(company)) {
            throw new CompanyNotFoundException();
        }

        Owner owner = ownerService.getOwner(ownerSocialSecurityNumber);

        if(Objects.isNull(owner)) {
            return null;
        }

        List<String> ownerIds = new ArrayList<>(company.getOwnerIds());

        ownerIds.add(owner.getId());

        company.setOwnerIds(ownerIds);

        dataStore.updateCompany(companyId, company);

        return owner;
    }
}

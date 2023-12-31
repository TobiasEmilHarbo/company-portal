package teh.dev.companyportal.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.exceptions.CompanyNotFoundException;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.domain.models.Owner;
import teh.dev.companyportal.domain.models.Role;
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

    public Owner addOwnerToCompany(String companyId, String ownerSocialSecurityNumber) throws CompanyNotFoundException {
        Company company = dataStore.getCompany(companyId);

        if(Objects.isNull(company)) {
            throw new CompanyNotFoundException();
        }

        Owner owner = ownerService.getOwner(ownerSocialSecurityNumber);

        if(Objects.isNull(owner)) {
            return null;
        }

        List<String> ownerIds = new ArrayList<>(company.getOwnerIds());

        if(ownerIds.contains(owner.getId())) {
            return owner;
        }

        ownerIds.add(owner.getId());

        company.setOwnerIds(ownerIds);

        dataStore.updateCompany(companyId, company);

        return owner;
    }

    public Company updateCompany(String companyId, Company company) {
        return dataStore.updateCompany(companyId, company);
    }

    public List<Owner> getOwnersOfCompany(String companyId) {
        Company company = dataStore.getCompany(companyId);

        if(Objects.isNull(company)) {
            return null;
        }

        return company.getOwnerIds()
                .stream()
                .map(dataStore::getOwnerById).toList();
    }

    /**
     * Should simulate a check to see, if the current logged in user has permission to do this action
     */
    public boolean canViewCompanyOwnerDetails(Role role) {
         return role.equals(Role.OWNER);
    }

    /**
     * Should simulate a check to see, if the current logged in user has permission to do this action
     */
    public boolean canAddOwner(Role role) {
        return role.equals(Role.OWNER);
    }

    /**
     * Should simulate a check to see, if the current logged in user has permission to do this action
     */
    public boolean canUpdateCompany(Role role) {
        return role.equals(Role.OWNER);
    }

    /**
     * Should simulate a check to see, if the current logged in user has permission to do this action
     */
    public boolean canCreateNewCompany(Role role) {
        return role.equals(Role.ADMIN);
    }
}

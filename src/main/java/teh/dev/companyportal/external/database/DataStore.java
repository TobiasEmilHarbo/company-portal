package teh.dev.companyportal.external.database;

import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.domain.models.Owner;
import teh.dev.companyportal.domain.models.OwnerData;

import java.util.List;

public interface DataStore {
    List<Company> getCompanies();
    Company getCompany(String id);
    Company createCompany(CompanyData companyData);
    Company updateCompany(String companyId, Company companyData);
    Owner createOwner(OwnerData ownerData);
    Owner getOwnerById(String id);
    Owner getOwnerBySocialSecurityNumber(String socialSecurityNumber);
}

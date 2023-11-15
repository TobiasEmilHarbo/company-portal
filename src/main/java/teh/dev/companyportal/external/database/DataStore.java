package teh.dev.companyportal.external.database;

import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;

import java.util.List;

public interface DataStore {
    List<Company> getCompanies();
    Company getCompany(String id);
    Company createCompany(CompanyData company);
}

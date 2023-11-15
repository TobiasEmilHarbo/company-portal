package teh.dev.companyportal.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.external.database.MemoryDataStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

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
}

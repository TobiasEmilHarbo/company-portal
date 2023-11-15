package teh.dev.companyportal.external.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.external.database.entities.CompanyEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class MemoryDataStoreTest {
    @InjectMocks
    MemoryDataStore sut;

    @Mock
    Store<CompanyEntity> companyStore;

    @Test
    void getCompany_shouldReturnNullWhenCompanyDoesNotExist() {
        Company actualCompany = sut.getCompany("not-existing-company");

        assertNull(actualCompany);
    }
    }
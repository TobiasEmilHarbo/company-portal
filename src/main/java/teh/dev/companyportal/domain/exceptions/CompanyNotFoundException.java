package teh.dev.companyportal.domain.exceptions;

import static teh.dev.companyportal.domain.exceptions.ErrorMessage.COMPANY_NOT_FOUND;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super(COMPANY_NOT_FOUND.getValue());
    }
}

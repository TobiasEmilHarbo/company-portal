package teh.dev.companyportal.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.external.socialSecurityNumber.SocialSecurityRestService;
import teh.dev.companyportal.external.socialSecurityNumber.api.models.Person;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SocialSecurityNumberService {
    private final SocialSecurityRestService socialSecurityRestService;

    public boolean checkSocialSecurityNumber(String socialSecurityNumber) {
        Person person = socialSecurityRestService.getPerson(socialSecurityNumber);

        return !Objects.isNull(person);
    }
}

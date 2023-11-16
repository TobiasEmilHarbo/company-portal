package teh.dev.companyportal.external.socialsecuritynumber;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.external.socialsecuritynumber.api.SocialSecurityNumberApi;
import teh.dev.companyportal.external.socialsecuritynumber.api.models.Person;

@Service
@RequiredArgsConstructor
public class SocialSecurityRestService {

    private final SocialSecurityNumberApi socialSecurityNumberApi;

    public Person getPerson(String socialSecurityNumber) {
        Response response = socialSecurityNumberApi.getPerson(socialSecurityNumber);

        Response.Status responseStatus = response.getStatusInfo()
                .toEnum();

        if (responseStatus != Response.Status.OK) {
            return null;
        }

        return (Person) response.getEntity();
    }
}

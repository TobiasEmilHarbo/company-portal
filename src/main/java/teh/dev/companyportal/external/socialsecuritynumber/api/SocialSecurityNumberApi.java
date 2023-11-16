package teh.dev.companyportal.external.socialsecuritynumber.api;

import com.github.javafaker.Faker;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.external.socialsecuritynumber.api.models.Person;

import java.util.Locale;

@Service
@Component("ExternalSocialSecurityNumberApi")
public class SocialSecurityNumberApi {
    public Response getPerson(String socialSecurityNumber) {
        Faker faker = new Faker(Locale.of("da-DK"));

        // generates a random value of either true or false to simulate a validation check
        boolean isValueSocialSecurityNumber = faker.random().nextBoolean();

        if(isValueSocialSecurityNumber) {
            Person person = Person.builder()
                    .name(faker.name().name())
                    .socialSecurityNumber(socialSecurityNumber)
                .build();

            return Response
                    .status(Response.Status.OK)
                    .entity(person)
                    .build();
        }

        return Response.noContent()
                .status(Response.Status.NOT_FOUND)
                .build();
    }
}

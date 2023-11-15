package teh.dev.companyportal.external.socialSecurityNumber.api;

import com.github.javafaker.Faker;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.external.socialSecurityNumber.api.models.Person;

import java.util.Locale;

@Service
public class SocialSecurityNumberApi {
    public Response getPerson(String socialSecurityNumber) {
        Faker faker = new Faker(Locale.of("da-DK"));

        if(faker.random().nextBoolean()) {
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

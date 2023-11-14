package teh.dev.companyportal.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.Company;

@Service
@Path("companies")
public class CompanyApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompany() {
        return Response.ok()
                .entity(Company.builder()
                        .name("ACME")
                        .country("US")
                        .phoneNumber("23456789")
                        .build())
                .build();
    }
}

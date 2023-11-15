package teh.dev.companyportal.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import teh.dev.companyportal.api.models.AddCompanyRequestBody;
import teh.dev.companyportal.api.models.AddOwnerRequestBody;
import teh.dev.companyportal.domain.exceptions.CompanyNotFoundException;
import teh.dev.companyportal.domain.models.Company;
import teh.dev.companyportal.domain.models.CompanyData;
import teh.dev.companyportal.domain.models.Owner;
import teh.dev.companyportal.domain.services.CompanyService;

import java.util.List;
import java.util.Objects;

import static teh.dev.companyportal.api.ParameterConstants.COMPANY_ID_PATH_PARAMETER;

@Service
@Path("companies")
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanies() {

        List<Company> companies = companyService.getCompanies();

        return Response.ok()
                .entity(companies)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompany(
            @RequestBody() AddCompanyRequestBody requestBody
    ) {
        CompanyData companyData = CompanyData.builder()
                .name(requestBody.getName())
                .country(requestBody.getCountry())
                .phoneNumber(requestBody.getPhoneNumber())
                .ownerIds(requestBody.getOwnerIds())
                .build();

        Company company = companyService.createCompany(companyData);

        return Response.ok()
                .entity(company)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{" + COMPANY_ID_PATH_PARAMETER + "}/")
    public Response getCompany(
            @PathParam(COMPANY_ID_PATH_PARAMETER) String companyId) {

        Company company = companyService.getCompany(companyId);

        if(Objects.isNull(company)) {
            return Response
                    .noContent()
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok()
                .entity(company)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{" + COMPANY_ID_PATH_PARAMETER + "}/owners/")
    public Response addOwner(
            @PathParam(COMPANY_ID_PATH_PARAMETER) String companyId,
            @RequestBody() AddOwnerRequestBody requestBody) {
        Owner owner;

        try {
            owner = companyService.addOwner(companyId, requestBody.getSocialSecurityNumber());
        } catch (CompanyNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }

        if(Objects.isNull(owner)) {
            return Response
                    .noContent()
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        return Response.ok()
                .entity(owner)
                .build();
    }
}

package teh.dev.companyportal.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import teh.dev.companyportal.api.models.CheckSocialSecurityNumberRequestBody;
import teh.dev.companyportal.domain.models.SocialSecurityNumberChecker;
import teh.dev.companyportal.domain.services.SocialSecurityNumberService;

/**
 * This API has only one resource allowing for checking if a social security number is "valid".
 * This endpoint does not really belong to this service and should be moved to a dedicated service.
 *
 * In line with REST API guidelines provided by zalando.com this endpoint is designed as a resource rather than an action:
 * https://opensource.zalando.com/restful-api-guidelines/#hypermedia
 */
@Service
@Path("social-security-number-checkers")
@RequiredArgsConstructor
public class SocialSecurityNumberApi {

    private final SocialSecurityNumberService socialSecurityNumberService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkSocialSecurityNumber(
            @RequestBody() CheckSocialSecurityNumberRequestBody requestBody
    ) {

        boolean isValue = socialSecurityNumberService.checkSocialSecurityNumber(requestBody.getSocialSecurityNumber());

        SocialSecurityNumberChecker checker = SocialSecurityNumberChecker.builder()
                .isValid(isValue)
                .build();

        return Response.ok()
                .entity(checker)
                .build();
    }
}

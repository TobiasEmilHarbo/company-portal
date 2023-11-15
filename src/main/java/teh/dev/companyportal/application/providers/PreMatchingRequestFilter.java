package teh.dev.companyportal.application.providers;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PreMatching
public class PreMatchingRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) {
        log.info(ctx.getMethod() + " request on /" + ctx.getUriInfo().getPath());
    }
}
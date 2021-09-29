package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Tag(name = "authentication", description = "Handling of authentication")
public class AuthenticationController {

    @Inject
    AuthenticationService authenticationService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(User user){
        if (authenticationService.checkIfUserExists(user)) {
            return authenticationService.GenerateValidJwtToken(user.getUsername());
        } else {
            return "";
        }

    }
}

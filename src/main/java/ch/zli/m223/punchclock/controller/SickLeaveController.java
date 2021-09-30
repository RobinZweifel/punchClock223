package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.SickLeave;
import ch.zli.m223.punchclock.service.SickLeaveService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/leaves")
@Tag(name = "Leaves", description = "Handlich of SickLeaves")
public class SickLeaveController {
    @Inject
    SickLeaveService sickLeaveService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List of all SickLeaves", description = "List of all SickLeaves returnes")
    public List<SickLeave> list(){
        return sickLeaveService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Sick Leave", description = "The newly created entry is returned. The id may not be passed.")
    public SickLeave add(SickLeave sickLeave) {
        return sickLeaveService.createSickLeave(sickLeave);
    }


    @DELETE
    @Operation(summary = "Deletes one Objekt", description = "Deletes a Sick Leave Object")
    public void deleteObject(SickLeave sickLeave){
        sickLeaveService.delSickLeaveObject(sickLeave);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update Sick Leave", description = "To update one Sick Leave Object")
    public SickLeave update(SickLeave sickLeave){
        return sickLeaveService.updateSickLeave(sickLeave);
    }
}

package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    EntryService entryServic = new EntryService();

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries", description = "")
    public List<Entry> list() {
        return entryService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Entry getEntry(@PathParam Long id) {
        return entryService.getEntry(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
       return entryService.createEntry(entry);
    }


    @DELETE
    @Operation(summary = "Deletes one Objekt", description = "")
    @Path("/{id}")
    public void deletePerId(@PathParam Long id){
        entryService.delEntryId(id);
    }

    @DELETE
    @Operation(summary = "Deletes one Objekt", description = "")
    public void deleteObject(Entry entry){
        entryService.delEntryObject(entry);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry update(Entry entry){
        return entryService.updateEntry(entry);
    }
}

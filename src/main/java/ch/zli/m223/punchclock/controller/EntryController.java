package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.service.SickLeaveService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    SickLeaveService sickLeaveService;

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries", description = "To Get a List of all the ENtries in the Database")
    public List<Entry> list() {
        return entryService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Get one Entity", description = "To Get on entity with the ID of the Entity")
    public Entry getEntry(@PathParam Long id) {
        return entryService.getEntry(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
        //Prüen ob sick leave existiert
        //if(true){
            //if ja --> Sick leave erstellen

        //}
        //Neuer sick leave laden und auf entry setzen
        return entryService.createEntry(entry);
    }


    @DELETE
    @Operation(summary = "Deletes one Objekt", description = "Deletes one Objekt with the passed ID")
    @Path("/{id}")
    public void deletePerId(@PathParam Long id){
        entryService.delEntryId(id);
    }

    @DELETE
    @Operation(summary = "Deletes one Objekt", description = "Deletes one Objekt with the passed Object")
    public void deleteObject(Entry entry){
        entryService.delEntryObject(entry);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update Entry", description = "To update a Entry withe the passed Entry Obkect")
    public Entry update(Entry entry){
        return entryService.updateEntry(entry);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/jpql")
    @Operation(summary = "Execute Querry", description = "Executes a jpql Query")
    public List<Entry> getJpql(){
        return entryService.jpqlQuerry();
    }
}

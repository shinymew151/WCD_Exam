package com.player.rest;

import com.player.entity.Player;
import com.player.service.PlayerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/player")  // Changed from /player to /api/player
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @GET
    public Response getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return Response.ok(players).build();
    }

    @GET
    @Path("/{id}")
    public Response getPlayer(@PathParam("id") Integer id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(player).build();
    }

    @POST
    public Response createPlayer(Player player) {
        playerService.createPlayer(player);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlayer(@PathParam("id") Integer id, Player player) {
        player.setPlayerId(id);
        playerService.updatePlayer(player);
        return Response.ok(player).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlayer(@PathParam("id") Integer id) {
        playerService.deletePlayer(id);
        return Response.noContent().build();
    }
}

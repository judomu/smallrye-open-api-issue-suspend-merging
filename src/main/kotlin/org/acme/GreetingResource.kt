package org.acme

import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PATCH
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun foo(
        @QueryParam("foo") foo: String
    ) = "Hello from RESTEasy Reactive"

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun bla(
        @QueryParam("bla") bla: String
    ) = "Hello from RESTEasy Reactive"

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun foobla(
        @QueryParam("foobla") bla: String
    ) = "Hello from RESTEasy Reactive"

    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    fun allgood(
        @QueryParam("allgood") bla: String
    ) = "Hello from RESTEasy Reactive"
}
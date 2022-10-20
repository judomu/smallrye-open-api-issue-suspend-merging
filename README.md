# Showcase quarkus + openapi suspend issue

When using `quarkus`, `smallrye-open-api` and kotlin-`suspend` functions, the generated openapi file contains all `@QueryParam` definitions (from `suspend` functions) on every path level.

The following resource definition:

```kotlin
@Path("/hello")
class GreetingResource {

    @Path("foo")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun foo(
        @QueryParam("foo") foo: String
    ) = "Hello from RESTEasy Reactive"

    @Path("bla")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun bla(
        @QueryParam("bla") bla: String
    ) = "Hello from RESTEasy Reactive"

    @Path("foobla")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun foobla(
        @QueryParam("foobla") bla: String
    ) = "Hello from RESTEasy Reactive"

    @Path("blafoo")
    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    fun blafoo(
        @QueryParam("blafoo") bla: String
    ) = "Hello from RESTEasy Reactive"
}
```

leads to

```yaml
---
openapi: 3.0.3
info:
  title: rest-kotlin-quickstart API
  version: 1.0.0-SNAPSHOT
paths:
  /hello/bla:
    post:
      tags:
      - Greeting Resource
      parameters:
      - name: bla # correct!
        in: query
        schema:
          type: string
      responses:
        "200":
          description: OK
          content:
            text/plain:
              schema:
                type: string
    parameters:
    - name: bla # failure: query parameter from bla-function 
      in: query
      schema:
        type: string
    - name: foo # failure: query parameter from foo-function
      in: query
      schema:
        type: string
    - name: foobla # failure: query parameter from foobla-function
      in: query
      schema:
        type: string
# ...
```

## Steps to reproduce

1. `git clone git@github.com:judomu/smallrye-open-api-issue-suspend-merging.git`
2. `cd smallrye-open-api-issue-suspend-merging` 
3. `quarkus dev`
4. see issue at `http://localhost:8080/q/swagger-ui`
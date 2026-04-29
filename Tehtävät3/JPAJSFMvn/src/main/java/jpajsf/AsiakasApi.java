package jpajsf;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationPath("api")
@Path("asiakkaat")
public class AsiakasApi extends Application {

    @EJB
    private AsiakasService as;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAsiakkaat() {
        List<Asiakas> list = as.getAllAsiakkaat();
        GenericEntity<List<Asiakas>> entity = new GenericEntity<List<Asiakas>>(list) {};
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsiakasById(@PathParam("id") int id) {
        Asiakas a = as.getAsiakasById(id);
        if (a != null) {
            return Response.ok(a).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAsiakas(Asiakas asiakas) {
        as.save(asiakas);
        return Response.ok(asiakas).build();
    }
}
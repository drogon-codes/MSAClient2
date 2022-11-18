/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clients;

import entities.Category;
import entities.Garments;
import entities.TblOrder;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author c computer
 */
@RegisterRestClient(baseUri = "http://localhost:8585/MSAApp2/rest/example")
public interface myclient {
    @GET
    public String getHello();
    
    @RolesAllowed("customer")
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getCategories")
    @Produces(MediaType.APPLICATION_JSON)
     public Collection<Category> getCategory();
     
    @RolesAllowed("customer")
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getGarmentsByCatId/{catid}")
    @Produces(MediaType.APPLICATION_JSON)
     public Collection<Garments> getGarmentsByCatId(@PathParam("catid") Integer catid);
    
    @RolesAllowed("customer")
    @GET
    @ClientHeaderParam(name = "authorization", value = "{generateToken}")
    @Path("/getOrders")
    @Produces(MediaType.APPLICATION_JSON)
     public Collection<TblOrder> getOrders();
     
    default String generateToken() {
        Config config = ConfigProvider.getConfig();
        String token = "Bearer " + config.getValue("jwt-string", String.class);
        return token;
    }
}

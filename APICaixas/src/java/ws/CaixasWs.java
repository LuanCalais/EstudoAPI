package ws;

import com.google.gson.Gson;
import dao.CaixasDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Caixas;

@Path("generic")
public class CaixasWs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CaixasWs
     */
    public CaixasWs() {
    }

    /**
     * Retrieves representation of an instance of ws.CaixasWs
     *
     * @return an instance of java.lang.String
     */
    
    
    //http://localhost:8080/APICaixas/webresources/generic/caixa/cadastrar
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("caixa/cadastrar")
    public void cadastrar(String content) {
        Gson g = new Gson();
        Caixas c = (Caixas) g.fromJson(content, Caixas.class);

        CaixasDAO dao = new CaixasDAO();

        try {

            dao.cadastrar(c);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //http://localhost:8080/APICaixas/webresources/generic/caixa/alterar
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("caixa/alterar")
    public void alterar(String content) {
        Gson g = new Gson();
        Caixas c = (Caixas) g.fromJson(content, Caixas.class);

        CaixasDAO dao = new CaixasDAO();

        try {

            dao.atualizar(c);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //http://localhost:8080/APICaixas/webresources/generic/caixa/consultartodos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("caixa/consultartodos")
    public String consultarTodos() {
        ArrayList<Caixas> caixas = new ArrayList();
        CaixasDAO dao = new CaixasDAO();

        try {

            caixas = dao.listar();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        Gson g = new Gson();
        return g.toJson(caixas);
    }

    //http://localhost:8080/APICaixas/webresources/generic/caixa/get/{id}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("caixa/get/{id}")
    public String getCaixa(@PathParam("id") int id) {

        Caixas c = new Caixas();
        c.setId(id);

        CaixasDAO dao = new CaixasDAO();

        try {

            c = dao.listarId(c);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        Gson g = new Gson();
        return g.toJson(c);
    }

    //http://localhost:8080/APICaixas/webresources/generic/caixa/deletar/{id}
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("caixa/deletar/{id}")
    public void deletarCaixa(@PathParam("id") int id) {
        
        Caixas c = new Caixas();
        c.setId(id);

        CaixasDAO dao = new CaixasDAO();

        try {

            dao.deletar(c);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * PUT method for updating or creating an instance of CaixasWs
     *
     * @param content representation for the resource
     */
}

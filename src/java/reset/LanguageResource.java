/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reset;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author sean
 */
@Path("language/{language_id:.*}")
public class LanguageResource {

    public Connection conn;
    public Statement stat;
    public ResultSet result;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LanguageResource
     */
    public LanguageResource() {
    }

    /**
     * Retrieves representation of an instance of reset.LanguageResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@PathParam("language_id") String language_id) {
        //TODO return proper representation object
        String Json = "";
        Connection conn = this.getConexion();
        Statement stat;
        ResultSet result;
        if (conn != null) {
            try {
                stat = (Statement) conn.createStatement();

                result = stat.executeQuery("select * from language where language_id=" + language_id);

                while (result.next()) {
                    Json = "{'id ':'" + language_id + "','name ':'" + result.getString(2) + ", last_Update : '" + result.getString(3) + "'}";
                }

            } catch (SQLException ex) {
                System.out.println("language not found\n" + ex);
            }

        }

        return Json;
    }

    /**
     * PUT method for updating or creating an instance of LanguageResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    public Connection getConexion() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.123.128/sakila", "nom", "badia123");
        } catch (SQLException ex) {
            System.out.println("Error de Conexion: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActorsResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ActorsResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ActorsResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }
}

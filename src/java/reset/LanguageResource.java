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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.json.JSONException;
import org.json.JSONObject;

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

    private String[] keys = new String[2];

    /**
     * Creates a new instance of LanguageResource
     */
    public LanguageResource() {
        this.keys[0] = "name";
        this.keys[1] = "last_update";
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
    public String putJson(String content) {
        JSONObject json = null;
        boolean valid = true;

        //Pasar el contenido a JSON
        try {
            json = new JSONObject(content);
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        }

        for (String key : keys) {
            if (!json.has(key)) {
                valid = false;
                break;
            }
        }

        if (json != null && valid) {
            Connection conn = this.getConexion();
            Statement stat;
            int result;
            if (conn != null) {
                try {
                    stat = (Statement) conn.createStatement();
                    String query = "";
                    try {
                        query = "INSERT INTO language (name) VALUES ('" + json.getString("name") + "')";
                        result = stat.executeUpdate(query);
                    } catch (JSONException ex) {
                        Logger.getLogger(ActorsResource.class.getName()).log(Level.SEVERE, null, ex);
                        valid = false;
                    }

                } catch (SQLException ex) {
                    System.out.println("language not found\n" + ex);
                    valid = false;
                }
            }
        } else {
            System.out.println("Not valid format");
        }

        return "{'succesful' : '" + valid + "'}";
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

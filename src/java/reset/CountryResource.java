/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reset;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author ricard
 */
@Path("country/{country_id:.*}")
public class CountryResource {

    @Context
    private UriInfo context;

    private String[] keys = new String[1];

    /**
     * Creates a new instance of actors
     */
    public CountryResource() {
        this.keys[0] = "country";

    }

    /**
     * Retrieves representation of an instance of reset.actors
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("country_id") String country_id) {
        String Json = "";
        Connection conn = this.getConexion();
        Statement stat;
        ResultSet result;
        if (conn != null) {
            try {
                stat = (Statement) conn.createStatement();

                result = stat.executeQuery("select * from country where country_id=" + country_id);

                while (result.next()) {
                    Json = "{'id ':'" + country_id + "','country ':'" + result.getString(2) + "'}";
                }

            } catch (SQLException ex) {
                System.out.println("country not found\n" + ex);
            }

        }

        return Json;
    }

    /**
     * PUT method for updating or creating an instance of actors
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
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
                        query = "INSERT INTO country (country) VALUES ('" + json.getString("country") + "')";
                        result = stat.executeUpdate(query);
                    } catch (JSONException ex) {
                        Logger.getLogger(ActorsResource.class.getName()).log(Level.SEVERE, null, ex);
                        valid = false;
                    }

                } catch (SQLException ex) {
                    System.out.println("country not found\n" + ex);
                    valid = false;
                }
            }
        } else {
            System.out.println("Not valid format");
        }

        return "{'succesful' : '" + valid + "'}";
    } //{'country' : 'sabadell'}

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

package com.javaeehandbook;

import com.javaeehandbook.models.JavaEETechnology;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/javaeehandbook")
public class JavaEEHandbookService {
    private static final Logger log = LogManager.getLogger(JavaEEHandbookService.class);
    private Database database;

    {
        try {
            database = new Database();
        } catch (Exception e) {
           log.error(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTechnologies() {
        String SQL;
        ResultSet rs;
        List<JavaEETechnology> list = new ArrayList<JavaEETechnology>();
        try {
            SQL = "SELECT *\n" +
                    "FROM java_technologies INNER JOIN used_versions\n" +
                    "ON java_technologies.versions = used_versions.used_versions_id;";

            rs = database.getConnection().createStatement().executeQuery(SQL);

            while (rs.next()) {
                list.add(fromResultSetToObject(rs));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return convertJavaEETechnologiesToJSON(list).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTechnology(String jsonTechnology) {
        JavaEETechnology technology = new JavaEETechnology();
        JSONObject json = new JSONObject(jsonTechnology);

        technology.setName(json.getString("name"));
        technology.setVersionForJava4(json.getString("versionForJava4"));
        technology.setVersionForJava5(json.getString("versionForJava5"));
        technology.setVersionForJava6(json.getString("versionForJava6"));
        technology.setVersionForJava7(json.getString("versionForJava7"));
        technology.setVersionForJava8(json.getString("versionForJava8"));
        technology.setDescription(json.getString("description"));

        String SQL;
        PreparedStatement preparedStatement;
        try {

            SQL = "INSERT INTO used_versions(java_4, java_5, java_6, java_7, java_8) \n" +
                    "VALUES(?, ?, ?, ?, ?)\n" +
                    "ON DUPLICATE KEY UPDATE java_4=?, java_5=?, java_6=?, java_7=?, java_8=?;";
            preparedStatement = database.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, technology.getVersionForJava4());
            preparedStatement.setString(2, technology.getVersionForJava5());
            preparedStatement.setString(3, technology.getVersionForJava6());
            preparedStatement.setString(4, technology.getVersionForJava7());
            preparedStatement.setString(5, technology.getVersionForJava8());
            preparedStatement.setString(6, technology.getVersionForJava4());
            preparedStatement.setString(7, technology.getVersionForJava5());
            preparedStatement.setString(8, technology.getVersionForJava6());
            preparedStatement.setString(9, technology.getVersionForJava7());
            preparedStatement.setString(10, technology.getVersionForJava8());
            preparedStatement.executeUpdate();

            SQL = "INSERT INTO java_technologies(tech_name, versions, description)\n" +
                    "VALUES(?,\n" +
                    "(SELECT used_versions_id\n" +
                    "FROM used_versions\n" +
                    "WHERE java_4=? AND java_5=? AND java_6=? AND java_7=? AND java_8=?), ?)\n" +
                    "ON DUPLICATE KEY UPDATE tech_name=?;";
            preparedStatement = database.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, technology.getName());
            preparedStatement.setString(2, technology.getVersionForJava4());
            preparedStatement.setString(3, technology.getVersionForJava5());
            preparedStatement.setString(4, technology.getVersionForJava6());
            preparedStatement.setString(5, technology.getVersionForJava7());
            preparedStatement.setString(6, technology.getVersionForJava8());
            preparedStatement.setString(7, technology.getDescription());
            preparedStatement.setString(8, technology.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeTechnology(String jsonTechnology) {
        JavaEETechnology technology = new JavaEETechnology();
        technology.fromJSON(new JSONObject(jsonTechnology));

        String SQL;
        PreparedStatement preparedStatement;
        try {
            SQL = "DELETE FROM java_technologies\n" +
                    "WHERE tech_id=?;";
            preparedStatement = database.getConnection().prepareStatement(SQL);
            preparedStatement.setInt(1, technology.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTechnology(String jsonTechnology) {
        JavaEETechnology technology = new JavaEETechnology();
        technology.fromJSON(new JSONObject(jsonTechnology));

        String SQL;
        PreparedStatement preparedStatement;
        try {
            SQL = "INSERT INTO used_versions(java_4, java_5, java_6, java_7, java_8) \n" +
                    "VALUES(?, ?, ?, ?, ?)\n" +
                    "ON DUPLICATE KEY UPDATE java_4=?, java_5=?, java_6=?, java_7=?, java_8=?;";
            preparedStatement = database.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, technology.getVersionForJava4());
            preparedStatement.setString(2, technology.getVersionForJava5());
            preparedStatement.setString(3, technology.getVersionForJava6());
            preparedStatement.setString(4, technology.getVersionForJava7());
            preparedStatement.setString(5, technology.getVersionForJava8());
            preparedStatement.setString(6, technology.getVersionForJava4());
            preparedStatement.setString(7, technology.getVersionForJava5());
            preparedStatement.setString(8, technology.getVersionForJava6());
            preparedStatement.setString(9, technology.getVersionForJava7());
            preparedStatement.setString(10, technology.getVersionForJava8());
            preparedStatement.executeUpdate();

            SQL = "UPDATE java_technologies\n" +
                    "SET tech_name=?, versions=(SELECT used_versions_id\n" +
                    "FROM used_versions\n" +
                    "WHERE java_4=? AND java_5=? AND java_6=? AND java_7=? AND java_8=?),\n" +
                    "description=?\n" +
                    "WHERE tech_id=?;";
            preparedStatement = database.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, technology.getName());
            preparedStatement.setString(2, technology.getVersionForJava4());
            preparedStatement.setString(3, technology.getVersionForJava5());
            preparedStatement.setString(4, technology.getVersionForJava6());
            preparedStatement.setString(5, technology.getVersionForJava7());
            preparedStatement.setString(6, technology.getVersionForJava8());
            preparedStatement.setString(7, technology.getDescription());
            preparedStatement.setInt(8, technology.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private JavaEETechnology fromResultSetToObject(ResultSet rs) throws SQLException {
        JavaEETechnology technology = new JavaEETechnology();
        technology.setId(rs.getInt("tech_id"));
        technology.setName(rs.getString("tech_name"));
        technology.setVersionForJava4(rs.getString("java_4"));
        technology.setVersionForJava5(rs.getString("java_5"));
        technology.setVersionForJava6(rs.getString("java_6"));
        technology.setVersionForJava7(rs.getString("java_7"));
        technology.setVersionForJava8(rs.getString("java_8"));
        technology.setDescription(rs.getString("description"));

        return technology;
    }

    private JSONArray convertJavaEETechnologiesToJSON(List<JavaEETechnology> list) {
        JSONArray array = new JSONArray();
        for(JavaEETechnology technology: list) {
            array.put(technology.toJSON());
        }
        return array;
    }
}
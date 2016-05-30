package Main; /**
 * Created by Dell_2 on 5/29/2016.
 */


import services.DataBaseServices;
import spark.ModelAndView;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.Statement;

import static spark.Spark.*;
public class Main {

    public static void main(String[] args) throws Exception{

        Connection conn= DataBaseServices.getInstancia().getConexion();
        Statement stmt = conn.createStatement();
        staticFileLocation("/publico");
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        String Select = "INSERT INTO articulos  VALUES(2011,'mujeres','ariel','papazon','2011-12-24');";
        stmt.execute(Select);



        get("/hello", (req, res) -> "Hello World");


    }
}

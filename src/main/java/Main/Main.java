package Main; /**
 * Created by Dell_2 on 5/29/2016.
 */


import modelos.Articulo;
import services.ArticuloServices;
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


        Articulo articulo = ArticuloServices.getArticulo(1);

        System.out.println("asdf");

        get("/hello", (req, res) -> "Hello World");

        ManejoTemplates mt = new ManejoTemplates();
        mt.manejarTemplates();
    }
}

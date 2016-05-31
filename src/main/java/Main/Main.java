package Main; /**
 * Created by Dell_2 on 5/29/2016.
 */


import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;
import services.ArticuloServices;
import services.ComentarioServices;
import services.DataBaseServices;
import services.EtiquetaServices;
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


        Etiqueta estiqueta = EtiquetaServices.getEtiqueta(2);

        System.out.println("dkdd");
        get("/hello", (req, res) -> "Hello World");


        ManejoTemplates mt = new ManejoTemplates();
        mt.manejarTemplates();




    }
}

package Main;

import freemarker.template.Configuration;
import modelos.Usuario;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ManejoTemplates {


    public void manejarTemplates() {

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(ManejoTemplates.class , "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAdministrador(false);
            u.setAutor(false);

            attributes.put("usuario", u);
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);

        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);




    }
}

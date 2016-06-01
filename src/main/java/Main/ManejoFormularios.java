package Main;

import freemarker.template.Configuration;
import modelos.Articulo;
import modelos.Usuario;
import services.ArticuloServices;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;
import spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

/**
 * Created by Dell_2 on 6/1/2016.
 */
public class ManejoFormularios {

    public void manejarFormularios() {

        post("autenticar/", (request, response) -> {
            //obteniendo la matricula.
            System.out.println("hola");

            Session session=request.session(true);
            Usuario usuario = UsuarioServices.getUsuario(request.queryParams("username"));

            if (usuario==null ||!request.queryParams("password").equals(usuario.getPassword())){
                halt(401,"Credenciales no validas...");
            }
            else {

                session.attribute("usuario", usuario);
                response.redirect("/");
                return "lol";
            }




            return "a fuera";
        });





    }
}

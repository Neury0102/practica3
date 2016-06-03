package Main;

import modelos.Usuario;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.halt;

/**
 * Created by Dell_2 on 6/2/2016.
 */
public class Filtro {
    public void aplicarFiltros(){
//        /**
//         * Se ejecuta antes de un request. Podemos modificar las llamada.
//         */
//        before((request, response) -> {
//            System.out.println("Filtro Before -> Realizando llamada a la ruta: "+request.pathInfo());
//        });
//        /**
//         * Luego de la ejecución permute interceptar el response...
//         */
//        after((request, response) -> {
//            System.out.println("Filtro After -> Incluyendo Header...");
//            response.header("barcamp", "2016");
//            response.header("otroHeader", "Cualquier Cosa");
//        });
//
//        /**
//         *Se ejecuta antes de un request. Podemos modificar las llamada.
//         */
//        before("zonaadmin/",(request, response) -> {
//            Usuario usuario=request.session(true).attribute("usuario");
//           // System.out.print("lolbefore");
//            if(usuario==null){
//                //parada del request, enviando un codigo.
//                halt(401, "No tiene permisos para acceder -- Lo dice el filtro....");
//            }
//            response.redirect("/");
//        });
        before("/login",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
           // System.out.print("lolbefore");
            if(usuario!=null){
                //parada del request, enviando un codigo.
                response.redirect("/");
            }
        });

        before("/administracion/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            // System.out.print("lolbefore");
            if(usuario == null ||usuario.getAdministrador()!=true){
                //parada del request, enviando un codigo.
                response.redirect("/");
            }
        });

        before("/administracion/editar/:valor",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            // System.out.print("lolbefore");
            if(usuario == null ||usuario.getAdministrador()!=true){
                //parada del request, enviando un codigo.
                response.redirect("/");
            }
        });

        before("/administracion/crearUsuario",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            // System.out.print("lolbefore");
            if(usuario == null ||usuario.getAdministrador()!=true){
                //parada del request, enviando un codigo.
                response.redirect("/");
            }
        });

        before("/redactarArticulo",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            // System.out.print("lolbefore");
            if(usuario == null ||usuario.getAdministrador()!=true||!usuario.getAutor()){
                //parada del request, enviando un codigo.
                response.redirect("/");
            }
        });




    }
}

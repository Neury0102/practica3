package services;

import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by saleta on 5/30/2016.
 */
public class ArticuloServices {

    public static Articulo getArticulo(int id) {
        Articulo articulo = null;
        Connection con = null;
            try {

                String query = "select * from articulos where id = ?";
                con = DataBaseServices.getInstancia().getConexion();
                PreparedStatement prepareStatement = con.prepareStatement(query);
                prepareStatement.setInt(1, id);
                ResultSet rs = prepareStatement.executeQuery();
                while(rs.next()){
                    articulo = new Articulo();
                    articulo.setId(rs.getInt("id"));
                    articulo.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));
                    articulo.setComentarios(getComentariosArticulo(articulo));
                    articulo.setTitulo(rs.getString("titulo"));
                    articulo.setCuerpo(rs.getString("cuerpo"));
                    articulo.setFecha(rs.getDate("fecha"));
                    articulo.setEtiquetas(getEtiquetasArticulo(articulo));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        return articulo;
    }

    public static ArrayList<Articulo> getTodosArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        Connection con = null;
        try {

            String query = "select * from articulos order by FECHA DESC";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));
                articulo.setComentarios(getComentariosArticulo(articulo));
                articulo.setTitulo(rs.getString("titulo"));
                articulo.setCuerpo(rs.getString("cuerpo"));
                articulo.setFecha(rs.getDate("fecha"));
                articulo.setEtiquetas(getEtiquetasArticulo(articulo));
                articulos.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return articulos;
    }

    private static ArrayList<Comentario> getComentariosArticulo (Articulo  articulo){
        Connection con = null;
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        try {
            String query = "select * from comentarios where articulo = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, articulo.getId());
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setArticulo(articulo);
                c.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));
                c.setComentario(rs.getString("comentario"));
                comentarios.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return comentarios;

    }

    private static ArrayList<Etiqueta> getEtiquetasArticulo (Articulo  articulo){
        Connection con = null;
        ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        try {
            String query = "select * from etiquetas where articulo = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, articulo.getId());
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Etiqueta e = new Etiqueta();
                e.setEtiqueta(rs.getString("etiqueta"));
                e.setId(rs.getInt("id"));
                etiquetas.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiquetas;

    }

    public boolean crearArticulo(Articulo articulo){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "insert into articulos(id, titulo, cuerpo, autor, fecha) values(?,?,?,?,?)";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, articulo.getId());
            prepareStatement.setString(2, articulo.getTitulo());
            prepareStatement.setString(3, articulo.getCuerpo());
            prepareStatement.setString(4, articulo.getAutor().getUsername());
            prepareStatement.setDate(5, (Date)articulo.getFecha());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

}

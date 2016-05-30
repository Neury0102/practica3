package services;

import modelos.Articulo;
import modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by saleta on 5/30/2016.
 */
public class UsuarioServices {
    public static Usuario getUsuario(String username) {

        Usuario usuario = null;
        Connection con = null;
        try {

            String query = "select * from usuarios where username = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, username);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setAdministrador(rs.getBoolean("administrator"));
                usuario.setAutor(rs.getBoolean("autor"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsername(rs.getString("username"));


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

        return usuario;
    }
}

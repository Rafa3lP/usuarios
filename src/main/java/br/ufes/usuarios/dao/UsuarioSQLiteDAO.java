/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.dao;

import br.ufes.usuarios.connection.ConnectionSQLiteFactory;
import br.ufes.usuarios.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class UsuarioSQLiteDAO implements IUsuarioDAO {

    public UsuarioSQLiteDAO() {
        try {
            criaTUsuario();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void criaTUsuario() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuario("
                + "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome VARCHAR(60) NOT NULL, "
                + "dataCadastro DATE NOT NULL, "
                + "usuario VARCHAR(45) NOT NULL UNIQUE, "
                + "senha VARCHAR(128) NOT NULL,"
                + "nivelDeAcesso INTEGER NOT NULL"
                + ");";
        
        Connection con = ConnectionSQLiteFactory.getConnection();
        Statement st = con.createStatement();
        st.execute(sql);
        st.close();
        ConnectionSQLiteFactory.closeConnection(con);
    }

    @Override
    public void criar(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario (nome, dataCadastro, usuario, senha, nivelDeAcesso) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?);";
            Connection con = ConnectionSQLiteFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getSenha());
            ps.setInt(5, usuario.getNivelDeAcesso());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    

    @Override
    public Usuario lerPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Usuario lerPorUsuario(String usuario) {
        try {
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            Connection con = ConnectionSQLiteFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            Usuario u = null;
            if (rs.next()) {
                 u = new Usuario(
                    rs.getLong("idUsuario"), 
                    rs.getString("nome"), 
                    rs.getString("usuario"), 
                    rs.getString("senha"),
                    rs.getInt("nivelDeAcesso")
                );
            }
            
            return u;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> lerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

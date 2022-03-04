/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.dao;

import br.ufes.usuarios.connection.ConnectionSQLiteFactory;
import br.ufes.usuarios.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO usuario (nome, dataCadastro, usuario, senha, nivelDeAcesso) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?);";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setDate(2, java.sql.Date.valueOf(usuario.getDataCadastro()));
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getSenha());
            ps.setInt(5, usuario.getNivelDeAcesso());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps);
        }
    }
    

    @Override
    public Usuario lerPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Usuario lerPorUsuario(String usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            Usuario u = null;
            if (rs.next()) {
                 u = new Usuario(
                    rs.getLong("idUsuario"), 
                    rs.getString("nome"),
                    rs.getString("usuario"), 
                    rs.getString("senha"),
                    rs.getDate("dataCadastro").toLocalDate(),
                    rs.getInt("nivelDeAcesso")
                );
                 
            }
            
            return u;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps, rs);
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE usuario "
                    + "SET "
                    + "nome = ?, "
                    + "dataCadastro = ?, "
                    + "usuario = ?, "
                    + "senha = ?, "
                    + "nivelDeAcesso = ? "
                    + "WHERE idUsuario = ?;";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setDate(2, java.sql.Date.valueOf(usuario.getDataCadastro()));
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getSenha());
            ps.setInt(5, usuario.getNivelDeAcesso());
            ps.setLong(6, usuario.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps);
        }
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM usuario WHERE idUsuario = " + id + ";";
        Connection con = null;
        try {
            con = ConnectionSQLiteFactory.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
            st.close();
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
             ConnectionSQLiteFactory.closeConnection(con);
        }
    }

    @Override
    public List<Usuario> lerTodos() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> resposta = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuario ORDER BY nome COLLATE NOCASE ASC";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                usuario = new Usuario(
                    rs.getLong("idUsuario"), 
                    rs.getString("nome"), 
                    rs.getString("usuario"), 
                    rs.getString("senha"),
                    rs.getDate("dataCadastro").toLocalDate(),
                    rs.getInt("nivelDeAcesso")
                );
                
                resposta.add(usuario);
            }
            
            return resposta;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps, rs);
        }
    }

    @Override
    public List<Usuario> buscaUsuariosPorNome(String filtroNome) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> resposta = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuario WHERE nome LIKE ? ORDER BY nome COLLATE NOCASE ASC";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + filtroNome + "%");
            rs = ps.executeQuery();
            
            while(rs.next()) {
                usuario = new Usuario(
                    rs.getLong("idUsuario"), 
                    rs.getString("nome"), 
                    rs.getString("usuario"), 
                    rs.getString("senha"),
                    rs.getDate("dataCadastro").toLocalDate(),
                    rs.getInt("nivelDeAcesso")
                );
                
                resposta.add(usuario);
            }
            
            return resposta;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps, rs);
        }
    }
    
}

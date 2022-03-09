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
                + "admin BOOLEAN DEFAULT false NOT NULL,"
                + "aprovado BOOLEAN DEFAULT false NOT NULL"
                + ");";
        
        Connection con = ConnectionSQLiteFactory.getConnection();
        Statement st = con.createStatement();
        st.execute(sql);
        st.close();
        ConnectionSQLiteFactory.closeConnection(con);
    }

    @Override
    public Long criar(Usuario usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO usuario (nome, dataCadastro, usuario, senha, admin) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?);";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNome());
            ps.setDate(2, java.sql.Date.valueOf(usuario.getDataCadastro()));
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getSenha());
            ps.setBoolean(5, usuario.isAdmin());
            
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()) {
                return rs.getLong(1);
            } else {
                throw new RuntimeException("Não foi possível inserir o usuario");
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps);
        }
    }
    
    @Override
    public void aprovar(Long id) {
        String sql = "UPDATE usuario SET aprovado = true WHERE idUsuario = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConnectionSQLiteFactory.getConnection();
            pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, pst);
        }
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
                    rs.getBoolean("admin"),
                    rs.getBoolean("aprovado")
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
                    + "admin = ? "
                    + "WHERE idUsuario = ?;";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setDate(2, java.sql.Date.valueOf(usuario.getDataCadastro()));
            ps.setString(3, usuario.getUsuario());
            ps.setBoolean(4, usuario.isAdmin());
            ps.setLong(5, usuario.getId());
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
            String sql = "SELECT * FROM usuario WHERE aprovado = true ORDER BY nome COLLATE NOCASE ASC";
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
                    rs.getBoolean("admin"),
                    rs.getBoolean("aprovado")
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
            String sql = "SELECT * FROM usuario WHERE nome LIKE ? AND aprovado = true ORDER BY nome COLLATE NOCASE ASC";
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
                    rs.getBoolean("admin"),
                    rs.getBoolean("aprovado")
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
    public Usuario lerPorId(Long id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM usuario WHERE idUsuario = ?";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Usuario u = null;
            if (rs.next()) {
                 u = new Usuario(
                    rs.getLong("idUsuario"), 
                    rs.getString("nome"),
                    rs.getString("usuario"), 
                    rs.getString("senha"),
                    rs.getDate("dataCadastro").toLocalDate(),
                    rs.getBoolean("admin"),
                    rs.getBoolean("aprovado")
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
    public void alterarSenha(Usuario usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE usuario "
                    + "SET "
                    + "senha = ? "
                    + "WHERE idUsuario = ?;";
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getSenha());
            ps.setLong(2, usuario.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps);
        }
    }
    
}

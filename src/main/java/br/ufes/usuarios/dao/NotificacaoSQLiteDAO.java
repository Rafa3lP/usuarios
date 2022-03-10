/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.dao;

import br.ufes.usuarios.connection.ConnectionSQLiteFactory;
import br.ufes.usuarios.model.Notificacao;
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
public class NotificacaoSQLiteDAO implements INotificacaoDAO {

    public NotificacaoSQLiteDAO() {
        try {
            criaTNotificacao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void criaTNotificacao() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS notificacao("
                + "idNotificacao INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idRemetente INTEGER NOT NULL, "
                + "idDestinatario INTEGER NOT NULL, "
                + "lida BOOLEAN DEFAULT false NOT NULL, "
                + "aprovacao BOOLEAN DEFAULT false NOT NULL,"
                + "titulo VARCHAR(100) NOT NULL,"
                + "mensagem VARCHAR(250) NOT NULL,"
                + "FOREIGN KEY(idRemetente) REFERENCES usuario(idUsuario),"
                + "FOREIGN KEY(idDestinatario) REFERENCES usuario(idUsuario)"
                + ");";
        
        Connection con = ConnectionSQLiteFactory.getConnection();
        Statement st = con.createStatement();
        st.execute(sql);
        st.close();
        ConnectionSQLiteFactory.closeConnection(con);
    }
    
    @Override
    public void criar(Notificacao notificacao) {
        String sql = "INSERT INTO notificacao(idRemetente, idDestinatario, titulo, mensagem, aprovacao) "
                + "VALUES(?, ?, ?, ?, ?);";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConnectionSQLiteFactory.getConnection();
            pst = con.prepareStatement(sql);
            pst.setLong(1, notificacao.getIdRemetente());
            pst.setLong(2, notificacao.getIdDestinatario());
            pst.setString(3, notificacao.getTitulo());
            pst.setString(4, notificacao.getMensagem());
            pst.setBoolean(5, notificacao.isAprovacao());
            pst.execute();
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, pst);
        }
    }

    @Override
    public void marcarComoLida(Notificacao notificacao) {
        String sql = "UPDATE notificacao SET lida = true WHERE idNotificacao = ?";
        Connection con = null;
        PreparedStatement ps = null;
       
        try {
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, notificacao.getIdNotificacao());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps);
        }
        
    }

    @Override
    public void deletar(Long idNotificacao) {
        String sql = "DELETE FROM notificacao WHERE idNotificacao = ?;";
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConnectionSQLiteFactory.getConnection();
            pst = con.prepareStatement(sql);
            pst.setLong(1, idNotificacao);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, pst);
        }
    }

    @Override
    public List<Notificacao> getNotificacoes(Usuario usuario, boolean somenteLidas) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Notificacao notificacao;
        List<Notificacao> resposta = new ArrayList<>();
        try {
            String sql = "SELECT * FROM notificacao WHERE idDestinatario = ?";
            sql = (somenteLidas) ? sql + " AND lida = true;" : sql + ";";
           
            con = ConnectionSQLiteFactory.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            rs = ps.executeQuery();
            
            while(rs.next()) {
                notificacao = new Notificacao(
                    rs.getLong("idNotificacao"), 
                    rs.getLong("idRemetente"),
                    rs.getLong("idDestinatario"), 
                    rs.getString("titulo"),
                    rs.getString("mensagem"),
                    rs.getBoolean("lida"),
                    rs.getBoolean("aprovacao")
                );
                
                resposta.add(notificacao);
            }
            
            return resposta;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionSQLiteFactory.closeConnection(con, ps, rs);
        }
    }
    
}

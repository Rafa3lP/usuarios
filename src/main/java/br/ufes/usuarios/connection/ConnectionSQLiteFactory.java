/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class ConnectionSQLiteFactory {
    private static final String URL = "jdbc:sqlite:banco.db";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        }         
        catch(SQLException ex) {
            throw new RuntimeException("Falha ao conectar com banco de dados", ex.getCause());
        }
    }
    
    public static void closeConnection(Connection con) {
        
        try {
            if(con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLiteFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("falha ao fechar conexao", ex.getCause());
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement pst) {
        closeConnection(con);
        try {
            if(pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLiteFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("falha ao fechar conexao", ex.getCause());
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) {
        closeConnection(con, pst);
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQLiteFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("falha ao fechar conexao", ex.getCause());
        }
    }
    
}

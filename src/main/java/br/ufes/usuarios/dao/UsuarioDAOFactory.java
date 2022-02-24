/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.dao;

/**
 *
 * @author Rafael
 */
public class UsuarioDAOFactory implements IUsuarioDAOFactory {

    @Override
    public IUsuarioDAO cria(String tipo) {
        switch (tipo) {
            case "sqlite":
                return new UsuarioSQLiteDAO();
            default:
                throw new RuntimeException("Tipo de DAO não suportado");
        }
    }
    
}

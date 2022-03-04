/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.dao;

/**
 *
 * @author Rafael
 */
public class NotificacaoDAOFactory implements INotificacaoDAOFactory {

    @Override
    public INotificacaoDAO cria(String tipo) {
        switch (tipo) {
            case "sqlite":
                return new NotificacaoSQLiteDAO();
            default:
                throw new RuntimeException("Tipo de DAO não suportado");
        }
    }
    
}

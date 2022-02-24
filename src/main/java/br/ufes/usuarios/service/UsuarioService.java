/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.service;

import br.ufes.usuarios.dao.IUsuarioDAO;
import br.ufes.usuarios.dao.IUsuarioDAOFactory;

/**
 *
 * @author Rafael
 */
public class UsuarioService {
    private IUsuarioDAOFactory factory;
    private IUsuarioDAO dao;

    public UsuarioService(IUsuarioDAOFactory factory, String tipo) {
        this.factory = factory;
        this.dao = this.factory.cria(tipo);
    }
    
}

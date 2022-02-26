/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command;

import br.ufes.usuarios.dao.UsuarioDAOFactory;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.service.UsuarioService;

/**
 *
 * @author Rafael
 */
public class SalvarUsuarioCommand extends ManterUsuarioPresenterCommand {
    private UsuarioService service;
    private Usuario usuario;

    public SalvarUsuarioCommand(Usuario usuario) {
        this.service = new UsuarioService(new UsuarioDAOFactory(), "sqlite");
        this.usuario = usuario;
    }
    
    @Override
    public void executar() {
       this.service.criar(this.usuario);
    }
    
}

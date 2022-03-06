/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command;

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
        this.service = UsuarioService.getInstancia();
        this.usuario = usuario;
    }
    
    @Override
    public void executar() {
       validar(usuario);
       if(this.service.getListaUsuarios(null).isEmpty()) {
           this.service.criarAdministrador(usuario);
       } else {
           this.service.criarUsuario(usuario);
       }
       
    }
    
}

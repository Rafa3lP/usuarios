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
public class ExcluirUsuarioCommand extends ManterUsuarioPresenterCommand {
    private UsuarioService service;
    private Usuario usuario;

    public ExcluirUsuarioCommand(Usuario usuario) {
        this.service = UsuarioService.getInstancia();
        this.usuario = usuario;
    }
    
    @Override
    public void executar() {
       this.service.deletar(this.usuario);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.usuario;

import br.ufes.usuarios.model.Usuario;

/**
 *
 * @author Rafael
 */
public class AprovarUsuarioCommand extends ManterUsuarioPresenterCommand {

    public AprovarUsuarioCommand(Usuario usuario) {
        super(usuario);
    }
    
    @Override
    public void executar() {
        service.aprovarUsuario(usuario.getId());
    }
    
}

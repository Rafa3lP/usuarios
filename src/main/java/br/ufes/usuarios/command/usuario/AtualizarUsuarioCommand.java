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
public class AtualizarUsuarioCommand extends ManterUsuarioPresenterCommand {
    private boolean alterarSenha;

    public AtualizarUsuarioCommand(Usuario usuario, boolean alterarSenha) {
        super(usuario);
        this.alterarSenha = alterarSenha;
    }
    
    @Override
    public void executar() {
       if(alterarSenha) {
           validar(true);
           service.atualizar(usuario);
           service.alterarSenha(usuario);
       }else{
           validar(false);
           service.atualizar(usuario);
       }
    
    }
    
}

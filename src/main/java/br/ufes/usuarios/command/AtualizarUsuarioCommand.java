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
public class AtualizarUsuarioCommand extends ManterUsuarioPresenterCommand {
    private UsuarioService service;
    private Usuario usuario;
    private boolean alterarSenha;

    public AtualizarUsuarioCommand(Usuario usuario, boolean alterarSenha) {
        this.service = UsuarioService.getInstancia();
        this.usuario = usuario;
        this.alterarSenha = alterarSenha;
    }
    
    @Override
    public void executar() {
       if(alterarSenha) {
           validar(usuario);
           service.atualizarComSenha(this.usuario);
       }else{
           service.atualizar(this.usuario);
       }
    
    }
    
}

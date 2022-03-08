/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.usuario;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.service.UsuarioService;
import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;

/**
 *
 * @author Rafael
 */
public abstract class ManterUsuarioPresenterCommand {
    protected UsuarioService service = UsuarioService.getInstancia();
    protected Usuario usuario;

    public ManterUsuarioPresenterCommand(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public abstract void executar();
    
    public final void validar(boolean validarSenha) {
        
        if(validarSenha) {
            //validar senha
            List<String> errosSenha = new ValidadorSenha().validar(usuario.getSenha());
            if(!errosSenha.isEmpty()) {
                throw new RuntimeException(errosSenha.get(0));
            }
        }
        
    }
}

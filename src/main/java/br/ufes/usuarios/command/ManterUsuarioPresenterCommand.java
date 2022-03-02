/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command;

import br.ufes.usuarios.model.Usuario;
import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author Rafael
 */
public abstract class ManterUsuarioPresenterCommand {
    
    public abstract void executar();
    
    public final void validar(Usuario usuario) {
        //validar senha
        List<String> errosSenha = new ValidadorSenha().validar(usuario.getSenha());
        if(!errosSenha.isEmpty()) {
            throw new RuntimeException(errosSenha.get(0));
        }
        
    }
}

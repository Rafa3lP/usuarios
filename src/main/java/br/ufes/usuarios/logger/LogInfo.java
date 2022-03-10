/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger;

import br.ufes.usuarios.model.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rafael
 */
public class LogInfo extends Log {

    public LogInfo(Usuario usuarioAlvo, Usuario usuarioAutenticado, int operacao) {
        super(usuarioAlvo, usuarioAutenticado, operacao);
        createMensagem();
    }
    
    @Override
    protected void createMensagem() {
        setMensagem(
            getOperacao() + ": " + getUsuarioAlvo().getNome() + ",(" + 
            LocalDate.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            ) + ", " + 
            LocalTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm")
            ) + ", e " + 
            getUsuarioAutenticado().getUsuario() + ")"
        );
    }
    
}

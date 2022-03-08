/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.notificacao;

import br.ufes.usuarios.model.Notificacao;

/**
 *
 * @author Rafael
 */
public class EnviarNotificacaoCommand extends NotificacaoPresenterCommand {

    public EnviarNotificacaoCommand(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public void executar() {
        validarNotificacao();
        usuarioService.enviarNotificacao(notificacao);
    }
    
}

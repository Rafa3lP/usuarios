/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.manternotificacao;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class AprovarNotificacaoCommand extends NotificacaoPresenterCommand {
    private Notificacao notificacao;
    
    public AprovarNotificacaoCommand(ManterNotificacaoPresenter presenter, Notificacao notificacao) {
        super(presenter);
        this.notificacao = notificacao;
    }

    @Override
    public void executar() {
        usuarioService.aprovarUsuario(notificacao.getIdRemetente());
        JOptionPane.showMessageDialog(
            view, 
            "Usuario Aprovado com sucesso!",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
        usuarioService.enviarNotificacao(
            new Notificacao(
                notificacao.getIdDestinatario(), 
                notificacao.getIdRemetente(), 
                "Bem-vindo!", 
                "Seja bem-vindo ao sistema de usuarios!",
                false
            )
        );
        usuarioService.deletarNotificacao(notificacao);
    }
    
}

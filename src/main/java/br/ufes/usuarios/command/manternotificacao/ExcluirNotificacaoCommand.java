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
public class ExcluirNotificacaoCommand extends NotificacaoPresenterCommand {
    private Notificacao notificacao;
    public ExcluirNotificacaoCommand(ManterNotificacaoPresenter presenter, Notificacao notificacao) {
        super(presenter);
        this.notificacao = notificacao;
    }
   
    @Override
    public void executar() {
        usuarioService.deletarNotificacao(notificacao);
        JOptionPane.showMessageDialog(
            view, 
            "Notificacao Excluida", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
}

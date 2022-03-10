/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.manternotificacao;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class EnviarNotificacaoCommand extends NotificacaoPresenterCommand {
    private Usuario remetente;
    private Usuario destinatario;

    public EnviarNotificacaoCommand(ManterNotificacaoPresenter presenter, Usuario remetente, Usuario destinatario) {
        super(presenter);
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    @Override
    public void executar() {
        String titulo = this.view.getTxtTitulo().getText();
        String mensagem = this.view.getTxtMensagem().getText();
        
        Notificacao notificacao = new Notificacao(
            this.remetente.getId(),
            this.destinatario.getId(), 
            titulo, 
            mensagem,
            false
        );
        
        validarNotificacao(notificacao);
        
        usuarioService.enviarNotificacao(notificacao);
        
        JOptionPane.showMessageDialog(
            this.view, 
            "Notificação enviada!", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
        
    }
    
}

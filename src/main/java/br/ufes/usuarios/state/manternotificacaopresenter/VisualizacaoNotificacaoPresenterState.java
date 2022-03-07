/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manternotificacaopresenter;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import br.ufes.usuarios.service.UsuarioService;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class VisualizacaoNotificacaoPresenterState extends ManterNotificacaoPresenterState {
    private Notificacao notificacao;
    private UsuarioService usuarioService;
    
    public VisualizacaoNotificacaoPresenterState(ManterNotificacaoPresenter presenter,Notificacao notificacao) {
        super(presenter);
        this.usuarioService = UsuarioService.getInstancia();
        this.view.setTitle("Visualizar Notificação");
        
        this.notificacao = notificacao;
        
        Usuario remetente = usuarioService.lerPorId(notificacao.getIdRemetente());
        Usuario destinatario = usuarioService.lerPorId(notificacao.getIdDestinatario());
        
        if(!notificacao.isLida()) usuarioService.lerNotificacao(notificacao);
        
        this.view.getTxtRemetente().setText(remetente.getNome());
        this.view.getTxtDestinatario().setText(destinatario.getNome());
        this.view.getTxtTitulo().setText(notificacao.getTitulo());
        this.view.getTxtMensagem().setText(notificacao.getMensagem());
        this.view.getTxtMensagem().setEnabled(false);
        this.view.getTxtTitulo().setEnabled(false);
        this.view.getTxtRemetente().setEnabled(false);
        this.view.getTxtDestinatario().setEnabled(false);
        this.view.getBtnEnviar().setVisible(false);
        
        if(notificacao.isAprovacao() && !remetente.isAprovado()) {
            this.view.getBtnAprovar().setVisible(true);
            this.view.getBtnRecusar().setVisible(true);
            this.view.getBtnExcluir().setVisible(false);
            
        } else {
            this.view.getBtnAprovar().setVisible(false);
            this.view.getBtnRecusar().setVisible(false);
            this.view.getBtnExcluir().setVisible(true);
        }
        
    }
    
    @Override
    public void aprovar() {
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
        fechar();
    }
    
    @Override
    public void recusar() {
        usuarioService.deletar(usuarioService.lerPorId(notificacao.getIdRemetente()));
        usuarioService.deletarNotificacao(notificacao);
        JOptionPane.showMessageDialog(
            view, 
            "Solicitação recusada!", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
        fechar();
    }
    
    public void excluir() {
        usuarioService.deletarNotificacao(notificacao);
        JOptionPane.showMessageDialog(
            view, 
            "Notificacao Excluida", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
        fechar();
    }
    
}

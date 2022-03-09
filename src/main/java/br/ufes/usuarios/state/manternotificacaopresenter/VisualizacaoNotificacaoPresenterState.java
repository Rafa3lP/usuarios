/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manternotificacaopresenter;

import br.ufes.usuarios.command.manternotificacao.AprovarNotificacaoCommand;
import br.ufes.usuarios.command.manternotificacao.ExcluirNotificacaoCommand;
import br.ufes.usuarios.command.manternotificacao.RecusarNotificacaoCommand;
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
    private Usuario remetente;
    private Usuario destinatario;
    
    public VisualizacaoNotificacaoPresenterState(ManterNotificacaoPresenter presenter,Notificacao notificacao) {
        super(presenter);
        this.usuarioService = UsuarioService.getInstancia();
        this.view.setTitle("Visualizar Notificação");
        
        this.notificacao = notificacao;
        
        remetente = usuarioService.lerPorId(notificacao.getIdRemetente());
        
        if(remetente == null) {
            JOptionPane.showMessageDialog(
                view, 
                "O remetente não existe mais no sistema!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            excluir();
        }
        
        destinatario = usuarioService.lerPorId(notificacao.getIdDestinatario());
        
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
        new AprovarNotificacaoCommand(presenter, notificacao).executar();
        fechar();
    }
    
    @Override
    public void recusar() {
        new RecusarNotificacaoCommand(presenter, notificacao).executar();
        fechar();
    }
    
    public void excluir() {
        new ExcluirNotificacaoCommand(presenter, notificacao).executar();
        fechar();
    }
    
}

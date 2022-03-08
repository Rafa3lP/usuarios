/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manternotificacaopresenter;

import br.ufes.usuarios.command.notificacao.EnviarNotificacaoCommand;
import br.ufes.usuarios.command.notificacao.ExcluirNotificacaoCommand;
import br.ufes.usuarios.command.usuario.AprovarUsuarioCommand;
import br.ufes.usuarios.command.usuario.ExcluirUsuarioCommand;
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
        new AprovarUsuarioCommand(remetente).executar();
        JOptionPane.showMessageDialog(
            view, 
            "Usuario Aprovado com sucesso!",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
        new EnviarNotificacaoCommand(
            new Notificacao(
                notificacao.getIdDestinatario(), 
                notificacao.getIdRemetente(), 
                "Bem-vindo!", 
                "Seja bem-vindo ao sistema de usuarios!",
                false
            )
        ).executar();
        new ExcluirNotificacaoCommand(notificacao).executar();
        fechar();
    }
    
    @Override
    public void recusar() {
        new ExcluirUsuarioCommand(remetente).executar();
        new ExcluirNotificacaoCommand(notificacao).executar();
        JOptionPane.showMessageDialog(
            view, 
            "Solicitação recusada!", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
        fechar();
    }
    
    public void excluir() {
        new ExcluirNotificacaoCommand(notificacao).executar();
        JOptionPane.showMessageDialog(
            view, 
            "Notificacao Excluida", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
        fechar();
    }
    
}

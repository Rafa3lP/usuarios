/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import br.ufes.usuarios.service.UsuarioService;

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
        this.view.getBtnAprovar().setVisible(false);
    }
    
}

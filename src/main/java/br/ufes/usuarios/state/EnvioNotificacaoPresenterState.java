/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import br.ufes.usuarios.service.UsuarioService;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class EnvioNotificacaoPresenterState extends ManterNotificacaoPresenterState {
    private UsuarioService service;
    private Usuario remetente;
    private Usuario destinatario;
    public EnvioNotificacaoPresenterState(ManterNotificacaoPresenter presenter, Usuario destinatario) {
        super(presenter);
        service = UsuarioService.getInstancia();
        this.view.setTitle("Enviar Notificação");
        this.remetente = Application.getSession().getUsuario();
        this.destinatario = destinatario;
        
        if(remetente.getId().longValue() == destinatario.getId().longValue()) {
            throw new RuntimeException("Você não pode enviar uma notificação para você mesmo");
        }
        
        this.view.getTxtRemetente().setText(this.remetente.getNome());
        this.view.getTxtDestinatario().setText(this.destinatario.getNome());
        this.view.getTxtRemetente().setEnabled(false);
        this.view.getTxtDestinatario().setEnabled(false);
        this.view.getBtnEnviar().setVisible(true);
        this.view.getBtnAprovar().setVisible(false);
    }
    
    @Override
    public void enviar() {
        String titulo = this.view.getTxtTitulo().getText();
        String mensagem = this.view.getTxtMensagem().getText();
        
        Notificacao notificacao = new Notificacao(
            this.destinatario.getId(), 
            titulo, mensagem
        );
        
        this.service.enviarNotificacao(notificacao);
        
        JOptionPane.showMessageDialog(this.view, "Notificação enviada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.state.manternotificacaopresenter.EnvioNotificacaoPresenterState;
import br.ufes.usuarios.state.manternotificacaopresenter.ManterNotificacaoPresenterState;
import br.ufes.usuarios.state.manternotificacaopresenter.VisualizacaoNotificacaoPresenterState;
import br.ufes.usuarios.view.ManterNotificacaoView;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class ManterNotificacaoPresenter {
    private ManterNotificacaoView view;
    private ManterNotificacaoPresenterState state;
    private MainPresenter mainPresenter;
    private Notificacao notificacao;
    private Usuario destinatario;

    public ManterNotificacaoPresenter(MainPresenter mainPresenter, Notificacao notificacao) {
        this.view = new ManterNotificacaoView();
        this.mainPresenter = mainPresenter;
        this.notificacao = notificacao;
        this.state = new VisualizacaoNotificacaoPresenterState(this, notificacao);
        exibir();
        configuraBotoes();
    }
    
    public ManterNotificacaoPresenter(MainPresenter mainPresenter, Usuario destinatario) {
        this.view = new ManterNotificacaoView();
        this.mainPresenter = mainPresenter;
        this.destinatario = destinatario;
        this.state = new EnvioNotificacaoPresenterState(this, this.destinatario);
        exibir();
        configuraBotoes();
    }
    
    public void configuraBotoes() {
        this.view.getBtnFechar().addActionListener((e) -> {
            try {
                this.state.fechar();
            }catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnEnviar().addActionListener((e) -> {
            try {
                this.state.enviar();
            } catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        this.view.getBtnAprovar().addActionListener((e) -> {
            try {
                this.state.aprovar();
            }catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnRecusar().addActionListener((e) -> {
            try {
                this.state.recusar();
            }catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnExcluir().addActionListener((e) -> {
            try {
                this.state.excluir();
            } catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public ManterNotificacaoView getView() {
        return view;
    }

    public ManterNotificacaoPresenterState getState() {
        return state;
    }

    public void setState(ManterNotificacaoPresenterState state) {
        this.state = state;
    }
    
    public void exibir() {
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manternotificacaopresenter;

import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import br.ufes.usuarios.view.ManterNotificacaoView;

/**
 *
 * @author Rafael
 */
public abstract class ManterNotificacaoPresenterState {
    protected ManterNotificacaoPresenter presenter;
    protected ManterNotificacaoView view;
    public ManterNotificacaoPresenterState(ManterNotificacaoPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
    }
    
    public void enviar() {
        throw new RuntimeException("Método enviar não pode ser executado");
    }
   
    public void aprovar() {
        throw new RuntimeException("Método aprovar não pode ser executado");
    }
    
    public void fechar() {
        this.view.dispose();
    }   
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.configuracoespresenter;

import br.ufes.usuarios.presenter.ConfiguracoesPresenter;
import br.ufes.usuarios.view.ConfiguracoesView;

/**
 *
 * @author Rafael
 */
public abstract class ConfiguracoesPresenterState {
    protected ConfiguracoesPresenter presenter;
    protected ConfiguracoesView view;
    
    public ConfiguracoesPresenterState(ConfiguracoesPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
    }
    
    public void salvar() {
        throw new RuntimeException("Método salvar não pode ser executado");
    }
    
    public void fechar() {
        this.view.dispose();
    }
}

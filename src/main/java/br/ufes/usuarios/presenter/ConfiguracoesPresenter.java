/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.state.configuracoespresenter.ProntoParaConfigurarConfiguracoesPresenterState;
import br.ufes.usuarios.state.configuracoespresenter.ConfiguracoesPresenterState;
import br.ufes.usuarios.view.ConfiguracoesView;

/**
 *
 * @author Rafael
 */
public class ConfiguracoesPresenter {
    private ConfiguracoesView view;
    private MainPresenter mainPresenter;
    private ConfiguracoesPresenterState state;

    public ConfiguracoesPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.view = new ConfiguracoesView();
        
        setState(new ProntoParaConfigurarConfiguracoesPresenterState(this));
        
        this.view.getBtnFechar().addActionListener((e) -> {
            state.fechar();
        });
        
        this.view.getBtnSalvar().addActionListener((e) -> {
            state.salvar();
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    public ConfiguracoesView getView() {
        return this.view;
    }

    public ConfiguracoesPresenterState getState() {
        return state;
    }

    public void setState(ConfiguracoesPresenterState state) {
        this.state = state;
    }
    
}

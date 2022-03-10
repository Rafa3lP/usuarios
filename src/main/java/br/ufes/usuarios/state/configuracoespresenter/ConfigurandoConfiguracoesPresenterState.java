/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.configuracoespresenter;

import br.ufes.usuarios.presenter.ConfiguracoesPresenter;

/**
 *
 * @author Rafael
 */
public class ConfigurandoConfiguracoesPresenterState extends ConfiguracoesPresenterState{
    
    public ConfigurandoConfiguracoesPresenterState(ConfiguracoesPresenter presenter) {
        super(presenter);
        view.getProgressConfiguracoes().setVisible(true);
        view.getBtnFechar().setEnabled(false);
        view.getBtnSalvar().setEnabled(false);
        view.getCbFormatoLog().setEnabled(false);
        view.getProgressConfiguracoes().setString("Configurando...");
        
    }
    
}

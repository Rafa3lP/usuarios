/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.configuracoespresenter;

import br.ufes.usuarios.command.configuracoes.AlterarLogConfiguracoesCommandTemplate;
import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.ConfiguracoesPresenter;
import javax.swing.JOptionPane;

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

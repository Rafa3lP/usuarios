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
public class ProntoParaConfigurarConfiguracoesPresenterState extends ConfiguracoesPresenterState{
    
    public ProntoParaConfigurarConfiguracoesPresenterState(ConfiguracoesPresenter presenter) {
        super(presenter);
        this.view.getProgressConfiguracoes().setVisible(false);
        this.view.getBtnFechar().setEnabled(true);
        this.view.getBtnSalvar().setEnabled(true);
        this.view.getCbFormatoLog().setEnabled(true);
        this.view.getCbFormatoLog().setSelectedItem(Application.getLogFormat());
    }
    
    @Override
    public void salvar() {
        new AlterarLogConfiguracoesCommandTemplate(presenter) {
            @Override
            public void onStart(){
                presenter.setState(new ConfigurandoConfiguracoesPresenterState(presenter));
            }
            
            @Override
            public void onSuccess() {
                JOptionPane.showMessageDialog(
                    view, 
                    "Alteração de formato de log realizada com sucesso!", 
                    "sucesso", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

            @Override
            public void onError(Exception ex) {
                throw new RuntimeException(ex);
            }
            
            @Override
            public void onFinish() {
                presenter.setState(new ProntoParaConfigurarConfiguracoesPresenterState(presenter));
            }
        }.executar();
        
    }
    
}

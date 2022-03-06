/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.configuracoespresenter;

import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.ConfiguracoesPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class AlteracaoConfiguracoesPresenterState extends ConfiguracoesPresenterState{
    
    public AlteracaoConfiguracoesPresenterState(ConfiguracoesPresenter presenter) {
        super(presenter);
        this.view.getProgressConfiguracoes().setVisible(false);
        this.view.getBtnFechar().setEnabled(true);
        this.view.getBtnSalvar().setEnabled(true);
        this.view.getCbFormatoLog().setEnabled(true);
    }
    
    @Override
    public void salvar() {
        String novoFormatoLog = (String) this.view.getCbFormatoLog().getSelectedItem();
        if(novoFormatoLog.equals(Application.getLogFormat())) {
            JOptionPane.showMessageDialog(
                view, 
                "O formato de log selecionado já está em uso", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(
                view, 
                "Deseja realmente alterar o formato de log?", 
                "Confirmação", 
                JOptionPane.YES_NO_OPTION
            );
            
            if(confirmacao == JOptionPane.YES_OPTION) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            view.getProgressConfiguracoes().setVisible(true);
                            view.getBtnFechar().setEnabled(false);
                            view.getBtnSalvar().setEnabled(false);
                            view.getCbFormatoLog().setEnabled(false);
                            view.getProgressConfiguracoes().setString("Alterando Formato de Log...");
                            Application.changeLogFormat(novoFormatoLog);
                            JOptionPane.showMessageDialog(
                                view, 
                                "Alteração de formato de log realizada com sucesso!", 
                                "sucesso", 
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }catch(RuntimeException ex) {
                            JOptionPane.showMessageDialog(
                                view, 
                                ex.getMessage(), 
                                "Erro", 
                                JOptionPane.ERROR_MESSAGE
                            );
                        } finally {
                            view.getProgressConfiguracoes().setVisible(false);
                            view.getBtnFechar().setEnabled(true);
                            view.getBtnSalvar().setEnabled(true);
                            view.getCbFormatoLog().setEnabled(true);
                        }

                    }
                }.start();
   
            }
            
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.configuracoes;

import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.ConfiguracoesPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class AlterarLogConfiguracoesCommandTemplate extends ConfiguracoesCommandTemplate {

    public AlterarLogConfiguracoesCommandTemplate(ConfiguracoesPresenter presenter) {
        super(presenter);
    }

    @Override
    public void executar() {
        String novoFormatoLog = (String) this.view.getCbFormatoLog().getSelectedItem();
        if(novoFormatoLog.equals(Application.getLogFormat())) {
            JOptionPane.showMessageDialog(
                view,
                "O formato de log selecionado já está em uso", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE
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
                            onStart();
                            Application.changeLogFormat(novoFormatoLog);
                            onSuccess();
                        }catch(RuntimeException ex) {
                            onError(ex.getMessage());
                        } finally {
                            onFinish();
                        }

                    }
                }.start();
            }
        }
    }

}

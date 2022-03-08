/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.configuracoes;

import br.ufes.usuarios.presenter.Application;

/**
 *
 * @author Rafael
 */
public class AlterarLogConfiguracoesCommandTemplate extends ConfiguracoesCommandTemplate {

    @Override
    public void executar(String novoFormatoLog) {
        new Thread(){
            @Override
            public void run() {
                try {
                    created();
                    Application.changeLogFormat(novoFormatoLog);
                    onSuccess();
                }catch(RuntimeException ex) {
                    onError(ex.getMessage());
                } finally {
                    completed();
                }

            }
        }.start();
    }
    
}

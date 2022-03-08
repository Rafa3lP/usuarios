/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.configuracoes;

/**
 *
 * @author Rafael
 */
public abstract class ConfiguracoesCommandTemplate {
    public void created() {
        
    }
    public void completed() {
        
    }
    
    public void onError(String errorMessage) {
        
    }
    
    public void onSuccess() {
        
    }
    public abstract void executar(String novoFormatoLog);
}
